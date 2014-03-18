	package com.pc2mweb.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import com.paysetter.amax.AMAXConstants;
import com.paysetter.commons.pctomobile.P2MConstants;
import com.paysetter.commons.pctomobile.WalletManager;


import com.paysetter.topup.amax.AutoloadMax;
import com.paysetter.topup.amax.response.TopUpResponse;
import com.paysetter.topup.gcash.dao.HandleEpin;
import com.paysetter.topup.gcash.dao.HandleGcash;
import com.paysetter.topup.gcash.dao.HandleGcash2Load;
import com.paysetter.topup.gcash.response.GCMaxResponse;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;
import com.pc2mweb.beans.AMAXGCASHConstants;
import com.pc2mweb.beans.Agent;
import com.pc2mweb.beans.CredentialsBean;
import com.pc2mweb.beans.DecrementationBean;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.beans.RetStatus;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.beans.Wallet;
import com.pc2mweb.dao.transactions.EmergencyLoadDAO;
import com.pc2mweb.dao.transactions.LoginDAO;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransfertoMotherDAO;
import com.pc2mweb.model.AgentModel;
import com.pc2mweb.model.CreditModel;
import com.pc2mweb.model.LC;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.ResponseString;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.TransferCreditsModel;
import com.pc2mweb.model.Wallet_Transaction_Information;
import com.pc2mweb.utility.function.ProcessorUtil;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("cashincustomer")
public class GcashCashInController implements ServletContextAware  {
	
	@Autowired
	private ServletContext servletContext;
	
	private static DataSource ds;
	private static DataSource SMSds;
	private static ProfileManagerDAO profileManager  = null;
	//private static WalletManager  walletManager   = null;
	private static GlobeUtil globeUtil;
	private static TouchMobileUtil tmUtil;
	
	private static String GHP_NUMRANGE  = null;
	private static String TM_NUMRANGE   = null;
	private static String SMART_NUMRANGE   = null;
	private static String SUN_NUMRANGE   = null;
	
