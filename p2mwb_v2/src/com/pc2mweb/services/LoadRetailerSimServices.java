package com.pc2mweb.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.paysetter.amax.AMAXConstants;
import com.paysetter.amax.TransferStocksConstant;
import com.paysetter.commons.pctomobile.P2MConstants;
import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.StockTransferResponse;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.beans.RetailerSimBean;
import com.pc2mweb.controller.LoadRetailerSimController;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransfertoRetailerDAO;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TransfertoRetailerModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class LoadRetailerSimServices {
	
	
	private static final Logger logger = Logger.getLogger(LoadRetailerSimServices.class);

	private static DataSource ds;
	private static DataSource SMSds;
	private static ProfileManagerDAO profileManager  = null;
	//private static WalletManager  walletManager   = null;
	private static GlobeUtil globeUtil;
	private static TouchMobileUtil tmUtil;
	
	private static String GHP_NUMRANGE  = null;
	private static String TM_NUMRANGE   = null;
	
	private static String AMAX_HOST     = null;
	private static String AMAX_URI      = null;
	private static String AMAX_USER     = null;
	private static String AMAX_PASSWORD = null;
	
	private static String dbContext;
	private static String smsDbContext = null;
	
	private static String AMAX_DEBUG = null;
	
	
	
	public String loadRetailer(TransfertoRetailerModel topup, HttpSession usersession,ServletContext servletContext){
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ApplicationContext  smscontext = new ClassPathXmlApplicationContext("Spring-SMS.xml");
		
		OutgoingSMSWriterDAO smsdao =  (OutgoingSMSWriterDAO)smscontext.getBean("smsDAO");
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		RetailerSimBean bean = new RetailerSimBean();
		
		GHP_NUMRANGE = servletContext.getInitParameter("GHP_NUMRANGE");
		TM_NUMRANGE  = servletContext.getInitParameter("TM_NUMRANGE");

		try {
			globeUtil = new GlobeUtil(new FileInputStream(GHP_NUMRANGE));
			tmUtil    = new TouchMobileUtil(new FileInputStream(TM_NUMRANGE));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AMAX_DEBUG  = servletContext.getInitParameter("DEBUG_AMAX");
		AMAX_HOST =	servletContext.getInitParameter("AMAX_HOST");
		AMAX_URI =	servletContext.getInitParameter("AMAX_URI");
		AMAX_USER =	servletContext.getInitParameter("AMAX_USER");
		AMAX_PASSWORD =	servletContext.getInitParameter("AMAX_PASSWORD");
		
		String mobile = null;
		int errorState = 0;

		//Connection conn = null;
		Connection sms_conn = null;
		PartnerProfile partner = null;

		int serviceType = 0;
		
		mobile = topup.getMsisdn();
		logger.info("check retailer sim: " + topup.getMsisdn() + ", topup amount: " + topup.getAmount());
		
		if(!topup.getAmount().equals("") && !this.isNumber(topup.getAmount())){
	    	
	    	return "Invalid Amount";
	    	
	    }else
	    	
	
		if(topup.getAmount().equals(""))
		{
			
			return "";
			
		}
		
		if(!retailerDao.getSimInfo(topup.getMsisdn(),bean)){
			
			errorState = P2MConstants.AGENT_PERMISSION_ERROR_CODE;
			logger.info(P2MConstants.getMessage(errorState));
			mobile = null;
			closeconn(sms_conn);
			return P2MConstants.getMessage(errorState).toString();
			
		}
		
		
		topup.setProdtype("TRANSFER");
		topup.setTxtype("mobile");
		topup.setRequestype("load");
		
		logger.info("simPID: "+bean.pid +",simNumber: "+bean.msisdn+"");

		try {
			logger.info("get profile: " + usersession.getAttribute("USER"));
			partner = profiledao.GetProfilebyUsername(usersession.getAttribute("USER").toString());
			topup.agentid = partner.agentid;
			topup.txid = dao.insertTransaction(topup,usersession);
			logger.info("topup tx id is: "+topup.txid + ",User agent id: " + partner.agentid + ",User pid: " + partner.partnerid);
			logger.info("partner topup tx id is: "+topup.ptxid);
		} catch (Exception e) {
			e.printStackTrace();
			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
			logger.info(P2MConstants.getMessage(errorState));
			mobile = null;
			topup = null;
			closeconn(sms_conn);
			return  pc2mwebFunc.P2MConstantsgetMessage(errorState);
		}
		
		// Check if TM or Globe for EPIN HANDLING
		if (globeUtil.isPrepaidNumber(mobile)) {
			serviceType = 1;
		} else if (tmUtil.isPrepaidNumber(mobile)) {
			serviceType = 2;
		}
		
		if ( !(globeUtil.isPrepaidNumber(mobile) || tmUtil.isPrepaidNumber(mobile)) ) {
			errorState = P2MConstants.NUMBER_NOT_IN_RANGE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}
		
		logger.info("check balance :  " + partner.agentid + "," + partner.partnerid + "," + partner.wallet + "," + topup.getAmount() +"," + bean.pid + "," );
		
		
		
		if ( errorState != 0 ) {
			dao.updateTransaction(topup.txid,topup.ptxid,errorState,"0",usersession);
			logger.info(P2MConstants.getMessage(errorState));
			mobile = null;
			topup = null;
			closeconn(sms_conn);
			return P2MConstants.getMessage(errorState).toString();
		}
		
		StockTransferResponse resp = null;
		int debit = 0;
		String session = null;
		boolean loginsuccess = false;
		
		
		
		if(partner.runmode.equalsIgnoreCase("TEST"))
		
		{
			
			debit = retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()) * -1, bean);	
			
			
			resp = new StockTransferResponse();
			
			resp.setTransferResultCode(0);
			resp.setSessionID("TESTTEST");
			resp.setTransferTransactionID(1234567);
			
			if(resp != null)
			{
				
				
				errorState = resp.getTransferResultCode();
				
			}
			
			try {
				
				logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState);
				
				if ( resp != null ) {
						
					dao.updateTransactionTest(topup.txid,topup.ptxid,errorState,resp.getTransferTransactionID()+"",usersession);
					
					if(errorState == 0)
					{
						
						retailerDao.insertRetailSimTransferLogs(usersession,mobile,topup.txid+"",AMAXConstants.getMessage(0),resp.getTransferTransactionID()+"",Integer.parseInt(topup.getAmount()));
					
					}
					
					
					
				}  

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
	
				
			}

		}
		
		
		else
		
		{
			

			

			try {

				logger.info("debiting wallet : " + topup.txid + " " + topup.getAmount() );
				
				debit = retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()) * -1, bean);	
				
				logger.info("Debit result: " + debit);
				logger.info("+++++++++++++++++++AMAX TEST++++++++++++++++" + mobile);
			
				
				if (debit != 1) {
					logger.info("Unable to deduct from wallet from  " + partner.partnerid  + "; Amount: " + topup.getAmount());	
					errorState = P2MConstants.DEBIT_FAILED_CODE;	
					
				}
				
				else		
				{
					logger.info("tx id: " + topup.txid);
					dao.updateTransaction(topup.txid,topup.ptxid,63,"0",usersession);
					logger.info("+++++++++++++++++++AMAX IS ON, try to connect to AMAX++++++++++++++++");
					AutoloadMax amax = new AutoloadMax(AMAX_HOST, AMAX_URI);
					
					try {
						if (!AMAX_DEBUG.equals("SESSION"))
							session = amax.createSession();
						else
							throw new Exception("session amax debug");
						logger.info("AMAX Session creation result: " + session);
					} catch (Exception e) { 
						   session = null;
						   //errorState = P2MConstants.NETWORK_ERROR_SESSION_CODE; 
						   //logger.info(P2MConstants.getMessage(errorState));
						   logger.info("Error in Session Amax " + e.getMessage());
					}
					
				if ( session != null ) 
				{
							
							try {					   
							   logger.info("+++++++++++++++++++Login to Amax p2mweb_domestic+++++++++");
							   if (!AMAX_DEBUG.equals("LOGIN")){
								   amax.login(session, AMAX_USER, AMAX_PASSWORD);
								   loginsuccess = true;
							   } else
								   throw new Exception("login amax debug");	   
							} catch (Exception e) {
							   logger.info("Error in  Amax Login");
							   loginsuccess = false;
							   resp = null;
							} 
							
							if(loginsuccess){
						
								try {
									dao.updateTransaction(topup.txid,topup.ptxid,62,session,usersession);
									 if (!AMAX_DEBUG.equals("TOPUP"))

									 	 resp = amax.requestStockTransfer(session, mobile, Integer.parseInt(topup.getAmount()), "","PC2MWEB");
										 //resp = amax.requestStockTransfer(session, "20", Integer.parseInt(topup.getAmount()), "");
									 else
										 throw new Exception("topup amax debug");
									} catch (Exception e) {
										logger.info("Error in Topup Amax");
									    resp = null;
									} 
							
							
							if ( resp != null ) 
							{
								
								errorState = resp.getTransferResultCode();
								
								logger.info("Stock Transfer Response: "+resp.getTransferResultCode());
								
									switch (errorState) 
									{
									
										case TransferStocksConstant.TRANSFER_STOCK_APPROVE_CODE:
						
											logger.info("++++++++++++++++Transfer Stocks Successful in AMAX+++++++++++++++++++++");
											logger.info("Transfer Stocks Transaction " + topup.txid + ": " + "AMAX Response= " + errorState + "; Trace= " + resp.getTransferTransactionID());
										
											if (topup.getMessage() != null && !topup.getMessage().equalsIgnoreCase(""))
											{
												OutgoingSMSModel SMSout = new OutgoingSMSModel();										

											
												SMSout.setSender("2808");
												SMSout.setRecipient(mobile);
												SMSout.setSmsData(topup.getMessage());
												SMSout.setTariffClass("");
												SMSout.setServiceType("");
																				
												try {
									
													smsdao.insertSmsSent(SMSout);
												} catch (Exception e) {
													e.printStackTrace();
													logger.info("***** Failed to insert SMS *****");
												
												} finally {
													SMSout = null;
											
												}
											}
											break;

										
										default:
											logger.info("+++++++++++++++TRANSFER STOCKS CODE FAILED+++++++++++++");
										
											
											// Refund wallet
											if ( debit == 1 ) 
											{
												 //walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
												retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()), bean);	
											}
										break;													
									}						
							}
							else
							{
								// no revert wallet if topup unsuccessful
								logger.info("+++++++++AMAX TRANSFER ERROR+++++++++++++");
								errorState = P2MConstants.AMAX_REQUEST_TOPUP_ERROR_CODE;
							}
								
							}
							else
							{
								
								logger.info("+++++++++ERROR AMAX LOGIN+++++++++++++");
								errorState = P2MConstants.AMAX_LOGIN_ERROR_CODE;
								if ( debit == 1 ) 
								{
									//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
									retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()), bean);	
								}
								
							}
					
							}
							else
							{
								
								logger.info("+++++++++ERROR CREATING SESSION +++++++++++++");
								errorState = P2MConstants.NETWORK_ERROR_SESSION_CODE;
								if ( debit == 1 ) 
								{
									//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
									retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()), bean);	
								}
												
							}	
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
				
				// Refund wallet
				if ( debit == 1 ) {
					//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
					retailerDao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()), bean);	
				}
			}
			try {
				logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState);
				
				if ( resp != null ) {
						
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,resp.getTransferTransactionID()+"",usersession);
					
					if(errorState == 0)
					{
						
						retailerDao.insertRetailSimTransferLogs(usersession,mobile,topup.txid+"",AMAXConstants.getMessage(0),resp.getTransferTransactionID()+"",Integer.parseInt(topup.getAmount()));
					
					}
					
					
					
				}  else  if (session == null){
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,"17",usersession);
				}
				else 
				{
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,session,usersession);
				}
				
				
				
				//sms_conn.close(); 
			
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				sms_conn = null;
				
			}
			
			
		}
		

		
		if ( resp != null ) { 

			logger.info(pc2mwebFunc.AmaxgetMessage(errorState)+" for mobile phone number: "+mobile);
			return pc2mwebFunc.AmaxgetMessage(errorState).toString();
		}else {
	
			logger.info(pc2mwebFunc.P2MConstantsgetMessage(errorState)+ "for mobile phone number: "+mobile);
			return pc2mwebFunc.P2MConstantsgetMessage(errorState).toString();
		}
		
	}

	
	private void closeconn(Connection sms_conn)
	{
		try {
			
			if (sms_conn != null)
				sms_conn.close();
		}
		catch (Exception exclose)
		{
			
		}

	}
	
    public boolean isNumber( String tx )  
    {  
       try  
       {  
          Float.parseFloat( tx );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }  
}
