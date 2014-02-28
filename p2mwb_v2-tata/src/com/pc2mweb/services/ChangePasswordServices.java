package com.pc2mweb.services;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.controller.ChangePasswordController;
import com.pc2mweb.dao.transactions.ChangePasswordDAO;
import com.pc2mweb.model.ChangePasswordModel;

public class ChangePasswordServices {
	

	private static final Logger logger = Logger.getLogger(ChangePasswordServices.class);
	
	
	public ModelAndView changemyPass(ChangePasswordModel changepass, HttpSession usersession)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ChangePasswordDAO dao = (ChangePasswordDAO) context.getBean("changepasswordDAO");
			
		ModelAndView modelAndView = new ModelAndView("changepassword");

		logger.info("Password Changed Request for username: "+usersession.getAttribute("USER"));
		
		usersession.setAttribute("username", changepass.getUsername());
		
		String user = (String) usersession.getAttribute("AID");
		BigDecimal aid = new BigDecimal(user);
		List<ChangePasswordModel> pass = dao.getMyPass(aid);
		modelAndView.addObject("changePassword", new ChangePasswordModel());
		modelAndView.addObject("records", pass);   
		modelAndView.addObject("changemypass","yes");
		modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		
		if(changepass.getOldpass() == null){
			

			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Please input your old password!" );
			return modelAndView;
			
		}
		
		if(changepass.getOldpass().equals("")){
			

			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Please input your old password!" );
			return modelAndView;
			
		}

		
		if(changepass.getNewpass().equals("")){
			

			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Please input your new password!" );
			return modelAndView;
			
		}
		
		if(changepass.getNewpass().length() < 6){
			

			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Password must be at least 6 characters" );
			return modelAndView;
			
		}
		
		if(changepass.getNewpass2().equals("")){
			
		
			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Please input your confirm new password!" );
			return modelAndView;
			
		}
		

		if(!changepass.getNewpass().equals((changepass.getNewpass2()))){
			
			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "New password and confirm new password must be the same!" );
			return modelAndView;
			
		}
		
		if(changepass.getNewpass().equals((changepass.getOldpass()))){
		
			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "New password and old password must not be the same!" );
			return modelAndView;
			
		}
		
		boolean valid = CheckPermission(changepass.getUsername(), usersession);
		if (valid)
		{	
	
		if(dao.changemyPassword(changepass,usersession)){
			
			logger.info("Password Changed Successful for username: "+usersession.getAttribute("USER"));
				
			modelAndView.addObject("status", "success" );
			modelAndView.addObject("msg", "Your password has been changed!" );
			modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
			  
			return	modelAndView;
			
		}else
		{	
			logger.info("Password Changed Failed for username: "+usersession.getAttribute("USER"));
			
			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "Password change not successful!" );
			modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
			  
			return	modelAndView;
			
		}
		}
		else {
			logger.info("You don't have permission to do transaction in this page.");
			modelAndView.addObject("status", "fail" );
			modelAndView.addObject("msg", "You don't have permission to do transaction in this page." );
	    	modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	return modelAndView;
		}
		

	}
	
	
	 public ModelAndView changeotherPass(ChangePasswordModel changepass, HttpSession usersession)
	    {
		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
			ChangePasswordDAO dao = (ChangePasswordDAO) context.getBean("changepasswordDAO");
				
			ModelAndView modelAndView = new ModelAndView("changepassword");

			usersession.setAttribute("username", changepass.getUsername());
			
			//String user = (String) usersession.getAttribute("AID");
			//BigDecimal aid = new BigDecimal(user);
			List<ChangePasswordModel> pass = dao.getUserPass(changepass.getUsername(), usersession);
			modelAndView.addObject("changePassword", new ChangePasswordModel());
			modelAndView.addObject("records", pass);   
			modelAndView.addObject("changeotherpass","yes");
			modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
			

			if(changepass.getNewpass().equals("")){
				

				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "Please input your new password!" );
				return modelAndView;
				
			}
			
			if(changepass.getNewpass().length() < 6){
				

				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "Password must be at least 6 characters" );
				return modelAndView;
				
			}
			
			if(changepass.getNewpass2().equals("")){
				
			
				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "Please input your confirm new password!" );
				return modelAndView;
				
			}
			

			if(!changepass.getNewpass().equals((changepass.getNewpass2()))){
				
				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "New password and confirm new password must be the same!" );
				return modelAndView;
				
			}
			
			if(changepass.getNewpass().equals((changepass.getOldpass()))){
			
				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "New password and old password must not be the same." );
				return modelAndView;
				
			}
			
			boolean valid = CheckPermission(changepass.getUsername(), usersession);
			if (valid)
			{	
		
			if(dao.changePassword(changepass,usersession)){
				
			
				modelAndView.addObject("status", "success" );
				modelAndView.addObject("msg", "Password change successful!" );
				modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
				  
				return	modelAndView;
				
			}else
			{	
			
				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "Password Change Not Successful!" );
				modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
				  
				return	modelAndView;
				
			}
			}
			else {
				logger.info("You don't have permission to do transaction in this page.");
				modelAndView.addObject("status", "fail" );
				modelAndView.addObject("msg", "You don't have permission to do transaction in this page." );
		    	modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		    	return modelAndView;
			}
		 
	    }
	 
		private boolean CheckPermission(String Username, HttpSession usersession)
		{
			boolean valid = true;
			if(usersession.getAttribute("USERLEVEL").equals("user")){
				String loggedinuser = usersession.getAttribute("USER").toString();
				if (!loggedinuser.equals(Username))
					valid = false;
			}
			return valid;
		}

}
