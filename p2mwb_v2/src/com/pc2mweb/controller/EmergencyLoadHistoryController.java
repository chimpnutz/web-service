package com.pc2mweb.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.pc2mweb.dao.transactions.EmergencyLoadDAO;
import com.pc2mweb.dao.transactions.TransactionHistoryDAO;
import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
import com.pc2mweb.model.EmergencyLoadHistoryModel;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransferModel;
import com.pc2mweb.model.UserManagementModel;

@Controller
@RequestMapping("emergencyloadhistory")
public class EmergencyLoadHistoryController {
	
	private static final Logger logger = Logger.getLogger(EmergencyLoadHistoryController.class);
	
	@RequestMapping(method = RequestMethod.GET)
 	 public ModelAndView login(ModelMap model,HttpServletRequest request,HttpSession usersession) throws ParseException {
			
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		EmergencyLoadDAO cDao = (EmergencyLoadDAO)context.getBean("emergencyDAO");

		ModelAndView modelAndView = new ModelAndView("emergencyloadhistory");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) 
		{				
				redirect.addObject("login", "no");
				return redirect;	
			
		} 
		else 
		{
				String user = (String) usersession.getAttribute("USER");
				List<EmergencyLoadHistoryModel> txlog = cDao.getHistory(usersession);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("logs",txlog);	

				
				return modelAndView;
								
		}
						
	}
	


}
