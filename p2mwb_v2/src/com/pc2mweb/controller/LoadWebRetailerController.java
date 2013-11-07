package com.pc2mweb.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paysetter.amax.AMAXConstants;
import com.paysetter.amax.TransferStocksConstant;
import com.paysetter.commons.pctomobile.P2MConstants;
import com.paysetter.commons.pctomobile.PartnerProfile;
import com.paysetter.commons.pctomobile.ProfileManager;
import com.paysetter.util.GlobePrepaid.GlobeUtil;
import com.paysetter.util.GlobePrepaid.TouchMobileUtil;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.RetailerSimBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.beans.UserBean;

import com.pc2mweb.dao.transactions.LoginDAO;
import com.pc2mweb.dao.transactions.OutgoingSMSWriterDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
import com.pc2mweb.dao.transactions.TransfertoBranchDAO;
import com.pc2mweb.dao.transactions.TransfertoMotherDAO;
import com.pc2mweb.dao.transactions.TransfertoRetailerDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.ViewAccountDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.OutgoingSMSModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.TransferCreditsModel;
import com.pc2mweb.model.TransfertoRetailerModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.model.WalletTransferModel;
import com.pc2mweb.model.container.TransferCreditsListContainer;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("loadretailer-web")
public class LoadWebRetailerController {
	
	@Autowired
	private ServletContext servletContext;
	
	private static final Logger logger = Logger.getLogger(LoadWebRetailerController.class);

	private static DataSource ds;
	private static DataSource SMSds;
	private static ProfileManager profileManager  = null;
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
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView WalletView(HttpServletRequest request,HttpSession userssession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		WalletTransferDAO dao = (WalletTransferDAO)context.getBean("wallettransferDAO");
		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
		TopupDAO tDao = (TopupDAO)context.getBean("topupDAO");
		
		ModelAndView modelAndView = new ModelAndView("loadretailer-web");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
				List<WalletTransferModel> partnerlist = dao.getPartners(userssession);
			//	List<WalletTransferModel> retailers = retailerDao.getInfo(userssession);
				List<MessageBean>  wallet = dao.getWallet(userssession);
				Float  partnerwallet = dao.getPartnerWallet(userssession);
				Float myWallet = tDao.getWallet(userssession);
				
				modelAndView.addObject("myWallet", myWallet);
				modelAndView.addObject("partners", partnerlist);
				//modelAndView.addObject("retailers", retailers);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("partnerwallet", partnerwallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				
				if(partnerlist.isEmpty()){
					
					modelAndView.addObject("retailer", "null");
					
					return modelAndView;
				}
				
				

				return modelAndView;
				
				
					
		}	
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String transfertoBranch(@RequestParam("amount") String txtamount,
    		@RequestParam("branchid") String branchid, HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		WalletTransferDAO dao = (WalletTransferDAO)context.getBean("wallettransferDAO");
		TransfertoRetailerDAO retailerDao = (TransfertoRetailerDAO)context.getBean("transfertoretailerDAO");
		TransfertoBranchDAO branchdao = (TransfertoBranchDAO)context.getBean("transfertobranchDAO");
		
		String pid = usersession.getAttribute("PID").toString();
		 
		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();
		
		TransactionIDObject obj = new TransactionIDObject();
			
		String amount = txtamount.trim();
		
	    if(!amount.equals("") && this.isNumber(amount) )
	    {
	    	
	    logger.info("Request Transfer Amount of "+amount+ " to Branch id: "+branchid+" from Partner id: "+pid );
	    Float amount2 = Float.parseFloat(amount);
	    Float currentPidwallet = branchdao.getpidWallet(pid);
	    Float currentBidwallet = branchdao.getbidWallet(branchid);

	    Float newpidwallet = null;
	    Float newbidwallet = null;
	                	            	

	         if((currentPidwallet>=amount2))
	         {
	            			
	            newpidwallet = currentPidwallet - amount2;
	            newbidwallet = currentBidwallet + amount2;
	            			
	            	if(branchdao.transfertoBranch(pid, branchid, amount2,obj))
	            	{
	            				
	            		System.out.println(obj.transactionID);
	            		branchdao.insertWalletHistory(branchid, pid, amount2, currentPidwallet, newpidwallet, currentBidwallet, newbidwallet,"TRANSFERTOBRANCH",obj);
	            				
	            		logger.info("Transfer Completed from Partner id: "+pid+ " to Partner id: "+branchid);	            		
	            		logger.info("New Partner ID Wallet is: "+newpidwallet+", New Partner ID Wallet is: "+newbidwallet);

	            				
	            		return "Transfer Success.";
	            			
	            						
	            	}
	            	else
	            	{
	            				
	            	logger.info("Transfer Fail.");
	            	
	            	return "Transfer Fail.";
		            			
	            							
	            	}
	            						
	         }else
	            	{
	            			
	            	logger.info("Insufficient Partner Wallet Balance");
	        
	            	return "Insufficient Wallet Balance";
	            			          			
	            	}
	         
	    }else if(!amount.equals("") && !this.isNumber(amount)){
	    	
	    	return "Invalid Amount";
	    	
	    }else
	    	
		return "";
	                	
	                	
   }
	
	
	@RequestMapping(method = RequestMethod.POST,params="mode=validUser")
    public @ResponseBody String checkPassword(@RequestParam("pw") String password,
    		HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TransfertoMotherDAO motherdao = (TransfertoMotherDAO)context.getBean("transfertomotherDAO");
		 
		LoginDAO logindao = (LoginDAO) context.getBean("loginDAO");
		
		ModelAndView modelAndView = new ModelAndView("redirect:main.html");

		ArrayList<TransferCreditsModel> transfercreditsList = new ArrayList<TransferCreditsModel>();

		TransactionIDObject obj = new TransactionIDObject();
		
		String pid = usersession.getAttribute("PID").toString();	
		String uname = usersession.getAttribute("USER").toString();
		
		
		UserBean bean = logindao.login(uname, password);
		
		if(bean!=null){
			
			return "SUCCESS";   
			
		}else

			return "FAIL";
		
}
	
	
	
	public void setServletContext(ServletContext context) {
		this.servletContext = context;		
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
	
	@ExceptionHandler()
    public ModelAndView iHandleExceptions(Exception e) {
        //do loads of interesting stuff to deal with the exception
		
		ModelAndView modelAndView = new ModelAndView("error");
		
		modelAndView.addObject("error","yes");
		
		logger.info(e.getMessage());
		
        return modelAndView;
    }


	
	
	
}
