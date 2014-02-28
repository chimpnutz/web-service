//package com.pc2mweb.controller;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//import com.paysetter.amax.AMAXConstants;
//import com.paysetter.amax.TransferStocksConstant;
//import com.paysetter.commons.pctomobile.P2MConstants;
//import com.paysetter.commons.pctomobile.PartnerProfile;
//import com.paysetter.commons.pctomobile.ProfileManager;
//import com.paysetter.topup.amax.AutoloadMax;
//import com.paysetter.topup.amax.response.StockTransferResponse;
//import com.paysetter.util.GlobePrepaid.GlobeUtil;
//import com.paysetter.util.GlobePrepaid.TouchMobileUtil;
//import com.pc2mweb.beans.MessageBean;
//import com.pc2mweb.beans.RetailerSimBean;
//import com.pc2mweb.beans.TransactionIDObject;
//import com.pc2mweb.beans.UserBean;
//
//import com.pc2mweb.dao.transactions.LoginDAO;
//import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
//import com.pc2mweb.dao.transactions.TopupDAO;
//import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
//import com.pc2mweb.dao.transactions.TransfertoBranchDAO;
//import com.pc2mweb.dao.transactions.TransfertoMotherDAO;
//import com.pc2mweb.dao.transactions.TransfertoRetailerDAO;
//import com.pc2mweb.dao.transactions.UserManagementDAO;
//import com.pc2mweb.dao.transactions.ViewAccountDAO;
//import com.pc2mweb.dao.transactions.WalletTransferDAO;
//import com.pc2mweb.model.LoginModel;
//import com.pc2mweb.model.OutgoingSMSModel;
//import com.pc2mweb.model.TopupModel;
//import com.pc2mweb.model.TransactionHistoryModel;
//import com.pc2mweb.model.TransactionInquiryModel;
//import com.pc2mweb.model.TransactionReportsResultModel;
//import com.pc2mweb.model.TransferCreditsModel;
//import com.pc2mweb.model.TransfertoRetailerModel;
//import com.pc2mweb.model.UserManagementModel;
//import com.pc2mweb.model.WalletTransferModel;
//import com.pc2mweb.model.container.TransferCreditsListContainer;
//import com.pc2mweb.utility.function.pc2mwebFunc;
//
//@Controller
//@RequestMapping("transfercredits")
//public class CreditsTransferController {
//	
//	@Autowired
//	private ServletContext servletContext;
//	
//	private static final Logger logger = Logger.getLogger(CreditsTransferController.class);
//
//	private static DataSource ds;
//	private static DataSource SMSds;
//	private static ProfileManager profileManager  = null;
//	//private static WalletManager  walletManager   = null;
//	private static GlobeUtil globeUtil;
//	private static TouchMobileUtil tmUtil;
//	
//	private static String GHP_NUMRANGE  = null;
//	private static String TM_NUMRANGE   = null;
//	
//	private static String AMAX_HOST     = null;
//	private static String AMAX_URI      = null;
//	private static String AMAX_USER     = null;
//	private static String AMAX_PASSWORD = null;
//	
//	private static String dbContext;
//	private static String smsDbContext = null;
//	
//	private static String AMAX_DEBUG = null;
//	
//	@RequestMapping(method = RequestMethod.GET)
//	 public ModelAndView WalletView(HttpServletRequest request,HttpSession userssession) {
//		
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		WalletTransferDAO dao = (WalletTransferDAO)context.getBean("wallettransferDAO");
//		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
//		TopupDAO tDao = (TopupDAO)context.getBean("topupDAO");
//		
//		ModelAndView modelAndView = new ModelAndView("transfercredits");
//		ModelAndView redirect = new ModelAndView("redirect:main.html");
//		
//		HttpSession isSession = request.getSession();
//
//		if (null == isSession.getAttribute("USER")) {				
//				
//				redirect.addObject("login", "no");
//				return redirect;	
//			
//		} else {
//			
//				List<WalletTransferModel> partnerlist = dao.getPartners(userssession);
//				List<WalletTransferModel> retailers = retailerDao.getInfo(userssession);
//				List<MessageBean>  wallet = dao.getWallet(userssession);
//				Float  partnerwallet = dao.getPartnerWallet(userssession);
//				Float myWallet = tDao.getWallet(userssession);
//				
//				modelAndView.addObject("myWallet", myWallet);
//				modelAndView.addObject("partners", partnerlist);
//				modelAndView.addObject("retailers", retailers);
//				modelAndView.addObject("currentwallet", wallet);
//				modelAndView.addObject("partnerwallet", partnerwallet);
//				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
//				
//				if(partnerlist.isEmpty()){
//					
//					modelAndView.addObject("retailer", "null");
//					
//					return modelAndView;
//				}
//				
//				
//
//				return modelAndView;
//				
//				
//					
//		}	
//		
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//    public @ResponseBody String transfertoBranch(@RequestParam("amount") String txtamount,
//    		@RequestParam("branchid") String branchid, HttpSession usersession)
//    {
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		WalletTransferDAO dao = (WalletTransferDAO)context.getBean("wallettransferDAO");
//		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
//		TransfertoBranchDAO branchdao = (TransfertoBranchDAO)context.getBean("transfertobranchDAO");
//		
//		String pid = usersession.getAttribute("PID").toString();
//		 
//		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();
//		
//		TransactionIDObject obj = new TransactionIDObject();
//			
//		String amount = txtamount.trim();
//		
//	    if(!amount.equals("") && this.isNumber(amount) )
//	    {
//	    	
//	    logger.info("Request Transfer Amount of "+amount+ " to Branch id: "+branchid+" from Partner id: "+pid );
//	    BigDecimal amount2 = new BigDecimal(amount);
//	    BigDecimal currentPidwallet = branchdao.getpidWallet(pid);
//	    BigDecimal currentBidwallet = branchdao.getbidWallet(branchid);
//
//	    BigDecimal newpidwallet = null;
//	    BigDecimal newbidwallet = null;
//	                	            	
//	    if(currentBidwallet.signum() == -1)
//	    {
//	            			
//	     currentBidwallet = new BigDecimal(0);
//	     
//	    }
//	            		
//	         if((currentPidwallet.compareTo(amount2) >= 0))
//	         {
//	            			
//	            newpidwallet = currentPidwallet.subtract(amount2);
//	            newbidwallet = currentBidwallet.add(amount2);
//	            			
//	            	if(branchdao.transfertoBranch(pid, branchid, amount2,obj))
//	            	{
//	            				
//	            		branchdao.insertWalletHistory(branchid, pid, amount2, currentPidwallet, newpidwallet, currentBidwallet, newbidwallet,"TRANSFERTOBRANCH",obj);
//	            				
//	            		logger.info("Transfer Completed from Partner id: "+pid+ " to Branch id: "+branchid);	            		
//	            		logger.info("New Partner ID Wallet is: "+newpidwallet+", New Branch ID Wallet is: "+newbidwallet);
//
//	            				
//	            		return "Transfer Success.";
//	            			
//	            						
//	            	}
//	            	else
//	            	{
//	            				
//	            	logger.info("Transfer Fail.");
//	            	
//	            	return "Transfer Fail.";
//		            			
//	            							
//	            	}
//	            						
//	         }else
//	            	{
//	            			
//	            	logger.info("Insufficient Partner Wallet Balance");
//	        
//	            	return "Insufficient Wallet Balance";
//	            			          			
//	            	}
//	         
//	    }else if(!amount.equals("") && !this.isNumber(amount)){
//	    	
//	    	return "Invalid Amount";
//	    	
//	    }else
//	    	
//		return "";
//	                	
//	                	
//   }
//	
//	
//	@RequestMapping(method = RequestMethod.POST,params="mode=retrievefrBranch")
//    public @ResponseBody String retrievefrBranch(@RequestParam("amount") String txtamount,
//    		@RequestParam("branchid") String branchid, HttpSession usersession)
//    {
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		TransfertoMotherDAO motherdao = (TransfertoMotherDAO)context.getBean("transfertomotherDAO");
//		 
//		ModelAndView modelAndView = new ModelAndView("redirect:main.html");
//
//		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();
//
//		TransactionIDObject obj = new TransactionIDObject();
//		
//		String pid = usersession.getAttribute("PID").toString();	    
//		
//		 String amount = txtamount.trim();
//		  
//		 if(!amount.equals("") && this.isNumber(amount) )
//		   
//		 {
//	         
//			 logger.info("Request Transfer Amount of "+amount+ " to Partner id: "+pid+" from Branchid id: "+branchid );
//			 BigDecimal amount2 = new BigDecimal(amount.trim());	                			
//			 BigDecimal currentPidwallet = motherdao.getpidWallet(pid);	            		
//			 BigDecimal currentBidwallet = motherdao.getbidWallet(branchid);
//			 
//			 BigDecimal newpidwallet = null;	            		
//			 BigDecimal newbidwallet = null;
//   		
//			 if(currentBidwallet.signum() == -1){
//	
//				 currentBidwallet = new BigDecimal(0);
//	            		
//			 }
//
//			 if(currentBidwallet.compareTo(amount2) >= 0)       		
//			 {
//				 newpidwallet = currentPidwallet.add(amount2);	      		
//				 newbidwallet = currentBidwallet.subtract(amount2);        		
//				 if(motherdao.transfertoMother(pid, branchid, amount2,obj))
//				 {
//		
//					 motherdao.insertWalletHistory(branchid, pid, amount2, currentPidwallet, newpidwallet, currentBidwallet, newbidwallet,"TRANSFERTOMOTHER",obj);			
//					 
//					 logger.info("New Partner ID Wallet is: "+newpidwallet+ ", New Branch ID Wallet is: "+newbidwallet);
//	            				//logger.info("New Branch ID Wallet is: "+newbidwallet);	
//					 logger.info("Transfer Complete!");
//					 return "Transfer Success.";
//
//				 }else
//				 {
//	            				
//					 logger.info("Transfer Fail.");      	
//					 return "Transfer Fail.";
//		
//				 }
//	            						
//			 }else
//	            		
//			 {
//	            			            			
//				 logger.info("Insufficient Branch Wallet Balance");
//				 return "Insufficient Wallet Balance";
//     		
//			 }               	
//	                	
//		    }else if(!amount.equals("") && !this.isNumber(amount)){
//		    	
//		    	return "Invalid Amount";
//		    	
//		    }else
//		    	
//			return "";      
// 
//   }
//	
//	@RequestMapping(method = RequestMethod.POST,params="mode=transfertoRetailer")
//    public @ResponseBody String transfertoRetailer(@RequestParam("amount") String amount,
//    		@RequestParam("mobileno") String mobileno, HttpSession usersession) throws NamingException
//    {
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
//		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
//		RetailerSimBean bean = new RetailerSimBean();
//		
//		TransfertoRetailerModel topup = new TransfertoRetailerModel();
//		
////		int debit = 0;
////		
////		if(!amount.equals(""))
////		{
////			
////			if(retailerDao.getSimInfo(mobileno,bean))
////			{
////			
////				logger.info("simBid " +bean.bid);
////				logger.info("simPID " +bean.pid);
////				logger.info("simMsidn "+bean.msisdn);
////				logger.info("simType "+bean.paymenttype);
////				
////				debit = retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(amount) * -1,"", bean);	
////				
////				logger.info("debit "+debit);
////				
////				if(debit == 0){
////					return "";
////				}else{
////					return "SUCCESS";
////				}
////
////			}
////			
////		}
//	
//		
//		String pid = (String) usersession.getAttribute("PID");
//		topup.setPid(pid);	
//		String bid = (String) usersession.getAttribute("BID");
//		topup.setBid(bid);
//
//		topup.setMsisdn(mobileno);
//		topup.setAmount(amount.trim());
//
//		
//		dbContext = servletContext.getInitParameter("DatabaseContext");
//		smsDbContext = servletContext.getInitParameter("SMSDatabaseContext");
//		
//		Context initCtx = new InitialContext();
//		Context envCtx  = (Context)initCtx.lookup("java:comp/env");
//		ds = (DataSource) envCtx.lookup(dbContext);
//		SMSds = (DataSource) envCtx.lookup(smsDbContext);
//		
//		GHP_NUMRANGE = servletContext.getInitParameter("GHP_NUMRANGE");
//		TM_NUMRANGE  = servletContext.getInitParameter("TM_NUMRANGE");
//
//		try {
//			globeUtil = new GlobeUtil(new FileInputStream(GHP_NUMRANGE));
//			tmUtil    = new TouchMobileUtil(new FileInputStream(TM_NUMRANGE));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		AMAX_DEBUG  = servletContext.getInitParameter("DEBUG_AMAX");
//		AMAX_HOST =	servletContext.getInitParameter("AMAX_HOST");
//		AMAX_URI =	servletContext.getInitParameter("AMAX_URI");
//		AMAX_USER =	servletContext.getInitParameter("AMAX_USER");
//		AMAX_PASSWORD =	servletContext.getInitParameter("AMAX_PASSWORD");
//		
//		String mobile = null;
//		int errorState = 0;
//
//		Connection conn = null;
//		Connection sms_conn = null;
//		PartnerProfile partner = null;
//
//		int serviceType = 0;
//		
//		mobile = topup.getMsisdn();
//		logger.info("check retailer sim: " + topup.getMsisdn() + ", topup amount: " + topup.getAmount());
//		
//		if(!amount.equals("") && !this.isNumber(amount)){
//	    	
//	    	return "Invalid Amount";
//	    	
//	    }else
//	    	
//	
//		if(topup.getAmount().equals(""))
//		{
//			
//			return "";
//			
//		}
//		
//		if(!retailerDao.getSimInfo(topup.getMsisdn(),bean)){
//			
//			errorState = P2MConstants.AGENT_PERMISSION_ERROR_CODE;
//			logger.info(P2MConstants.getMessage(errorState));
//			mobile = null;
//			closeconn(conn,sms_conn);
//			return P2MConstants.getMessage(errorState).toString();
//			
//		}
//		
//		
//		
//		try {
//			conn = ds.getConnection();
//			sms_conn = SMSds.getConnection(); 
//			profileManager = new ProfileManager(conn);
//			//walletManager  = new WalletManager(conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//			//throw new ServletException("Cannot open required connections");
//		}
//		
//
//		
////		int debit = 0;
////		
////		if(!amount.equals(""))
////		{
////			
////			if(retailerDao.getSimInfo(mobileno,bean))
////			{
////			
////				logger.info("simBid " +bean.bid);
////				logger.info("simPID " +bean.pid);
////				logger.info("simMsidn "+bean.msisdn);
////				logger.info("simType "+bean.paymenttype);
////				
////				debit = retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(amount) * -1,"", bean);	
////				
////				logger.info("debit "+debit);
////				
////				if(debit == 0){
////					return "";
////				}else{
////					return "SUCCESS";
////				}
////
////			}
////			
////		}
//		
//		
//		logger.info("simBid: " +bean.bid +",simPID: "+bean.pid +",simNumber: "+bean.msisdn+",simPaymenttype: "+bean.paymenttype);
//
//		try {
//			partner = profileManager.GetProfile((String) usersession.getAttribute("USER"));
//			topup.agentid = partner.agentid;
//			topup.txid = dao.insertTransaction(topup, conn);
//			logger.info("topup tx id is: "+topup.txid + ",User agent id: " + partner.agentid + ",User bid: " + partner.branchid + ",User pid: " + partner.partnerid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
//			logger.info(P2MConstants.getMessage(errorState));
//			mobile = null;
//			topup = null;
//			closeconn(conn,sms_conn);
//			return  pc2mwebFunc.P2MConstantsgetMessage(errorState);
//		}
//		
//		// Check if TM or Globe for EPIN HANDLING
//		if (globeUtil.isPrepaidNumber(mobile)) {
//			serviceType = 1;
//		} else if (tmUtil.isPrepaidNumber(mobile)) {
//			serviceType = 2;
//		}
//		
//		if ( !(globeUtil.isPrepaidNumber(mobile) || tmUtil.isPrepaidNumber(mobile)) ) {
//			errorState = P2MConstants.NUMBER_NOT_IN_RANGE_CODE;
//			logger.info(P2MConstants.getMessage(errorState));
//		}
//		
//		logger.info("check balance :  " + partner.agentid + "," + partner.branchid + "," + partner.partnerid + "," + partner.wallet + "," + topup.getAmount() +"," + bean.pid + "," + bean.bid);
//		
//		String wallettype = "own_wallet";
//		
//		if (bean.pid.equalsIgnoreCase(partner.partnerid) && !bean.bid.equalsIgnoreCase(partner.branchid))
//		{
//			wallettype="partner_wallet";
//		}
//		else if (!bean.pid.equalsIgnoreCase(partner.partnerid)) 
//		{
//			// error
//			wallettype="error";
//			errorState = P2MConstants.NUMBER_NOT_IN_RANGE_CODE;
//			logger.info(P2MConstants.getMessage(errorState));
//		}
//		
//		if (wallettype.equals("own_wallet"))
//		{
//			if ((partner.wallet <= 0) || (Integer.parseInt(topup.getAmount()) <= 0) || ( partner.wallet < Integer.parseInt(topup.getAmount()) )) 
//			{
//				logger.info("own wallet insufficient balance : " + partner.partnerid + "," + partner.agentid + "," + partner.wallet);
//				errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
//				logger.info(P2MConstants.getMessage(errorState));
//			}
//		}
//		else if (wallettype.equals("partner_wallet"))
//		{
//			ViewAccountDAO vdao = (ViewAccountDAO)context.getBean("viewaccountDAO");
//			
//			List<WalletTransferModel> waltransmodel = vdao.getPartnerInfo(usersession, partner.branchid);
//			
//			if (waltransmodel.isEmpty())
//			{
//				logger.info("partner wallet insufficient balance (empty) : " + partner.partnerid + "," + partner.agentid + "," );
//				errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
//				logger.info(P2MConstants.getMessage(errorState));
//			}
//			else {
//				
//				Float partnerwallet = waltransmodel.get(0).getPartnerwallet();
//				float fpartnerwallet = Float.parseFloat(partnerwallet.toString());
//				if ((fpartnerwallet <= 0) || (Integer.parseInt(topup.getAmount()) <= 0) || ( fpartnerwallet < Integer.parseInt(topup.getAmount()) )) {
//					logger.info("partner wallet insufficient balance : " + partner.partnerid + "," + partner.agentid + "," + partnerwallet);
//					errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
//					logger.info(P2MConstants.getMessage(errorState));
//				}
//					
//			}
//			
//		}
//		else {
//			logger.info("invalid PID/BID retailer SIM vs logged in user : " + partner.agentid + "," + partner.partnerid + "," + partner.branchid + "," + bean.pid + "," + bean.bid );
//			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
//			logger.info(P2MConstants.getMessage(errorState));
//		}
//		
//		
//		
//		if ( errorState != 0 ) {
//			dao.updateTransaction(topup.txid,errorState,"0", conn);
//			logger.info(P2MConstants.getMessage(errorState));
//			mobile = null;
//			topup = null;
//			closeconn(conn,sms_conn);
//			return P2MConstants.getMessage(errorState).toString();
//		}
//		
//		StockTransferResponse resp = null;
//		int debit = 0;
//		String session = null;
//		boolean loginsuccess = false;
//		
//		try {
//
//			debit = retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()) * -1,partner.paymenttype, bean);	
//			
//			logger.info("Debit result: " + debit);
//			logger.info("+++++++++++++++++++AMAX TEST++++++++++++++++" + mobile);
//		
//			
//			if (debit != 1) {
//				logger.info("Unable to deduct from wallet from  " + partner.partnerid + partner.branchid + "; Amount: " + topup.getAmount());	
//				errorState = P2MConstants.DEBIT_FAILED_CODE;	
//				
//			}
//			
//			else		
//			{
//				logger.info("tx id: " + topup.txid);
//				dao.updateTransaction(topup.txid,63,"0", conn);
//				logger.info("+++++++++++++++++++AMAX IS ON, try to connect to AMAX++++++++++++++++");
//				AutoloadMax amax = new AutoloadMax(AMAX_HOST, AMAX_URI);
//				
//				try {
//					if (!AMAX_DEBUG.equals("SESSION"))
//						session = amax.createSession();
//					else
//						throw new Exception("session amax debug");
//					logger.info("AMAX Session creation result: " + session);
//				} catch (Exception e) { 
//					   session = null;
//					   //errorState = P2MConstants.NETWORK_ERROR_SESSION_CODE; 
//					   //logger.info(P2MConstants.getMessage(errorState));
//					   logger.info("Error in Session Amax " + e.getMessage());
//				}
//				
//					if ( session != null ) 
//					{
//						
//						try {					   
//						   logger.info("+++++++++++++++++++Login to Amax p2mweb_domestic+++++++++");
//						   if (!AMAX_DEBUG.equals("LOGIN")){
//							   amax.login(session, AMAX_USER, AMAX_PASSWORD);
//							   loginsuccess = true;
//						   } else
//							   throw new Exception("login amax debug");	   
//						} catch (Exception e) {
//						   logger.info("Error in  Amax Login");
//						   loginsuccess = false;
//						   resp = null;
//						} 
//						
//						if(loginsuccess){
//					
//							try {
//								dao.updateTransaction(topup.txid,62,session, conn);
//								 if (!AMAX_DEBUG.equals("TOPUP"))
//
//								 	 resp = amax.requestStockTransfer(session, mobile, Integer.parseInt(topup.getAmount()), "","");
//									 //resp = amax.requestStockTransfer(session, "20", Integer.parseInt(topup.getAmount()), "");
//								 else
//									 throw new Exception("topup amax debug");
//								} catch (Exception e) {
//									logger.info("Error in Topup Amax");
//								    resp = null;
//								} 
//						
//						
//						if ( resp != null ) 
//						{
//							
//							errorState = resp.getTransferResultCode();
//							
//							logger.info("Stock Transfer Response: "+resp.getTransferResultCode());
//							
//								switch (errorState) 
//								{
//								
//									case TransferStocksConstant.TRANSFER_STOCK_APPROVE_CODE:
//					
//										logger.info("++++++++++++++++Transfer Stocks Successful in AMAX+++++++++++++++++++++");
//										logger.info("Transfer Stocks Transaction " + topup.txid + ": " + "AMAX Response= " + errorState + "; Trace= " + resp.getTransferTransactionID());
//
//										OutgoingSMSModel SMSout = new OutgoingSMSModel();										
//										OutgoingSMSWriterDAO SMSwriter = new OutgoingSMSWriterDAO();
//										
//										SMSout.setSender("2808");
//										SMSout.setRecipient(mobile);
//										//SMSout.setSmsData(topup.getMessage());
//										SMSout.setTariffClass("");
//										SMSout.setServiceType("");
//																			
//										try {
//											if ( sms_conn == null ) {
//												sms_conn = SMSds.getConnection();
//											}
//											SMSwriter.insertSmsSent(sms_conn, SMSout);
//										} catch (Exception e) {
//											e.printStackTrace();
//											logger.info("***** Failed to insert SMS *****");
//											
//										} finally {
//											SMSout = null;
//											SMSwriter = null;
//										}
//										
//										break;
//										
//									case TransferStocksConstant.NO_PERMISSION_CODE:
//										
//										errorState = P2MConstants.NETWORK_ERROR_SESSION_CODE;
//										if ( debit == 1 ) 
//										{
//										    //walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
//											retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()),partner.paymenttype, bean);	
//										}
//										
//									break;
//									
//									default:
//										logger.info("+++++++++++++++TRANSFER STOCKS CODE FAILED+++++++++++++");
//									
//										
//										// Refund wallet
//										if ( debit == 1 ) 
//										{
//											 //walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
//											retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()),partner.paymenttype, bean);	
//										}
//									break;													
//								}						
//						}
//						else
//						{
//							// no revert wallet if topup unsuccessful
//							logger.info("+++++++++AMAX TRANSFER ERROR+++++++++++++");
//							errorState = P2MConstants.AMAX_REQUEST_TOPUP_ERROR_CODE;
//						}
//							
//						}
//						else
//						{
//							
//							logger.info("+++++++++ERROR AMAX LOGIN+++++++++++++");
//							errorState = P2MConstants.AMAX_LOGIN_ERROR_CODE;
//							if ( debit == 1 ) 
//							{
//								//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
//								retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()),partner.paymenttype, bean);	
//							}
//							
//						}
//				
//						}
//						else
//						{
//							
//							logger.info("+++++++++ERROR CREATING SESSION +++++++++++++");
//							errorState = P2MConstants.NETWORK_ERROR_SESSION_CODE;
//							if ( debit == 1 ) 
//							{
//								//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
//								retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()),partner.paymenttype, bean);	
//							}
//											
//						}	
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info("exception : " + e.getMessage());
//			errorState = P2MConstants.GENERAL_ERROR_CODE;
//			
//			// Refund wallet
//			if ( debit == 1 ) {
//				//walletManager.AddWallet(partner.partnerid, partner.branchid, Integer.parseInt(topup.getAmount()));
//				retailerDao.updatetxid(usersession, topup.txid, Float.parseFloat(topup.getAmount()),partner.paymenttype, bean);	
//			}
//		}
//		try {
//			logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState);
//			
//			if ( resp != null ) {
//					
//				dao.updateTransaction(topup.txid,errorState,resp.getTransferTransactionID()+"", conn);
//				
//				if(errorState == 0)
//				{
//					
//					retailerDao.insertRetailSimTransferLogs(usersession,mobile,topup.txid+"",AMAXConstants.getMessage(0),resp.getTransferTransactionID()+"",Integer.parseInt(topup.getAmount()));
//				
//				}
//				
//				
//				
//			}  else  if (session == null){
//				dao.updateTransaction(topup.txid,errorState,"17", conn);
//			}
//			else 
//			{
//				dao.updateTransaction(topup.txid,errorState,session, conn);
//			}
//			
//			
//			conn.close();
//			sms_conn.close(); 
//		
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			conn = null;
//			sms_conn = null;
//			
//		}
//		
//		if ( resp != null ) { 
//			logger.info(pc2mwebFunc.AmaxgetMessage(errorState)+" for mobile phone number: "+mobile);
//			return pc2mwebFunc.AmaxgetMessage(errorState).toString();
//		}else {
//	
//			logger.info(pc2mwebFunc.P2MConstantsgetMessage(errorState)+ "for mobile phone number: "+mobile);
//			return pc2mwebFunc.P2MConstantsgetMessage(errorState).toString();
//		}
//			 
//   }
//	
//	
//	
//	@RequestMapping(method = RequestMethod.POST,params="mode=validUser")
//    public @ResponseBody String checkPassword(@RequestParam("pw") String password,
//    		HttpSession usersession)
//    {
//		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		TransfertoMotherDAO motherdao = (TransfertoMotherDAO)context.getBean("transfertomotherDAO");
//		 
//		LoginDAO logindao = (LoginDAO) context.getBean("loginDAO");
//		
//		ModelAndView modelAndView = new ModelAndView("redirect:main.html");
//
//		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();
//
//		TransactionIDObject obj = new TransactionIDObject();
//		
//		String pid = usersession.getAttribute("PID").toString();	
//		String uname = usersession.getAttribute("USER").toString();
//		
//		
//		UserBean bean = logindao.login(uname, password);
//		
//		if(bean!=null){
//			
//			return "SUCCESS";   
//			
//		}else
//
//			return "FAIL";
//		
//}
//	
//	
//	
//	public void setServletContext(ServletContext context) {
//		this.servletContext = context;		
//	}
//
//
//	
//    public boolean isNumber( String tx )  
//    {  
//       try  
//       {  
//          Float.parseFloat( tx );  
//          return true;  
//       }  
//       catch( Exception e )  
//       {  
//          return false;  
//       }  
//    }  
//    
//	private void closeconn(Connection conn, Connection sms_conn)
//	{
//		try {
//			if (conn != null)	
//				conn.close();
//			if (sms_conn != null)
//				sms_conn.close();
//		}
//		catch (Exception exclose)
//		{
//			
//		}
//
//	}
//	
//	@ExceptionHandler()
//    public ModelAndView iHandleExceptions(Exception e) {
//        //do loads of interesting stuff to deal with the exception
//		
//		ModelAndView modelAndView = new ModelAndView("error");
//		
//		modelAndView.addObject("error","yes");
//		
//		logger.info(e.getMessage());
//		
//        return modelAndView;
//    }
//
//
//	
//	
//	
//}
