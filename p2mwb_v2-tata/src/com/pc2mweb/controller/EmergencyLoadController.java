package com.pc2mweb.controller;

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
import com.pc2mweb.model.EmergencyLoadModel;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;

@Controller
@RequestMapping("emergencyload")
public class EmergencyLoadController {
	private static final Logger logger = Logger.getLogger(EmergencyLoadController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView emergencyloadView(ModelMap model,HttpServletRequest request, HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		EmergencyLoadDAO dao = (EmergencyLoadDAO)context.getBean("emergencyDAO");
		
		
		ModelAndView modelAndView = new ModelAndView("emergencyload");
		ModelAndView redirect = new ModelAndView("redirect:main.html");

		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
//			Map fillbox = dao.fillBox(usersession);
			modelAndView.addObject("emergencyForm", new EmergencyLoadModel());
//			modelAndView.addObject("fillbox", fillbox);
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			
			return modelAndView;
			
		}
					
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView viewResult(@ModelAttribute("emergencyForm") EmergencyLoadModel emergencyModel, HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadDAO dao = (EmergencyLoadDAO)context.getBean("emergencyDAO");
		
		ModelAndView modelAndView = new ModelAndView("emergencyload");
		
	    if(emergencyModel.getFrdate().equals("") || emergencyModel.getTodate().equals("") || emergencyModel.getAmount().equals(""))
	    {
	    	modelAndView.addObject("msg","All fields must not be empty!.");
	    	return modelAndView;
	    }
		
		if(usersession.getAttribute("USERLEVEL").equals("manager"))
		{

	    if(dao.insertcreditLimit(usersession, emergencyModel))
	    {
	    	
		    modelAndView.addObject("status","success");
		    modelAndView.addObject("msg","Credit Limit Request Successful");
		    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	return modelAndView;
	    	
	    }else{
		    modelAndView.addObject("status","fail");
		    modelAndView.addObject("msg","Credit Limit Request Fail");
		    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	return modelAndView;
	    }
	    
	
		}
	    else{
	        logger.info("You don't have permission to do transaction in this page.");
		    modelAndView.addObject("access","no");
		    modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
		    return modelAndView;
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
