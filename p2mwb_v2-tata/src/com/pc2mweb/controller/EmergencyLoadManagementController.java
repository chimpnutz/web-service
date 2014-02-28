package com.pc2mweb.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.pc2mweb.dao.transactions.EmergencyLoadDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransactionReportsDAO;
import com.pc2mweb.model.EmergencyLoadHistoryModel;
import com.pc2mweb.model.EmergencyLoadManagementModel;
import com.pc2mweb.model.EmergencyLoadModel;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;

@Controller
@RequestMapping("emergencyloadmanagement")
public class EmergencyLoadManagementController {
	private static final Logger logger = Logger.getLogger(EmergencyLoadManagementController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView emergencyloadView(ModelMap model,HttpServletRequest request, HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		EmergencyLoadDAO dao = (EmergencyLoadDAO)context.getBean("emergencyDAO");
		
		
		ModelAndView modelAndView = new ModelAndView("emergencyloadmanagement");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		//return modelAndView;

		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
			if(dao.checkCreditLimitStatus(usersession))
			{
				
				Map fillbox = dao.fillBox(usersession);
				Map fillbranchbox = dao.availBranch(usersession);
				BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
				List<EmergencyLoadHistoryModel> creditList = dao.getListCreditLimit(usersession);
				modelAndView.addObject("emergencyForm", new EmergencyLoadManagementModel());
				modelAndView.addObject("creditlist", creditList);
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillbranchbox", fillbranchbox);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				
				return modelAndView;
				
			}else
			{
				modelAndView.addObject("status", "not");
				return modelAndView;
			}
			

			
		}
					
	}
	
