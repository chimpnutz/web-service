package com.pc2mweb.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.paysetter.amax.AMAXConstants;
import com.paysetter.commons.pctomobile.P2MConstants;
import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.TopUpResponse;
import com.paysetter.topup.gcash.dao.HandleGcash2Load;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;
import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.controller.LoadCustomerController;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.Wallet_Transaction_Information;
import com.pc2mweb.utility.function.pc2mwebFunc;

public class LoadCustomerServices 

{	private static DataSource ds;
private static DataSource SMSds;
private static ProfileManagerDAO profileManager  = null;
//private static WalletManager  walletManager   = null;
private static GlobeUtil globeUtil;
private static TouchMobileUtil tmUtil;

private static String GHP_NUMRANGE  = null;
private static String TM_NUMRANGE   = null;
private static String SMART_NUMRANGE   = null;
private static String SUN_NUMRANGE   = null;

private static String AMAX_HOST     = null;
private static String AMAX_URI      = null;
private static String AMAX_USER     = null;
private static String AMAX_PASSWORD = null;

private static String dbContext;
private static String smsDbContext = null;

private static String AMAX_DEBUG = null;
private static String TOPUP_TYPE = null;

private final static String EPIN_SUCCESS = "EPIN SUCCESS"; 
private final static String EPIN_ERROR = "error";
private final static String  EPIN_INVALID_DENOM = "INVALID_DENOM";
private static final int EPIN_SUCCESS_CODE = 100;
private final static int EPIN_ERROR_CODE = 101; 
private final static int EPIN_INVALID_DENOM_CODE = 102;
private static final String appName = "P2MWEB_INTL";
private static String EPIN_DB_URL = null;
private static String EPIN_USER = null;
private static String EPIN_PASS = null;
private static String EPIN_INSTRUCTION1 = null;
private static String EPIN_INSTRUCTION2 = null;
private static DataSource epinDs;
private static String[] amaxDenoms;
private static String epinDbContext;

private InputStream ismLC = null;

private BufferedReader brReader = null;

private URL url;

private StringBuffer strMsg = new StringBuffer();
	
private static final Logger logger = Logger.getLogger(LoadCustomerServices.class);
	
