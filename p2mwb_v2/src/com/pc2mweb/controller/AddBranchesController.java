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
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.services.AddBranchServices;

@Controller
@RequestMapping("addbranch")
public class AddBranchesController {
	private static final Logger logger = Logger.getLogger(AddBranchesController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		ManageBranchesDAO dao = (ManageBranchesDAO) context.getBean("managebranchesDAO");
		
		
		ModelAndView modelAndView = new ModelAndView("addbranch");
		ModelAndView redirect = new ModelAndView("redirect:main.html");
		
		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		} else {
			
			
			List<ManageBranchesModel> partnerlist = dao.getPartners(usersession);
			
			modelAndView.addObject("partners", partnerlist);
			
			modelAndView.addObject("managebranchesForm", new ManageBranchesModel());
			
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
			
			return modelAndView;
					
		}
	
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView viewResult(@ModelAttribute("managebranchesForm") ManageBranchesModel model, HttpSession usersession)
    {
			
		AddBranchServices service = new AddBranchServices();
		
		
		return service.addBranch(model, usersession);

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
