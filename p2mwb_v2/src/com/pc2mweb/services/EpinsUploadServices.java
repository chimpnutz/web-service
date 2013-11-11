package com.pc2mweb.services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.payexchange.epins.ws.dao.EpinsUpload;
import com.payexchange.ws.dao.EpinsUploadResponse;
import com.paysetter.commons.pctomobile.P2MConstants;
import com.pc2mweb.beans.PartnerProfile;
import com.pc2mweb.controller.EpinsController;
import com.pc2mweb.dao.transactions.EpinDAO;
import com.pc2mweb.dao.transactions.ProfileManagerDAO;
import com.pc2mweb.model.EpinModel;
import com.pc2mweb.model.TopupModel;

public class EpinsUploadServices {

	private static final Logger logger = Logger.getLogger(EpinsUploadServices.class);
	
	public ModelAndView epinsUpload(EpinModel epins, HttpSession usersession,ServletContext servletContext)
	{
		if(epins.getPassword() == epins.getPassword2())
		{
			return null;
		}
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EpinDAO dao = (EpinDAO)context.getBean("epinDAO");
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");


		
		String pid = (String) usersession.getAttribute("PID");
		epins.setPid(pid);	
		String bid = (String) usersession.getAttribute("BID");
		epins.setBid(bid);

		
		epins.setRequestype("epin");
		epins.setTxtype(epins.getType());
		epins.setProdtype("EpinsUpload");

		Float wallet = dao.getWallet(usersession);
		
		ModelAndView modelAndView = new ModelAndView("epins");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		modelAndView.addObject("epinForm", new EpinModel());
		modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));	
		

		PartnerProfile partner = null;

		int errorState = 0;


		
		try {
			logger.info("get profile: " + usersession.getAttribute("USER"));
			partner = profiledao.GetProfilebyUsername(usersession.getAttribute("USER").toString());
			epins.agentid = partner.agentid;
			epins.txid = dao.insertTransaction(epins,usersession);
			logger.info("topup tx id is: "+epins.txid);
						

			
			
			logger.info("topup tx id is: "+epins.txid);
		} catch (Exception e) {
			e.printStackTrace();	
			errorState = P2MConstants.PROFILE_NOT_FOUND_CODE;
			logger.info(P2MConstants.getMessage(errorState));
			//return "fail";
		}
		
		

		if ((partner.wallet <= 0) || (Float.parseFloat(epins.getAmount()) <= 0) || ( partner.wallet < Float.parseFloat(epins.getAmount()) )) {
			errorState = P2MConstants.INSUFFICIENT_BALANCE_CODE;
			logger.info(P2MConstants.getMessage(errorState));
		}

		if ( errorState != 0 ) {
			
			dao.updateTransaction(epins.txid,errorState,"0");


			logger.info(P2MConstants.getMessage(errorState));
		
			//return "fail";
		}

		int debit = 0;
		String session = null;
		String resp = null;
			
			if(partner.runmode.equalsIgnoreCase("TEST"))
			
			{
				
//				String paymenttype = (String) usersession.getAttribute("paymenttype");
//				
//				if(paymenttype.equalsIgnoreCase("PREPAID"))
//				{
//					
//					dao.deductWallet(usersession, epins.txid, Float.parseFloat(epins.getAmount()));
//
//					
//					logger.info("deducting prepaid wallet done");
//				}
//				else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
//					
//					dao.creditWallet(usersession, epins.txid, Float.parseFloat(epins.getAmount()));
//					
//					logger.info("deducting settlement done");
//					
//				}
//				else if(paymenttype.equalsIgnoreCase("FEES")){
//					
//					dao.creditWallet(usersession, epins.txid, Float.parseFloat(epins.getAmount()));
//					
//					logger.info("deducting fees wallet done");
//				}
				

			}
			
			
			else
			
			{
				

				
				
				try {
				

//					String paymenttype = (String) usersession.getAttribute("paymenttype");
//					
//					if(paymenttype.equalsIgnoreCase("PREPAID"))
//					{
//						
//						debit = dao.updatetxid(usersession, epins.txid, Float.parseFloat(epins.getAmount()) * -1);
//						logger.info("deducting prepaid wallet done");
//						
//					}
//					else if(paymenttype.equalsIgnoreCase("SETTLEMENT")){
//						
//						debit = dao.creditWallet(usersession, epins.txid, Float.parseFloat(epins.getAmount()));
//						logger.info("deducting settlement done");
//						
//					}
//					else if(paymenttype.equalsIgnoreCase("FEES")){
//						
//						debit = dao.creditWallet(usersession, epins.txid, Float.parseFloat(epins.getAmount()));					
//						logger.info("deducting fees wallet done");
//						
//					}
					
				logger.info("Debit result: " + debit);
				logger.info("+++++++++++++++++++Epin Upload++++++++++++++++");
				
				if (debit != 1) {
					logger.info("Unable to deduct from wallet from  " + partner.partnerid + "; Amount: " + epins.getAmount());	
					errorState = P2MConstants.DEBIT_FAILED_CODE;				
				}
				
				else		
				{
				
					EpinsUpload upload = new EpinsUpload();
					EpinsUploadResponse resp1 = new EpinsUploadResponse();
					String type = epins.getTxtype();
					String prodcode = epins.getProdtype();
					int qty = Integer.parseInt(epins.getQuantity());
					String target = epins.getTarget();
					String appname = "PC2MWEB";
					String trantype = "topup";
					String denom = epins.getDenom();
					String message = epins.getMessage();
					String username = epins.getUsername();
					String password = epins.getPassword();
					String password2 = epins.getPassword2();
					long tranid = epins.getTransid();
					String transid =""+tranid;
					
					resp1=upload.epinsupload(type,prodcode,qty,"",target,appname,"",trantype,denom,message,username,password,transid);
					
					if(resp1.getResultcode()==0){
						modelAndView.addObject("msg", "success");
					}
				
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
				
//				// Refund wallet
//				if ( debit == 1 ) {
//
//					dao.updatetxid(usersession, epins.txid, Float.parseFloat(epins.getAmount()));
//				}
			}
			try {				
				
				if(epins.getTxtype()== "individual"){
					epins.setTarget(epins.getPrefix()+ epins.getMobnum());
				}
				if(epins.getTxtype()== "bulk"){
					
					
				}

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			
				
			}
				
				
				
				
			}
		

		logger.info("+++++++++++++++++++EPINS UPLOAD Status! "+resp+" ++++++++++++++++");
		return modelAndView;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
