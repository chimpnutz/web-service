package com.pc2mweb.services;

import java.util.Map;

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
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EpinDAO dao = (EpinDAO)context.getBean("epinDAO");
		ProfileManagerDAO profiledao = (ProfileManagerDAO)context.getBean("profileDAO");
		
		Map fillbox = dao.fillBox();
		
		String pid = (String) usersession.getAttribute("PID");
		epins.setPid(pid);	
		String bid = (String) usersession.getAttribute("BID");
		epins.setBid(bid);
		String email = (String) usersession.getAttribute("EMAIL");
		epins.setEmail(email);
		
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
		
	
			if(epins.getType().equals("bulk")){
				
//				if(epins.getPassword() == epins.getPassword2())
//					{
//						return null;
//					}
				
				System.out.println(epins.getType());
				
			}
		
			if(epins.getType().equals("individual")){
				
				if(!this.isNumber(epins.getMobnum())||Integer.parseInt(epins.getMobnum())<11) {
					
					modelAndView.addObject("status", "fail");
					modelAndView.addObject("msg", "Please input valid mobile number.");
					modelAndView.addObject("fillbox", fillbox);
					logger.info("+++++++++++++++++++ERROR: Please input valid mobile number+++++++++++++++++++");
					return modelAndView;
					
				}
				System.out.println(epins.getType());
			}
		

		if(epins.getTarget().equals("")){
			modelAndView.addObject("blankemail", "yes");
			modelAndView.addObject("msg", "*Please input your password");
			logger.info("+++++++++++++++++++ERROR: Please input your email+++++++++++++++++++");
			return modelAndView;
		}
		
		if(epins.getPassword()==null){
			modelAndView.addObject("blankpass", "yes");
			modelAndView.addObject("msg", "*Please input your password");
			logger.info("+++++++++++++++++++ERROR: Please input your password+++++++++++++++++++");
			return modelAndView;
		}
		if(epins.getPassword2()==null){
			modelAndView.addObject("blankpass2", "yes");
			modelAndView.addObject("msg", "*Please input same password as above");
			logger.info("+++++++++++++++++++ERROR: Please input your password+++++++++++++++++++");
			return modelAndView;
		}
		if(epins.getPassword()!=epins.getPassword2()){
			modelAndView.addObject("confirm", "yes");
			modelAndView.addObject("msg", "*Password must be the same");
			logger.info("+++++++++++++++++++ERROR: Password must be the same+++++++++++++++++++");
			return modelAndView;
		}
		
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
		
		

		if ((partner.wallet <= 0) || (Float.parseFloat(epins.getDenom()) <= 0) || ( partner.wallet < Float.parseFloat(epins.getDenom()) )) {
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

			}
			
			
			else
			
			{
				
					
				try {
				
					logger.info("+++++++++++++++++++Epin Upload++++++++++++++++");
					
					
					if(epins.getType().equals("individual")){						
						epins.setTarget(epins.getPrefix()+ epins.getMobnum());
						
						if(epins.getTarget().length()<11){
						logger.info("+++++++++++++++++++ERROR: mobile number should atleast 11 digits+++++++++++++++++++");
						
						}
						else{
						EpinsUpload upload = new EpinsUpload();
						EpinsUploadResponse respo = new EpinsUploadResponse();
						String type = epins.getTxtype();
						String prodcode = epins.getProdcode();
						int qty = 1;
						String target = epins.getTarget();
						String appname = "PC2MWEB";
	//ip dito			
						String trantype = "epins";
						String denom = epins.getDenom();
						String message = epins.getMessage();
						String username = epins.getUsername();
						String password = epins.getPassword();
						
						String transid = Long.toString(epins.txid);
						
						System.out.println(transid);
						respo=upload.epinsupload("",prodcode,qty,"",target,appname,"",trantype,denom,message,username,password,transid, "");
						
						if(respo.getResultcode()==0){
							modelAndView.addObject("msg", "success");
						}
						return modelAndView;
						}
				}
				
				if(epins.getTxtype().equals("bulk")){
					
						if(Integer.parseInt(epins.getQuantity())==1){
							logger.info("+++++++++++++++++++ERROR: quantity must be 2 or more +++++++++++++++++++");
							
						}
						
						else{
							
								EpinsUpload upload = new EpinsUpload();
								EpinsUploadResponse respo = new EpinsUploadResponse();
								String type = epins.getTxtype();
								String prodcode = epins.getProdcode();
								int qty = Integer.parseInt(epins.getQuantity());
								String target = epins.getTarget();
								String appname = "PC2MWEB";
								String trantype = "epins";
								String denom = epins.getDenom();
								String message = epins.getMessage();
								String username = (String) usersession.getAttribute("USER");
								String password = epins.getPassword();
								String password2 = epins.getPassword2();
								String transid = Long.toString(epins.txid);
								String emails = partner.email;
								respo=upload.epinsupload(type,prodcode,qty,"",target,appname,"",trantype,denom,message,username,password,transid,emails);
								
								if(respo.getResultcode()==0){
									modelAndView.addObject("msg", "success");				
								}
								return modelAndView;
								}
						}
				}

			 catch (Exception e) {
				e.printStackTrace();
				logger.info("exception : " + e.getMessage());
				errorState = P2MConstants.GENERAL_ERROR_CODE;
			
			}
			try {				
				
				
				

			}catch (Exception e) {
				e.printStackTrace();
			} finally {
			
				
			}
				
				
				
				
			}
			
			
		logger.info("+++++++++++++++++++EPINS UPLOAD Status! "+resp+" ++++++++++++++++");
		return modelAndView;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
}
