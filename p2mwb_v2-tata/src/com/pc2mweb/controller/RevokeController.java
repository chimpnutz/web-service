package com.pc2mweb.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pc2mweb.dao.transactions.EmergencyLoadDAO;
import com.pc2mweb.model.EmergencyLoadManagementModel;
import com.pc2mweb.model.TransferModel;

@Controller
@RequestMapping("revoke")
public class RevokeController {
	
	private static final Logger logger = Logger.getLogger(RevokeController.class);
	
	@RequestMapping(method = RequestMethod.GET)
 	 public ModelAndView login(ModelMap model,HttpServletRequest request) {
			
		ModelAndView modelAndView = new ModelAndView("revoke");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		return modelAndView;

//		HttpSession isSession = request.getSession();
//		
//		if (null == isSession.getAttribute("USER")) {	
//			
//			redirect.addObject("login", "no");
//			return redirect;	
//			
//		} else {
//			
//			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
//			
//			return modelAndView;
//		
//		}

	}
	
	@RequestMapping(method = RequestMethod.POST)
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
		
		@ExceptionHandler()
	    public ModelAndView iHandleExceptions(Exception e) {
	        //do loads of interesting stuff to deal with the exception
			
			ModelAndView modelAndView = new ModelAndView("error");
			
			modelAndView.addObject("error","yes");
			
			logger.info(e.getMessage());
			
	        return modelAndView;
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