	private static String GCash_HOST     = null;
	private static String GCash_URI      = null;
	private static String GCash_USER     = null;
	private static String GCash_PASSWORD = null;
	private static String[] GCashDenoms;
//	private static String GCASH_PROP = "";
//	private static String PREFIXES;
	
//	private static String PARTNERS_PROMO = "";
//	private static String TOPUPPID = "";
//	private static String TOPUPBID = "";
//	private static String TOPUPUSR = "";
//	private static String TOPUPPWD = "";
//	private static String DOM_URL = "";
	
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
	
	
	private static final Logger logger = Logger.getLogger(GcashCashInController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request, HttpSession session) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		
		ModelAndView modelAndView = new ModelAndView("cashincustomer");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
			
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
				redirect.addObject("login", "no");
				return redirect;	
			
		} else
				 {
			
				Map fillbox = dao.fillBox();
				Map fillprodtype = dao.fillprodtype();
				//	Map prodtype = dao.prodtype();
			
				Float wallet = dao.getWallet(session);

				modelAndView.addObject("topupForm", new TopupModel());
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillprodtype", fillprodtype);
			//	modelAndView.addObject("producttype", prodtype);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));	
				return modelAndView;
				
				}
			
		}		
	

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView viewResult(@ModelAttribute("topupForm") TopupModel topup, HttpSession usersession) throws NamingException, SQLException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ApplicationContext  smscontext = new ClassPathXmlApplicationContext("Spring-SMS.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		OutgoingSMSWriterDAO smsdao =  (OutgoingSMSWriterDAO)smscontext.getBean("smsDAO");

		ModelAndView success = new ModelAndView("cashincustomer");
		ModelAndView fail = new ModelAndView("loadcustomer");
		Map fillbox = dao.fillBox();
		Float wallet = dao.getWallet(usersession);

		String pid = (String) usersession.getAttribute("PID");
		topup.setPid(pid);	

		
		Float topupamount = Float.parseFloat(topup.getAmount());
		topup.setTxtype("mobile");
		topup.setRequestype("GCASH");
		topup.setProdtype("GCASH");
		
		if(topup.getProdtype().equals("none")) {
			
			fail.addObject("status", "fail");
			fail.addObject("msg", "Please choose valid product type.");
			fail.addObject("fillbox", fillbox);
			//fail.addObject("producttype", prodtype);
			fail.addObject("currentwallet", wallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info("Invalid Product type");
			return fail;
			
			
		}
		
		if(!this.isNumber(topup.getAmount())) {
			
			fail.addObject("status", "fail");
			fail.addObject("msg", "Please input valid amount.");
			fail.addObject("fillbox", fillbox);
			fail.addObject("currentwallet", wallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info("Invalid Amount");
			return fail;
			
			
		}
		
		
		if(!this.isNumber(topup.getMobnum())){
			
			fail.addObject("status", "fail");
			fail.addObject("msg", "Please Input Valid Mobile number");
			fail.addObject("fillbox", fillbox);
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
		
//		GCash_HOST = servletContext.getInitParameter("GCASH_HOST");
//		GCash_URI  = servletContext.getInitParameter("GCASH_URI");
//		GCash_USER = servletContext.getInitParameter("GCASH_USER");
//		GCash_PASSWORD = servletContext.getInitParameter("GCASH_PASSWORD");
//		
//		GCashDenoms = servletContext.getInitParameter("GCashDenoms").split(",");
		//GCASH_PROP = servletContext.getInitParameter("GCASH_PROP");
		//PREFIXES = servletContext.getInitParameter("PREFIXES");
		
//		PARTNERS_PROMO = servletContext.getInitParameter("PARTNERS_PROMO");
//		TOPUPPID = servletContext.getInitParameter("TOPUPPID");
//		TOPUPBID = servletContext.getInitParameter("TOPUPBID");
//		TOPUPUSR = servletContext.getInitParameter("TOPUPUSR");
//		TOPUPPWD = servletContext.getInitParameter("TOPUPPWD");
//		TOPUP_TYPE  = servletContext.getInitParameter("TOPUP_TYPE");
		
		
			
		String mobile = null;
		int errorState = 0;

		Connection conn = null;
		Connection sms_conn = null;
		PartnerProfile partner = null;

		int serviceType = 0;
		
		AgentModel agent = new AgentModel();

		mobile = topup.getPrefix() + topup.getMobnum();
		int walletid = -1;
		try {
			walletid= dao.getWalletid(usersession, "GCASH");
			
			logger.info("get walletid: " + walletid);
			
			if(walletid == - 1){
				Float currwallet = dao.getWallet(usersession);
				fail.addObject("status", "fail");
				fail.addObject("msg", "No Wallet found for Partnerid");
				fail.addObject("fillbox", fillbox);
				//fail.addObject("producttype", prodtype);
				fail.addObject("currentwallet", currwallet);
				fail.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(P2MConstants.getMessage(errorState));
				closeconn(conn, sms_conn);
				return fail;
			}

			
	
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
		
		if ((partner.wallet <= 0) || (Integer.parseInt(topup.getAmount()) <= 0) || ( partner.wallet < Integer.parseInt(topup.getAmount()) )) {
			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}
		
		
		String allows = "";
		try {
			allows = dao.getAllows(pid,topup.getProdtype());
			agent.setAllows(allows);
		} catch (Exception e) {
	
			
		} 
		
		if (allows.equals(""))
		{
		
			
			fail.addObject("status", "fail");
			fail.addObject("msg", "No Permission to do topup.");
			fail.addObject("fillbox", fillbox);
			fail.addObject("currentwallet", wallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info("Invalid Mobile Number");
			return fail;
			
			
		}
		
		boolean isfeeincluded = false;
		boolean isfeesettlement = false;
		
		
		if (agent.getAllows().length() >= 2)
		{
			char collect = agent.getAllows().charAt(AMAXGCASHConstants.ALLOW_GCASH_FEE_COLLECT);
			
			if (collect == '1')
			{
				isfeesettlement = true;

			}
			if (agent.getAllows().length() > 2)
			{
				char feeincluded = agent.getAllows().charAt(AMAXGCASHConstants.ALLOW_GCASH_FEE_INCLUDED);
				if (feeincluded == '1')
				{
					isfeeincluded = true;
				}
			}
		}
		
		

		
		
		Float transactionFee = -1F;
		
		Float amount = Float.parseFloat(topup.getAmount());
		
		transactionFee = dao.getTransactionFee(topup.getProdtype(),pid,amount);
		
		logger.info("p2m transactionFee: "+ transactionFee);
		
		try 
		{
			transactionFee = dao.getTransactionFee(topup.getProdtype(),pid,amount);
			if (transactionFee > 0)
			{
				logger.info("rounding transactionFee: "+ transactionFee);
				transactionFee = Round(transactionFee,2);
				logger.info("rounding transactionFee new: "+ transactionFee  );
			}

			if (transactionFee > 0 && isfeeincluded)
			{
				amount = amount - transactionFee;
			}
				
		}
		catch (Exception feeexcept)
		{
			transactionFee = -1F;
		}
		
		logger.info("p2m transactionFee: "+ transactionFee);
		logger.info("p2m entering GCASH mobile no: " + mobile );
		logger.info("p2m **********************Entering PCTOMOBILE GCASH***********************");
		
		int amtint = 0;
		 DecimalFormat df = new DecimalFormat("##");
	        try {
	        	Number num = df.parse(amount.toString());
	        	amtint = num.intValue();
	        	logger.info("Adjust amount = " + amount + " to " + amtint);

	        }
	        catch (Exception parsedf)
	        {
	        	logger.info("Amount Parsed");

	        }
	        
	    if (amtint <= 0)
	    {
	    
	    }
	    else
	    {
	    	logger.info("Adjust Fees and amount " + amount + " " + amtint + " " + transactionFee);
	    	Float difference = amount - amtint;
	    	if ((difference > 0) && (transactionFee >= 0))
	    	{
	    		logger.info("Add difference to fees " + difference + " " + transactionFee);
	    		transactionFee += difference;
	    	}
	    	amount = new Float(amtint);
	    	logger.info("Adjust Fees and amount adjustment " + amount + " " + amtint + " " + transactionFee);
	    	
	    }
	    
		Float walletbalance = 0.0F;
		
//		int walletid = dao.getWalletid(usersession, "GCASH");
//		logger.info("get walletid: " + walletid);
//		
//		if(walletid == -1){
//			
//		}
		Wallet wallets = dao.GetWalletInfo(pid, walletid);
		


		
			walletbalance = wallets.wallet;
			if (wallets.paymenttype.equalsIgnoreCase("FEES"))
				isfeesettlement = true;
			else if (wallets.paymenttype.equalsIgnoreCase("SETTLEMENT"))
				isfeesettlement = true;
			

		
		String px_transactionfeetype = "COLLECTED";
		if (isfeesettlement)
		{
			px_transactionfeetype = "SETTLEMENT";
		}
		
		
		Wallet w = new Wallet();
		

		logger.info("Transaction Fee" + transactionFee + " wallet balance = " + walletbalance + " fee settled? " + isfeesettlement + " fee included ? " + isfeeincluded );
		
		
		Wallet_Transaction_Information wallettransactioninfo = new Wallet_Transaction_Information(usersession.getAttribute("PID").toString(), topup.ptxid.toString(), String.valueOf(topup.txid),String.valueOf(walletid));
		
	       if (agent.getAllows().charAt(AMAXGCASHConstants.ALLOW_GCASH) != '1'){
		
					try {
						dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.GCASH_SERVICE_NOT_ALLOW_MSG,"",usersession);
					} catch (Exception e) {
						logger.info("***** Update failed for TXID " + topup.txid + "- Mobile not Allowed to GCash*****");
					} 	
				
			// Check if wallet is Sufficient 	
			}
	       else if (transactionFee < 0) {
		    	// transactionFee = -1 if invalid denom
	
				try {
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INVALID_DENOM_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- Invalid Denomination *****");
				}
			// Last if checking all succeed, go to GCash		
			}
		    else if (amount <= 0)
			{
		    	logger.info(" amount invalid " + amount);

				try {
					
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- Insufficient wallet balance *****");
				}
			}

		    else if ( walletbalance < 0 )
		    {
		    	logger.info("wallet balance error ");
				try {
					
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid  + "- Insufficient wallet balance *****");
				}
			   // Check if valid denom 
			} 
		    else if (wallet != null && wallets.paymenttype.equalsIgnoreCase("FEES"))
		    { // fees used to charge only fees for settlement    amount of product not included in charges  used for evending  who has own wallet and they manage the wallet on their own
		      // fees are only used for non payexchange wallets.  if payexchange wallet is used,  SHOULD NOT EVER BE FEES
		    	logger.info("payment type FEES not allowed");

				try {
					
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- FEES paymenttype  *****");
				}
		    }
		    else if (wallet != null && wallets.paymenttype.equalsIgnoreCase("PREPAID") && walletbalance < 0)  
		    {
		    	logger.info("Prepaid but wallet balance = 0");

		
				try {
					
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- Insufficient wallet balance *****");
				}
			// Check if valid denom 
			} 
		    else if (wallet != null && wallets.paymenttype.equalsIgnoreCase("PREPAID") && !isfeesettlement &&  walletbalance < amount + transactionFee) 
		    {
		    	logger.info("PREPAID but not enough balance for amount + transaction fee" );
				try {
					
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- Insufficient wallet balance *****");
				}
			// Check if valid denom 
			} 
		    else if (wallet != null && wallets.paymenttype.equalsIgnoreCase("PREPAID") && isfeesettlement &&  walletbalance < amount) 
		    {
		    	logger.info("Prepaid wallet balance not enough for amount " + amount);

			
				try {
					dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.INSUFFICIENT_BALANCE_MSG,"",usersession);
				} catch (Exception e) {
					logger.info("***** Update failed for TXID " + topup.txid + "- Insufficient wallet balance *****");
				}
			// Check if valid denom 
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
		
		GCMaxResponse resp = null;
		String session = null;
		boolean loginsuccess = false;
		

			
			if(partner.runmode.equalsIgnoreCase("TEST"))
			
			{
				if (wallet != null)
				{
					boolean deductflg  = false;
					
						if (wallets.paymenttype.equalsIgnoreCase("PREPAID"))
						{
							
							Float denom =  ( amount + transactionFee);
							if (isfeesettlement)
								denom = amount;
							 wallets = dao.GetWalletInfo(pid, walletid);
							deductflg =  dao.DeductWallet(pid,walletid, denom,  topup.txid);
							 wallets = dao.GetWalletInfo(pid, walletid);
							walletbalance = wallets.wallet;
						}
						else if (wallets.paymenttype.equalsIgnoreCase("SETTLEMENT"))
						{
							Float denom =  ( amount + transactionFee);
							 wallets = dao.GetWalletInfo(pid,walletid);
							deductflg =  dao.CreditWallet(pid, walletid, denom, topup.txid);
							logger.info("crediting for settlement wallet done");
							 wallets = dao.GetWalletInfo(pid, walletid);
							walletbalance = wallets.wallet;
						}
						else if (wallets.paymenttype.equalsIgnoreCase("FEES"))
						{
							Float denom =  new Float( transactionFee);
							 wallets = dao.GetWalletInfo(pid, walletid);
							deductflg =  dao.CreditWallet(pid, walletid, denom, topup.txid);
							logger.info("amount credited fee: " + denom);

							 wallets = dao.GetWalletInfo(pid, walletid);
							walletbalance = wallets.wallet;
						}
				

						logger.info("AMAXConstants.TOPUP_SUCCESSFUL_MSG");
						
						 GCMaxResponse gcResponse = new GCMaxResponse();
						 
						 gcResponse.setResponse("RC_GCASH_SUCCESS");
						 gcResponse.setResponseCode(0);
						 gcResponse.setTrace("123456");
						 gcResponse.setGCashTraceNo("123456");
						 gcResponse.setSessionCode(0);
						 
							
						 
						 resp = gcResponse;
						 
						 logger.info("response test: "+resp.equals(null));
						 

						try {
							dao.getWalletBalances(wallettransactioninfo);
							int iupfee = dao.updateTransactionwithFee("00(TEST)","RC_GCASH_SUCCESS","123456",  transactionFee, isfeesettlement,px_transactionfeetype,pid,topup.txid,wallettransactioninfo);
							errorState = 0;
							dao.updateTransaction(topup.txid,topup.ptxid,AMAXGCASHConstants.TOPUP_SUCCESSFUL_MSG,"",usersession);
						} catch (Exception e) {
							logger.info("***** (TEST) Update failed for TXID " + topup.txid + "- Test success update *****");
						}
					
				}
				
	
				
				try {
					logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState + " " + topup.getProdtype());
					
				
						
						
						dao.updateTransaction(topup.txid,topup.ptxid,errorState,"1234567",usersession);
			
						if(errorState == 0){
							dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,AMAXConstants.getMessage(0), "1234567");
						}

					
					  	
					
					DecrementationBean bean = dao.getDecrementation(topup.getProdtype(), usersession.getAttribute("PID").toString(), Float.parseFloat(topup.getAmount()));
//					int decrementation = dao.getDecrementation(pid);
					logger.info("Checking decrementation permission....");
					
					if(bean!= null)
					{
						
						
						
						logger.info("Computation of Decrementation Commission will apply");
						
						Float commission = bean.getCommission();
						Float divisor = (float) 100;
						Float decreamount = Float.parseFloat(topup.getAmount()) * (commission/(divisor));
						
						logger.info("Decremented Value is:"+decreamount);
						
						int returnCommission = dao.updatetxid(usersession, topup.ptxid, decreamount.floatValue());
						
						if(returnCommission>0){
							dao.getWalletBalances(wallettransactioninfo);
							dao.updateTransactionDecre(topup.txid, topup.ptxid, decreamount.floatValue(),wallettransactioninfo,usersession);
							
						}
					}
					

				}catch (Exception e) {
					e.printStackTrace();
				} finally {
		
					
				}
	
			}
			
			
			else
			
			{

				
				boolean flgupdatewallet = false;
				Float denom =  (amount + transactionFee);
				boolean debit = false;
				
	
				
				if (wallets.paymenttype.equalsIgnoreCase("PREPAID"))
				{
				
					if (isfeesettlement)
						denom = amount;
					 wallets = dao.GetWalletInfo(pid, walletid);
					 debit =  dao.DeductWallet(pid,walletid, denom, topup.txid);
					 wallets = dao.GetWalletInfo(pid, walletid);
					walletbalance = wallets.wallet;
	
				}
				else if (wallets.paymenttype.equalsIgnoreCase("SETTLEMENT"))
				{
					
					 wallets = dao.GetWalletInfo(pid, walletid);
					 debit =  dao.CreditWallet(pid, walletid, denom, topup.txid);
					logger.info("crediting for settlement wallet done");
					 wallets = dao.GetWalletInfo(pid, walletid);
					walletbalance = wallets.wallet;
				}
				else if (wallets.paymenttype.equalsIgnoreCase("FEES"))
				{
				
					 wallets = dao.GetWalletInfo(pid, walletid);
					 debit =  dao.CreditWallet(pid, walletid, denom, topup.txid);
					logger.info("amount credited fee: " + denom);

					 wallets = dao.GetWalletInfo(pid, walletid);
					walletbalance = wallets.wallet;
				}
				
				
				if (debit)
				{
					
							
					logger.info("+++++++++++++++++++GCASH IS ON, try to connect to GCASH++++++++++++++++");
					
					 GCMaxResponse gcMaxResponse = new HandleGcash().processGCash(mobile, 
								amount,"PC2MWEB");
					 
					 resp = gcMaxResponse;
					 
					 errorState = resp.getResponseCode();
					 
					 try{
						 	if (resp.getResponse().equalsIgnoreCase("RC_GCASH_SUCCESS")) 
						 	{
								
						 		dao.getWalletBalances(wallettransactioninfo);
						 		int iupfee = dao.updateTransactionwithFee("00", "RC_GCASH_SUCCESS", gcMaxResponse.getGCashTraceNo(),  transactionFee,isfeesettlement,px_transactionfeetype,pid,topup.txid,wallettransactioninfo);
				
								if ( ( topup.getMessage() != null ) && ( topup.getMessage().length() > 0) ) {
									
									OutgoingSMSModel SMSout = new OutgoingSMSModel();
									OutgoingSMSWriterDAO SMSwriter = new OutgoingSMSWriterDAO();
										
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
										SMSwriter = null;
									}
								}
							} else 
							{ 
								//loading not successful
								
								boolean flgcreditwallet = false;
								if (wallets == null)
									 flgcreditwallet = true;
								else 
								{
								// credit back amount
									Float denomamt = amount ;
									if (wallets.paymenttype.equalsIgnoreCase("PREPAID"))
										{
											if (isfeesettlement)
												denom = amount;
											flgcreditwallet = dao.CreditWallet(pid, walletid, denom, topup.txid);
											wallets = dao.GetWalletInfo(pid, walletid);
											walletbalance = wallets.wallet;
										}
										else if (wallets.paymenttype.equalsIgnoreCase("SETTLEMENT"))
										{
											flgcreditwallet =  dao.DeductWallet(pid,walletid, denom,  topup.txid);
											wallets = dao.GetWalletInfo(pid, walletid);
											walletbalance = wallets.wallet;
										}
										
						
								}
							
								dao.updateTransaction(String.valueOf(gcMaxResponse.getResponseCode()), gcMaxResponse.getResponse(),gcMaxResponse.getGCashTraceNo(),pc2mwebFunc.AmaxgetMessage(gcMaxResponse.getResponseCode()),wallets.partnertxid,pid);
							
								
							} 
						 	
						 	
						 	
						 
						} catch (Exception e) {
							
							 e.printStackTrace();
						}
					}
						 
				else
				{
		
				}
					
			try {
				logger.info("ErrorState on update for txn " + topup.txid + " = " + errorState);
				
				if ( resp != null ) {
					dao.updateTransaction(topup.txid,topup.ptxid,errorState,resp.getGCashTraceNo(),usersession);
	
					if(errorState == 0){
						dao.insertusertxLog(usersession,topup.ptxid,Integer.parseInt(topup.getAmount()),mobile,"GCASH_CASHIN_SUCCESS", resp.getGCashTraceNo());
					}
				}    	

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
		
			}
				
				
				
				
			}
			
	
		
		if ( resp != null ) 
		{ 
			if(errorState == 0)
			{
				Float currwallet = dao.getWallet(usersession);
				Map fillprodtype = dao.fillprodtype();
				success.addObject("status","success");
				success.addObject("msg", pc2mwebFunc.Gcashgetmessage(errorState)+" Trace Number is: "+ resp.getGCashTraceNo());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(AMAXConstants.getMessage(errorState));
				return success;
			}
			else if(errorState == 1 || errorState == 2 || errorState == 3 || errorState == 4 || errorState == 5 || errorState == 6)
			{
				Float currwallet = dao.getWallet(usersession);
				Map fillprodtype = dao.fillprodtype();
				success.addObject("status","fail");
				success.addObject("msg", pc2mwebFunc.AmaxgetMessage(errorState));
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(AMAXConstants.getMessage(errorState));
				return fail;
			}else if (errorState == 100)
			{
				Float currwallet = dao.getWallet(usersession);
				Map fillprodtype = dao.fillprodtype();
				success.addObject("status","success");
				success.addObject("msg","Epins dispense successful! Trace Number is: "+ resp.getGCashTraceNo());
				success.addObject("fillbox", fillbox);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(AMAXConstants.getMessage(errorState));
				return success;
			}else if (errorState == 101){
				Float currwallet = dao.getWallet(usersession);
				Map fillprodtype = dao.fillprodtype();
				success.addObject("status","fail");
				success.addObject("msg","Epins dispense error! Trace Number is: "+ resp.getGCashTraceNo());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);;
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(AMAXConstants.getMessage(errorState));
				return fail;
			}else if (errorState == 102){
				Float currwallet = dao.getWallet(usersession);
				Map fillprodtype = dao.fillprodtype();
				success.addObject("status","fail");
				success.addObject("msg","Epins dispense invalid denom! Trace Number is: "+ resp.getGCashTraceNo());
				success.addObject("fillbox", fillbox);
				success.addObject("fillprodtype", fillprodtype);
				success.addObject("currentwallet", currwallet);
				success.addObject("user",usersession.getAttribute("USERLEVEL"));
				logger.info(AMAXConstants.getMessage(errorState));
				return fail;
			}
			
			else 
			{
			Float currwallet = dao.getWallet(usersession);
			Map fillprodtype = dao.fillprodtype();
			fail.addObject("status", "fail");
			fail.addObject("msg", pc2mwebFunc.P2MConstantsgetMessage(errorState));
			fail.addObject("fillbox", fillbox);
			fail.addObject("fillprodtype", fillprodtype);
			fail.addObject("currentwallet", currwallet);
			fail.addObject("user",usersession.getAttribute("USERLEVEL"));
			logger.info(P2MConstants.getMessage(errorState));
			return fail;
			}
			
			

		}
		Float currwallet = dao.getWallet(usersession);
		Map fillprodtype = dao.fillprodtype();
		fail.addObject("status", "fail");
		fail.addObject("msg", "Operation Failed.");
		fail.addObject("fillbox", fillbox);
		fail.addObject("fillprodtype", fillprodtype);
		fail.addObject("currentwallet", currwallet);
		fail.addObject("user",usersession.getAttribute("USERLEVEL"));
		logger.info(P2MConstants.getMessage(errorState));
		return fail;

		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST,params="mode=getAmount")
    public @ResponseBody String getAmount(@RequestParam("prodtype") String prodtype,
    		HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TopupDAO dao = (TopupDAO)context.getBean("topupDAO");
		
		BigDecimal amount = dao.getAmount(prodtype);
				
		return amount+"";
		
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
	

	
   
    
	private static String md5HashStr2(String strToHash) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// md5.update(strToHash.getBytes("CP1252"));
			byte[] retBytes = md5.digest(strToHash.getBytes("CP1252"));

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < retBytes.length; ++i) {
				sb.append(Integer.toHexString((retBytes[i] & 0xFF) | 0x100)
						.toUpperCase().substring(1, 3));
			}

			String md5Hash = sb.toString();
			return md5Hash.toLowerCase();
		} catch (Exception e) {
			//System.out.println.log(Level.INFO,"Exception in LC md5HashStr2:",e);
			return null;
		}

	}

	private void registerMyHostnameVerifier() {
		javax.net.ssl.HostnameVerifier myHv = new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname,
					javax.net.ssl.SSLSession session) {
				return true;
			}
		};
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(myHv);
	}

	private void registerMyTrustManager() {
		TrustManager[] trustAll = new javax.net.ssl.TrustManager[] { new javax.net.ssl.X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		try {
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
					.getInstance("SSL");
			sc.init(null, trustAll, new java.security.SecureRandom());
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
					.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println.log(Level.INFO,"Exception in LC registerMyTrustManager:",e);
			
		}
	}
	
	private String getElement(String strMessage, String strElement) {
		String startEl = "<" + strElement + ">";
		String endEl = "</" + strElement + ">";

		String data = strMessage.substring(strMessage.indexOf(startEl)
				+ strElement.length() + 2, strMessage.indexOf(endEl));
		// String data = strMessage.substring(strMessage.indexOf(endEl));

		return data;
	}
	
	public void setServletContext(ServletContext context) {
		this.servletContext = context;		
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
	
	
	 public  float Round(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return (float)tmp/p;
		  }
	
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
	
}
