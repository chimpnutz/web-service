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


import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.dao.transactions.TransactionInquiryDAO;
import com.pc2mweb.dao.transactions.TransactionReportsDAO;
import com.pc2mweb.dao.transactions.WalletTransferHistoryDAO;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransactionReportsModel;
import com.pc2mweb.model.TransactionReportsResultModel;
import com.pc2mweb.model.WalletTransferHistoryModel;

@Controller
@RequestMapping("webwallethistory")
public class WalletTransferHistoryController {

	private static final Logger logger = Logger.getLogger(WalletTransferHistoryController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request,HttpSession usersession) {

		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		WalletTransferHistoryDAO dao = (WalletTransferHistoryDAO)context.getBean("wallettransferhistoryDAO");
		
		ModelAndView modelAndView = new ModelAndView("webwallethistory");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
	
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {					
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
			Map fillbox = dao.fillBox(usersession);	
			
			modelAndView.addObject("wallettransferForm", new WalletTransferHistoryModel());
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			modelAndView.addObject("fillbox", fillbox);
			
			return modelAndView;
		}
			
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView viewResult(@ModelAttribute("wallettransferForm") WalletTransferHistoryModel searchModel,HttpSession usersession)
    {
	  
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		WalletTransferHistoryDAO dao = (WalletTransferHistoryDAO)context.getBean("wallettransferhistoryDAO");
		
		ModelAndView modelAndView = new ModelAndView("searchresults");

		List<WalletTransferHistoryModel> searchresult = dao.getbranchidTrans(searchModel.getBranchid(),usersession);
		
		if(usersession.getAttribute("USERLEVEL").equals("manager")){
		
		if(searchresult.isEmpty()){
			
			modelAndView.addObject("wallethistory","null");
			modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
			
		}else{
			
//			dao.writeExcelWallet(searchModel.getBranchid(),usersession);
			modelAndView.addObject("wallethistory",searchresult);
			modelAndView.addObject("type","wallethistory");
			modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
			
		}

		return modelAndView;
		
		}
		else {
	    	
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