	@RequestMapping(method = RequestMethod.POST,params="transfer")
    public ModelAndView transfer(@ModelAttribute("emergencyForm") EmergencyLoadManagementModel emergencyModel, HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadDAO dao = (EmergencyLoadDAO)context.getBean("emergencyDAO");
		
		ModelAndView modelAndView = new ModelAndView("emergencyloadmanagement");
		
		String pid = usersession.getAttribute("PID").toString();
		String bid = usersession.getAttribute("BID").toString();
		
		String amount = emergencyModel.getAmount().trim();
		String branch = emergencyModel.getBranch().trim();
		
		if(amount.equals("")){
			
			Map fillbox = dao.fillBox(usersession);
			Map fillbranchbox = dao.availBranch(usersession);
			BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
			modelAndView.addObject("fillbox", fillbox);
			modelAndView.addObject("fillbranchbox", fillbranchbox);
			modelAndView.addObject("currentwallet", wallet);
			modelAndView.addObject("msg","Please input amount.");
    	    modelAndView.addObject("status","fail");
    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
    	    return modelAndView;
		}
		
		if(branch.equals("")){
			
			Map fillbox = dao.fillBox(usersession);
			Map fillbranchbox = dao.availBranch(usersession);
			BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
			modelAndView.addObject("fillbox", fillbox);
			modelAndView.addObject("fillbranchbox", fillbranchbox);
			modelAndView.addObject("currentwallet", wallet);
			modelAndView.addObject("msg","Please choose branch.");
    	    modelAndView.addObject("status","fail");
    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
    	    return modelAndView;
		}
		
		
		if(!amount.equals("") && this.isNumber(amount) )
		{
			
			BigDecimal amount2 = new BigDecimal(amount);
			BigDecimal availCreditLimit = dao.getAvailableCreditLimit(usersession);
			
			BigDecimal newCreditLimit = null;
			
		    if(availCreditLimit.signum() == -1)
		    {
		            			
		    	availCreditLimit = new BigDecimal(0);
		     
		    }
		    
		    if((availCreditLimit.compareTo(amount2) >= 0))
	         {
		    	
		    	newCreditLimit = availCreditLimit.subtract(amount2);
		    	
		    	if(dao.transfertoBranch(usersession, emergencyModel.getBranch(), amount2))
            	{
		    		Map fillbox = dao.fillBox(usersession);
					BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
					Map fillbranchbox = dao.availBranch(usersession);
		    		logger.info("Transfer of Credit Line Completed from Partner id: "+pid+ " to Branch id: "+bid);	            		
					modelAndView.addObject("fillbox", fillbox);
					modelAndView.addObject("fillbranchbox", fillbranchbox);
					modelAndView.addObject("currentwallet", wallet);
		    		modelAndView.addObject("status","success");
		    		modelAndView.addObject("msg","Transfer of Credit Line to Branch Success.");
		    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		    	    return modelAndView;

            		
		    		
		    		
            	}
		    	
		    	
	         }
				logger.info("Transfer of Credit Line Failed");	 
				Map fillbox = dao.fillBox(usersession);
				BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
				Map fillbranchbox = dao.availBranch(usersession);
				modelAndView.addObject("currentwallet", wallet);
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillbranchbox", fillbranchbox);
				modelAndView.addObject("msg","Transfer Failed. Not Enough Credit Limit for the partner.");
	    	    modelAndView.addObject("status","fail");
	    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	    return modelAndView;

		}
		
	    logger.info("You don't have permission to do transaction in this page.");
	    modelAndView.addObject("access","no");
	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    return modelAndView;
	    
	
    }
	
	
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,params="revoke")
    public ModelAndView revoke(@ModelAttribute("emergencyForm") EmergencyLoadManagementModel emergencyModel, HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadDAO dao = (EmergencyLoadDAO)context.getBean("emergencyDAO");
		
		ModelAndView modelAndView = new ModelAndView("emergencyloadmanagement");
		
		String pid = usersession.getAttribute("PID").toString();
		String bid = usersession.getAttribute("BID").toString();
		
		String amount = emergencyModel.getAmount().trim();
		String branch = emergencyModel.getBranch().trim();
		
		if(amount.equals("")){
			
			Map fillbox = dao.fillBox(usersession);
			Map fillbranchbox = dao.availBranch(usersession);
			BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
			modelAndView.addObject("fillbox", fillbox);
			modelAndView.addObject("fillbranchbox", fillbranchbox);
			modelAndView.addObject("currentwallet", wallet);
			modelAndView.addObject("revokemsg","Please input amount.");
    	    modelAndView.addObject("revokestatus","fail");
    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
    	    return modelAndView;
		}
		
		if(branch.equals("")){
			
			Map fillbox = dao.fillBox(usersession);
			Map fillbranchbox = dao.availBranch(usersession);
			BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
			modelAndView.addObject("fillbox", fillbox);
			modelAndView.addObject("fillbranchbox", fillbranchbox);
			modelAndView.addObject("currentwallet", wallet);
			modelAndView.addObject("revokemsg","Please choose branch.");
    	    modelAndView.addObject("revokestatus","fail");
    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
    	    return modelAndView;
		}
		
		if(!amount.equals("") && this.isNumber(amount) )
		{
			
			BigDecimal amount2 = new BigDecimal(amount);
			BigDecimal availCreditLimit = dao.getAvailableCreditLimitforBranch(usersession,emergencyModel);
			
			BigDecimal newCreditLimit = null;
			
		    if(availCreditLimit.signum() == -1)
		    {
		            			
		    	availCreditLimit = new BigDecimal(0);
		     
		    }
		    
		    if((availCreditLimit.compareTo(amount2) >= 0))
	         {
		    	
		    	newCreditLimit = availCreditLimit.subtract(amount2);
		    	
		    	if(dao.revokefromBranch(usersession, emergencyModel.getBranch(), amount2))
            	{
		    		Map fillbox = dao.fillBox(usersession);
		    		Map fillbranchbox = dao.availBranch(usersession);
		    		BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
		    		logger.info("Transfer of Credit Line Completed from Branch id: "+bid+ " to Partner id: "+pid);	            		
		    		modelAndView.addObject("fillbox", fillbox);
					modelAndView.addObject("fillbranchbox", fillbranchbox);
					modelAndView.addObject("currentwallet", wallet);
		    		modelAndView.addObject("revokestatus","success");
		    		modelAndView.addObject("revokemsg","Revoking of Credit Line from Branch Success.");
		    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		    	    return modelAndView;

            		
		    		
		    		
            	}
		    	
		    	
	         }
				logger.info("Transfer of Credit Line Failed, No credit limit available for this branch");	 
				Map fillbox = dao.fillBox(usersession);
	    		Map fillbranchbox = dao.availBranch(usersession);
				BigDecimal wallet = dao.getAvailableCreditLimit(usersession);
				modelAndView.addObject("fillbox", fillbox);
				modelAndView.addObject("fillbranchbox", fillbranchbox);
				modelAndView.addObject("currentwallet", wallet);
	    	    modelAndView.addObject("revokestatus","fail");
	    	    modelAndView.addObject("revokemsg","No credit limit available for this branch");
	    	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	    return modelAndView;

		}
		
	    logger.info("You don't have permission to do transaction in this page.");
	    modelAndView.addObject("access","no");
	    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    return modelAndView;
	    
	
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
//	
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