	public ModelAndView loadCustomer(TopupModel topup, HttpSession usersession,ServletContext servletContext){
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ApplicationContext  smscontext = new ClassPathXmlApplicationContext("Spring-SMS.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		OutgoingSMSWriterDAO smsdao =  (OutgoingSMSWriterDAO)smscontext.getBean("smsDAO");

		ModelAndView success = new ModelAndView("loadcustomer");
		ModelAndView fail = new ModelAndView("loadcustomer");
		Map fillbox = dao.fillBox();
		Map fillprodtype = dao.fillprodtype();
		Float wallet = dao.getWallet(usersession);

		String pid = (String) usersession.getAttribute("PID");
		topup.setPid(pid);	
		String bid = (String) usersession.getAttribute("BID");
		topup.setBid(bid);
		
		
		topup.setTxtype("mobile");
		topup.setRequestype("load");
		
//		if(topup.getMessage().equals("")){
//			
//			topup.setMessage(dao.getCustomMesasge(usersession));
//		}

		
		if(topup.getProdtype().contains("load-")){
			
			int pos = topup.getProdtype().indexOf("-");
			
			topup.setAmount(topup.getProdtype().substring(pos+1));
			topup.setProdtype("LOAD");

			
			
		}
		
		Float topupamount = (float) 0;
		
		if(!topup.getProdtype().equalsIgnoreCase("LOAD"))
		
		{
			
			 topupamount = dao.getProductAmount(topup.getProdtype());
			 topup.setAmount(topupamount.toString().split("\\.", 2)[0]);
		}
		else
		{
			
			if(!this.isNumber(topup.getAmount())) {
				
				fail.addObject("status", "fail");
				fail.addObject("msg", "Please input valid amount.");
				fail.addObject("fillbox", fillbox);
				fail.addObject("fillprodtype", fillprodtype);
				//fail.addObject("producttype", prodtype);
				fail.addObject("currentwallet", wallet);
				fail.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info("Invalid Amount");
				return fail;
				
				
			}
			 
			 
			
			
			 topupamount = Float.parseFloat(topup.getAmount());
			 topup.setAmount(topupamount.toString().split("\\.", 2)[0]);
			 
			 
			 
			 if(topupamount >= 151 && topupamount <= 349){
				 
					fail.addObject("status", "fail");
					fail.addObject("msg", "Please input valid amount.");
					fail.addObject("fillbox", fillbox);
					fail.addObject("fillprodtype", fillprodtype);
					//fail.addObject("producttype", prodtype);
					fail.addObject("currentwallet", wallet);
					fail.addObject("user",usersession.getAttribute("USERLEVEL"));
					logger.info("Invalid Amount");
					return fail;
				 
				 
			 }
		}
	
		logger.info("Product type: "+topup.getProdtype());
		logger.info("Amount: "+topup.getAmount());
		

		
		
		if(!this.isNumber(topup.getMobnum())){
			
			fail.addObject("status", "fail");
			fail.addObject("msg", "Please Input Valid Mobile number");
			fail.addObject("fillbox", fillbox);
			fail.addObject("fillprodtype", fillprodtype);
			//fail.addObject("producttype", prodtype);
			fail.addObject("currentwallet", wallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info("Invalid Mobile Number");
			return fail;
			
		}


		
		if(topupamount>wallet)
		{
			
		}
		else

		
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
		TOPUP_TYPE  = servletContext.getInitParameter("TOPUP_TYPE");
			
		String mobile = null;
		int errorState = 0;

		Connection conn = null;
		Connection sms_conn = null;
		
		PartnerProfile partner = null;

		int serviceType = 0;
		
		mobile = topup.getPrefix() + topup.getMobnum();
		
		
		
		
		try {
			int walletid = dao.getWalletid(usersession, "AMAX");
			logger.info("get walletid: " + walletid);
			logger.info("get profile: " + usersession.getAttribute("USER"));
			partner = profiledao.GetProfilebyUsername(usersession.getAttribute("USER").toString());
			topup.agentid = partner.agentid;
			topup.txid = dao.insertTransaction(topup,usersession,walletid);
			logger.info("topup tx id is: "+topup.txid);
			logger.info("partner topup tx id is: "+topup.ptxid);
		} catch (Exception e) {
			e.printStackTrace();	
			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
			mobile = null;
			topup = null;
			Float currwallet = dao.getWallet(usersession);
			fail.addObject("status", "fail");
			fail.addObject("msg", "Profile Not Found!");
			fail.addObject("fillbox", fillbox);
			fail.addObject("fillprodtype", fillprodtype);
			//fail.addObject("producttype", prodtype);
			fail.addObject("currentwallet", currwallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info(P2MConstants.getMessage(errorState));
			closeconn(conn, sms_conn);
			return fail;
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
		

		if ((partner.wallet <= 0) || (Float.parseFloat(topup.getAmount()) <= 0) || ( partner.wallet < Float.parseFloat(topup.getAmount()) )) {
			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}

		if ( errorState != 0 ) {
			
			dao.updateTransaction(topup.txid,topup.ptxid,errorState,"0",usersession);
			mobile = null;
			topup = null;
			Float currwallet = dao.getWallet(usersession);
			fail.addObject("status", "fail");
			fail.addObject("msg", P2MConstants.getMessage(errorState));
			fail.addObject("fillbox", fillbox);
			//fail.addObject("producttype", prodtype);
			fail.addObject("currentwallet", currwallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info(P2MConstants.getMessage(errorState));
			closeconn(conn, sms_conn);
			return fail;
		}
		Wallet_Transaction_Information wallettransactioninfo= new Wallet_Transaction_Information(usersession.getAttribute("PID").toString(), topup.ptxid.toString(), String.valueOf(topup.txid),usersession.getAttribute("walletid").toString());
		
		TopUpResponse resp = null;
		int debit = 0;
		String session = null;
		boolean loginsuccess = false;
		
			
			if(partner.runmode.equalsIgnoreCase("TEST"))
			
			{
				
				String paymenttype = (String) usersession.getAttribute("paymenttype");
				
				if(paymenttype.equalsIgnoreCase("PREPAID"))
				{
					
					dao.deductWallet(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));

					
					logger.info("deducting prepaid wallet done");
				}
				else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
					
					dao.creditWallet(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
					
					logger.info("deducting settlement done");
					
				}
				else if(paymenttype.equalsIgnoreCase("FEES")){
					
					dao.creditWallet(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
					
					logger.info("deducting fees wallet done");
				}
				
				
			//	dao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()) * -1);
				
				resp = new TopUpResponse("TestTestTest", 0, 0, "1234567");
				
				
				if(resp != null)
				{
					
					
					errorState = resp.getTopupResponseCode();
					
				}
				
				try {
					logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState + " = " + topup.getProdtype());
					
					if ( resp != null ) {
						
						
						dao.updateTransactionTest(topup.txid,topup.ptxid,errorState,resp.getTransactionID(),usersession);
			
						if(errorState == 0){
							dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,AMAXConstants.getMessage(0), resp.getTransactionID());
						}else if(errorState == EPIN_SUCCESS_CODE){
							dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,AMAXConstants.getMessage(0), resp.getTransactionID());
						}
					
					}    	
					
					DecrementationBean bean = dao.getDecrementation(topup.getProdtype(), usersession.getAttribute("PID").toString(), Float.parseFloat(topup.getAmount()));
//					int decrementation = dao.getDecrementation(pid);
					logger.info("Checking decrementation permission....");
					
					if(bean!= null)
					{
						
						
						logger.info("Computation of Decrementation Commission will apply");
//						
						Float commission = bean.getCommission();
//						Float divisor = (float) 100;
//						Float decreamount = Float.parseFloat(topup.getAmount()) * (commission/(divisor));
						
						BigDecimal com = new BigDecimal (commission+"");
						BigDecimal div = new BigDecimal (100);
						BigDecimal amt = new BigDecimal (topup.getAmount());
						BigDecimal decreamount = amt.multiply(com.divide(div));
						
						logger.info("Decremented Value is:"+decreamount);
						
						int returnCommission = dao.updatetxid(usersession, topup.ptxid, decreamount.floatValue());
						
						if(returnCommission>0){
							dao.getWalletBalances(wallettransactioninfo);
							dao.updateTransactionDecre(topup.txid,topup.ptxid, decreamount.floatValue(),wallettransactioninfo,usersession);
							
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
				
					//debit = dao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()) * -1);
					
					String paymenttype = (String) usersession.getAttribute("paymenttype");
					
					if(paymenttype.equalsIgnoreCase("PREPAID"))
					{
						
						//debit = dao.deductWallet(usersession, topup.txid, Float.parseFloat(topup.getAmount()));
						debit = dao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()) * -1);
						
						
						logger.info("deducting prepaid wallet done");
					}
					else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
						
						debit = dao.creditWallet(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
					
						
						logger.info("deducting settlement done");
						
					}
					else if(paymenttype.equalsIgnoreCase("FEES")){
						
						debit = dao.creditWallet(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
						
						
						logger.info("deducting fees wallet done");
					}
					
				logger.info("Debit result: " + debit);
				logger.info("+++++++++++++++++++AMAX TEST++++++++++++++++" + mobile);
				
				if (debit != 1) {
					logger.info("Unable to deduct from wallet from  " + partner.partnerid + "; Amount: " + topup.getAmount());	
					errorState = P2MConstants.DEBIT_FAILED_CODE;				
				}
				
				else		
				{
					dao.updateTransaction(topup.txid,topup.ptxid,63,"0",usersession);

					logger.info("tx id: " + topup.txid);
					logger.info("+++++++++++++++++++AMAX IS ON, try to connect to AMAX++++++++++++++++");

						AutoloadMax amax = new AutoloadMax(AMAX_HOST, AMAX_URI);
						
						try 
						{
							if (!AMAX_DEBUG.equals("SESSION"))
								session = amax.createSession();
							else
								throw new Exception("session amax debug");
							logger.info("AMAX Session creation result: " + session);
						} catch (Exception e) 
						{ 
							   session = null;
							   logger.info("Error in Session Amax: " + e.getMessage());
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
							   logger.info("Error in  Amax Login: "+e.getMessage());
							   loginsuccess = false;
							   resp = null;
							} 
										
							if(loginsuccess){
						
								try {
									
								//dao.updateTransaction(topup.txid,62,session);
								dao.updateTransaction(topup.txid,topup.ptxid,62,session,usersession);
								logger.info("++++Request Topup++++mobile:"+mobile+", amount:"+topup.getAmount());
								
									
									if (!AMAX_DEBUG.equals("TOPUP"))
									{
										
										
										if(topup.getProdtype().equalsIgnoreCase("LOAD")){
											
											topup.setProdtype("LD");
										}
										
										if(TOPUP_TYPE.equals("AMAX")){
											resp = amax.requestTopUp(session, mobile, Integer.parseInt(topup.getAmount()), "",topup.getProdtype(),"PC2MWEB");
										}else{
											resp = new HandleGcash2Load().processGCash2LoadAmax(session, mobile, Integer.parseInt(topup.getAmount()), "","PC2MWEB");
										}
							
										 
									}	 
									 else
										 throw new Exception("topup amax debug");
									} catch (Exception e) {
										logger.info("Error in Topup Amax: "+e.getMessage());
									    resp = null;
									} 
							
							
							if ( resp != null ) 
							{
								
								errorState = resp.getTopupResponseCode();
							
									switch (errorState) 
									{
									
										case AMAXConstants.TOPUP_SUCCESSFUL_CODE:
						
						
											logger.info("++++++++++++++++Top up successful in AMAX+++++++++++++++++++++");
											logger.info("Topup transaction " + topup.txid + ": " + "AMAX Response= " + errorState + "; Trace= " + resp.getTransactionID());

											logger.info(topup.getMessage());
											
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
											
											float amount = Float.parseFloat(topup.getAmount());

											
											DecrementationBean bean = dao.getDecrementation(topup.getProdtype(), usersession.getAttribute("PID").toString(), amount);
//											int decrementation = dao.getDecrementation(pid);
											logger.info("Checking decrementation permission....");
											
											if(bean != null)
											{
												
												logger.info("Computation of Decrementation Commission will apply");
												
//												Float commission = bean.getCommission();
//												Float divisor = (float) 100;
//												Float decreamount = amount * (commission/(divisor));
												
												Float commission = bean.getCommission();
//												Float divisor = (float) 100;
//												Float decreamount = Float.parseFloat(topup.getAmount()) * (commission/(divisor));
												
												BigDecimal com = new BigDecimal (commission+"");
												BigDecimal div = new BigDecimal (100);
												BigDecimal amt = new BigDecimal (topup.getAmount());
												BigDecimal decreamount = amt.multiply(com.divide(div));
												
												logger.info("Decremented Value is:"+decreamount);
												
											//	int returnCommission = dao.creditWallet(usersession, topup.txid, topupamount);
												int returnCommission = dao.updatetxid(usersession, topup.ptxid, decreamount.floatValue());
										
												logger.info("Returning Comission status: "+returnCommission);
												
												if(returnCommission>0){
													
													int decre = dao.updateTransactionDecre(topup.txid, topup.ptxid, decreamount.floatValue(),wallettransactioninfo,usersession);
													logger.info("Updating Transaction status: "+decre);
													
												}
												
												
											}

											break;
											
				
										
										default:
											logger.info("+++++++++++++++TOP UP RESPONSE CODE FAILED+++++++++++++");
											// Refund wallet
											if ( debit == 1 ) 
											{
												 //walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
												dao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
											}
											
										break;													
									}						
							}
							else
							{
								// no revert wallet if topup unsuccessful
								logger.info("+++++++++AMAX TOP UP ERROR +++++++++++++");
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
									dao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
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
									dao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
								}
												
							}	
						

					
					
		
			
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
				
				// Refund wallet
				if ( debit == 1 ) {
				//	walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
					dao.updatetxid(usersession, topup.ptxid, Float.parseFloat(topup.getAmount()));
				}
			}
			try {
				logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState);
				
				if ( resp != null ) {
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,resp.getTransactionID(),usersession);
				//	dao.updatetxid(usersession, topup.txid, Integer.parseInt(topup.getAmount()),partner.paymenttype);
					
					if(errorState == 0){
						dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,AMAXConstants.TOPUP_SUCCESSFUL_MSG, resp.getTransactionID());
					}else if(errorState == EPIN_SUCCESS_CODE){
						dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,AMAXConstants.TOPUP_SUCCESSFUL_MSG, resp.getTransactionID());
					}
				
				}    	
				
				else  if (session == null){
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,"17",usersession);
				}
				else 
				{
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,session,usersession);
				}
				

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
				sms_conn = null;
				
			}
				
				
				
				
			}
			
	
		
		if ( resp != null ) 
		{ 
			if(errorState == 0)
			{
				Float currwallet = dao.getWallet(usersession);
				success.addObject("status","success");
				success.addObject("msg", pc2mwebFunc.AmaxgetMessage(errorState)+" Trace Number is: "+resp.getTransactionID());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(pc2mwebFunc.AmaxgetMessage(errorState));
				return success;
			}
			else if(errorState == 1 || errorState == 2 || errorState == 3 || errorState == 4 || errorState == 5 || errorState == 6)
			{
				Float currwallet = dao.getWallet(usersession);
	
				success.addObject("status","fail");
				success.addObject("msg", pc2mwebFunc.AmaxgetMessage(errorState));
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(pc2mwebFunc.AmaxgetMessage(errorState));
				return success;
			}else if (errorState == 100)
			{
				Float currwallet = dao.getWallet(usersession);
	
				success.addObject("status","success");
				success.addObject("msg","Epins dispense successful! Trace Number is: "+resp.getTransactionID());
				success.addObject("fillbox", fillbox);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				//logger.info(AMAXConstants.getMessage(errorState));
				return success;
			}else if (errorState == 101){
				Float currwallet = dao.getWallet(usersession);
	
				success.addObject("status","fail");
				success.addObject("msg","Epins dispense error! Trace Number is: "+resp.getTransactionID());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);;
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				//logger.info(AMAXConstants.getMessage(errorState));
				return success;
			}else if (errorState == 102){
				Float currwallet = dao.getWallet(usersession);

				success.addObject("status","fail");
				success.addObject("msg","Epins dispense invalid denom! Trace Number is: "+resp.getTransactionID());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				//logger.info(AMAXConstants.getMessage(errorState));
				return success;
			}
			
			else 
			{
			Float currwallet = dao.getWallet(usersession);

			fail.addObject("status", "fail");
			fail.addObject("msg", pc2mwebFunc.P2MConstantsgetMessage(errorState));
			fail.addObject("fillbox", fillbox);
			fail.addObject("fillprodtype", fillprodtype);
			fail.addObject("currentwallet", currwallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info(pc2mwebFunc.P2MConstantsgetMessage(errorState));
			return fail;
			}
			
			

		}
		Float currwallet = dao.getWallet(usersession);

		fail.addObject("status", "fail");
		fail.addObject("msg", "Operation Failed.");
		fail.addObject("fillbox", fillbox);
		fail.addObject("fillprodtype", fillprodtype);
		fail.addObject("currentwallet", currwallet);
		fail.addObject("user",usersession.getAttribute("USERLEVEL"));
		logger.info(pc2mwebFunc.P2MConstantsgetMessage(errorState));
		return fail;
		
		
	}
	
	
	
    public boolean isNumber( String tx )  
    {  
       try  
       {  
          Integer.parseInt( tx );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }

	
	
	private void closeconn(Connection conn, Connection sms_conn)
	{
		try {
			if (conn != null)	
				conn.close();
			if (sms_conn != null)
				sms_conn.close();
		}
		catch (Exception exclose)
		{
			
		}

	}
	
	
}
