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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.TransactionIDObject;
import com.pc2mweb.dao.transactions.ChangePasswordDAO;
import com.pc2mweb.dao.transactions.TransfertoBranchDAO;
import com.pc2mweb.dao.transactions.TransfertoRetailerDAO;
import com.pc2mweb.dao.transactions.UserManagementDAO;
import com.pc2mweb.dao.transactions.WalletTransferDAO;
import com.pc2mweb.model.ChangePasswordModel;
import com.pc2mweb.model.EditPartnerModel;
import com.pc2mweb.model.TransactionInquiryModel;
import com.pc2mweb.model.TransferCreditsModel;
import com.pc2mweb.model.TransferModel;
import com.pc2mweb.model.WalletTransferModel;
import com.pc2mweb.services.EditBranchNameServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("editbranch")
public class EditBranchNameController {
	
	
	private static final Logger logger = Logger.getLogger(EditBranchNameController.class);
	
	@RequestMapping(method = {RequestMethod.GET},params={"branchId"})
    public ModelAndView changeAgentPass(@RequestParam("branchId") String branchid, ModelMap model,HttpServletRequest request,HttpSession usersession) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		UserManagementDAO dao = (UserManagementDAO) context.getBean("usermanagementDAO");
		
		ModelAndView modelAndView = new ModelAndView("editbranch");
		ModelAndView redirect = new ModelAndView("redirect:main.html");

		HttpSession isSession = request.getSession();
		
		ChangePasswordModel changepass = new ChangePasswordModel();
		changepass.setOldpass("changeotherpass");
		
		
		if ((null == isSession.getAttribute("USER"))) {				
				
				redirect.addObject("login", "no");
				return redirect;	
			
		}
		else {
			
				List<EditPartnerModel> branch = dao.getBranchname(branchid,usersession);
				modelAndView.addObject("editBranch", new EditPartnerModel());
				modelAndView.addObject("branch", branch);
				modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
				
			
				return modelAndView;
				
				
			
		}

    }
	

	
//	@RequestMapping( method = RequestMethod.POST)
//    public ModelAndView changemyPass(@ModelAttribute("editBranch")  EditBranchModel changebranchname, HttpSession usersession)
//    {
//		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
//		
//		UserManagementDAO dao = (UserManagementDAO) context.getBean("usermanagementDAO");
//			
//		ModelAndView modelAndView = new ModelAndView("editbranch");
//		modelAndView.addObject("editBranch", new EditBranchModel());
//
//	    
//	    if(usersession.getAttribute("USERLEVEL").equals("manager"))
//	    {
//	    	
//			if(changebranchname.getBranchname() == null){
//				
//
//				modelAndView.addObject("status", "fail" );
//				modelAndView.addObject("msg", "Please input your branch name" );
//				return modelAndView;
//				
//			}else if(dao.changeBranchName(changebranchname, usersession)){
//				
//				modelAndView.addObject("status", "success" );
//				modelAndView.addObject("msg", "Branch name successfully updated." );
//				modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
//				  
//				return	modelAndView;
//				
//			}else
//			{	
//			
//				modelAndView.addObject("status", "fail" );
//				modelAndView.addObject("msg", "Branch name change not successful!" );
//				modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
//				  
//				return	modelAndView;
//				
//			}
//	
//	    }
//
//		else {
//			logger.info("You don't have permission to do transaction in this page.");
//			modelAndView.addObject("status", "fail" );
//			modelAndView.addObject("msg", "You don't have permission to do transaction in this page." );
//	    	modelAndView.addObject("user",usersession.getAttribute("USERLEVEL"));
//	    	return modelAndView;
//		}
//
//		
//
// 
//	}
	
	
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String editBranch(@RequestParam("partnerId") String pid,
    	   @RequestParam("partnerName") String partnername,
    	   @RequestParam("message") String message,
    	   @RequestParam("email") String email, HttpSession usersession)
    {
		
		EditBranchNameServices service = new EditBranchNameServices();
		
		EditPartnerModel changepartnername = new EditPartnerModel();

		changepartnername.setPartnerid(pid);
		changepartnername.setPartnername(partnername);
		changepartnername.setMessage(message);
		changepartnername.setEmail(email);
		
	    return service.editBranch(changepartnername, usersession);
	                	
	                	
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
