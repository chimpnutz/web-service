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


import com.pc2mweb.dao.transactions.TransactionHistoryDAO;
import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
import com.pc2mweb.model.RetailerTransactionHistoryModel;
import com.pc2mweb.model.TransactionHistoryModel;
import com.pc2mweb.model.TransferModel;
import com.pc2mweb.model.UserManagementModel;

@Controller
@RequestMapping("saleshistory-customer")
public class SalesHistoryConsumerController {
	
	private static final Logger logger = Logger.getLogger(SalesHistoryConsumerController.class);
	
	@RequestMapping(method = RequestMethod.GET)
 	 public ModelAndView login(ModelMap model,HttpServletRequest request,HttpSession usersession) throws ParseException {
			
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		TransactionHistoryDAO dao = (TransactionHistoryDAO)context.getBean("transactionhistoryDAO");

		ModelAndView modelAndView = new ModelAndView("saleshistory-customer");
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
				List<TransactionHistoryModel> txlog = dao.getTransactionHistory(usersession);
				List<RetailerTransactionHistoryModel> retailerTxlog = dao.getRetailerTransactionHistory(usersession);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				modelAndView.addObject("logs",txlog);	
				modelAndView.addObject("retailerlogs",retailerTxlog);
				
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
