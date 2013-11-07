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


import com.pc2mweb.dao.transactions.ManageBranchesDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
import com.pc2mweb.dao.transactions.TransactionReportsDAO;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.ManageBranchesModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;

@Controller
@RequestMapping("transactioninquiry")
public class TransactionInquiryController {
	private static final Logger logger = Logger.getLogger(TransactionInquiryController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		TransactionInquiryDAO dao = (TransactionInquiryDAO)context.getBean("transactioninquiryDAO");
		
		
		ModelAndView modelAndView = new ModelAndView("transactioninquiry");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
			modelAndView.addObject("txInquiryForm", new TransactionInquiryModel());
			
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			
			return modelAndView;
					
		}
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView viewResult(@ModelAttribute("txInquiryForm") TransactionInquiryModel searchModel, HttpSession usersession)
    {
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		TransactionInquiryDAO dao = (TransactionInquiryDAO)context.getBean("transactioninquiryDAO");
			  
		ModelAndView modelAndView = new ModelAndView("searchresults");

		if(!this.isNumber(searchModel.getMsisdn()) && !searchModel.getMsisdn().equals(""))
		{
			ModelAndView redirect = new ModelAndView("transactioninquiry");
			redirect.addObject("msgTxinquiry","Input valid mobile number.");
			redirect.addObject("user",usersession.getAttribute("USERLEVEL"));
	    	return redirect;
		}
		
		   
			   List<TransactionReportsResultModel> searchresult = dao.getTransactions(searchModel,usersession);
				
			    if(searchModel.getMsisdn().equals("") && searchModel.getTransactiondate().equals(""))
			    {
			    	modelAndView.addObject("msgTxinquiry","One of the fields must not be empty.");
			    	return modelAndView;
			    }
			    
			    if(usersession.getAttribute("USERLEVEL").equals("manager"))
			    {
			    
					if(searchresult.isEmpty()){
						
						modelAndView.addObject("txinquiry","null");
						modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
						
					}else{
						
						modelAndView.addObject("results",searchresult);
						modelAndView.addObject("type","txinquiry");
						modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
					}
			
					return modelAndView;
					
			    }else {
			    	
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
	
    public boolean isNumber(String tx)  
    {  
       try  
       {  
          Long.parseLong(tx);
          
       }  
       catch( NumberFormatException nfe )  
       {  
          return false;  
       }  
       return true;  
    } 
	
}
