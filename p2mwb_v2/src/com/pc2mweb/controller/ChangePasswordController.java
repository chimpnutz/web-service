package com.pc2mweb.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.dao.transactions.ChangePasswordDAO;
import com.pc2mweb.model.ChangePasswordModel;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransferModel;
import com.pc2mweb.model.WalletTransferModel;
import com.pc2mweb.services.ChangePasswordServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("changepassword")
public class ChangePasswordController {
	
	
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView changemyPass(ModelMap model,HttpServletRequest request,HttpSession usersession) 
	{
		
	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
	ChangePasswordDAO dao = (ChangePasswordDAO) context.getBean("changepasswordDAO");
		
	ModelAndView modelAndView = new ModelAndView("changepassword");
	ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		HttpSession isSession = request.getSession();
				
		if (null == isSession.getAttribute("USER")) {			
							
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			

				String user = (String) usersession.getAttribute("AID");
				BigDecimal aid = new BigDecimal(user);
				List<ChangePasswordModel> pass = dao.getMyPass(aid);		
				modelAndView.addObject("changePassword", new ChangePasswordModel());
				modelAndView.addObject("records", pass);   
				modelAndView.addObject("changemypass","yes");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
							
				return modelAndView;
	
		}

    }
	
	@RequestMapping(method = {RequestMethod.GET},params={"agentID"})
    public ModelAndView changeAgentPass(@RequestParam("agentID") BigDecimal agentid, ModelMap model,HttpServletRequest request,HttpSession usersession) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ChangePasswordDAO dao = (ChangePasswordDAO) context.getBean("changepasswordDAO");
		
		ModelAndView modelAndView = new ModelAndView("changepassword");
		ModelAndView redirect = new ModelAndView("redirect:main.html");

	//	boolean valid = CheckPermission(pass.get(0).getUsername(),usersession);

		HttpSession isSession = request.getSession();
		
		ChangePasswordModel changepass = new ChangePasswordModel();
		changepass.setOldpass("changeotherpass");
		
		
		if ((null == isSession.getAttribute("USER"))) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		}
		else {
			
				List<ChangePasswordModel> pass = dao.getUserPass(agentid,usersession);
				modelAndView.addObject("changePassword", new ChangePasswordModel());
				modelAndView.addObject("records", pass);
				modelAndView.addObject("changeotherpass","yes");
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				
			
				return modelAndView;
				
				
			
		}

    }
	

	
	@RequestMapping( method = RequestMethod.POST,params="changemypass")
	
    public ModelAndView changemyPass(@ModelAttribute("changePassword")  ChangePasswordModel changepass, HttpSession usersession)
    {
		ChangePasswordServices service = new ChangePasswordServices();
		
		return service.changemyPass(changepass, usersession);
		
		
 
	}
	
	@RequestMapping( method = RequestMethod.POST,params="changeotherpass")
    public ModelAndView changeotherPass(@ModelAttribute("changePassword")  ChangePasswordModel changepass, HttpSession usersession)
    {
		ChangePasswordServices service = new ChangePasswordServices();
		
		return service.changeotherPass(changepass, usersession);
 
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
