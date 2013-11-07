package com.elp.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.elp.beans.UserBean;
import com.elp.dao.transactions.CreditLimitManagementDAO;
import com.elp.dao.transactions.EmergencyLoadManagementDAO;
import com.elp.dao.transactions.LoginDAO;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.CreditLogsModel;
import com.elp.model.LoginModel;
import com.elp.model.PermissionModel;


@Controller
@RequestMapping("creditlimitmanagement-edit")
public class CreditLimitManagementEditController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(CreditLimitManagementEditController.class);
	
	@RequestMapping(method = RequestMethod.GET,params={"replenish"})
	 public ModelAndView replenish(@RequestParam("cid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		EmergencyLoadManagementDAO edao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	

					List<CreditLimitManagementModel> creditlist = dao.getindividualcredit(id);
					boolean isCreditLimit = edao.checkRetailerCreditLimit(session);
					int balance = dao.getAvailableLimit(isSession);
					Map company = edao.getcompanylist();
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("balance",balance);
					page.addObject("isCreditLimit",isCreditLimit);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("creditlist",creditlist);
					page.addObject("type","replenish");
			    	return page;
			  
				}
		
	}		

	@RequestMapping(method = RequestMethod.GET,params={"pay"})
	 public ModelAndView payCredit(@RequestParam("cid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		EmergencyLoadManagementDAO edao = (EmergencyLoadManagementDAO)context.getBean("emergencyloadmanagementDAO");
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
 		HttpSession isSession = request.getSession();
 		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	

					List<CreditLimitManagementModel> creditlist = dao.getindividualcredit(id);
					boolean isCreditLimit = edao.checkRetailerCreditLimit(session);
					int balance = dao.getAvailableLimit(isSession);
					Map company = edao.getcompanylist();
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("balance",balance);
					page.addObject("isCreditLimit",isCreditLimit);
					page.addObject("companylist",company);	
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("creditlist",creditlist);
					page.addObject("type","unpaid");
					page.addObject("txtype","pay");
			    	return page;
			  
				}
		
	}		
	
	
	@RequestMapping(method = RequestMethod.GET,params={"edit"})
	 public ModelAndView editCredit(@RequestParam("cid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	
	
					List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
					List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
					Map company = dao.getcompanylist(session);
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Approved", "Approved");
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("creditList",creditList);
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("txtype","edit");
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
	}	
	
	@RequestMapping(method = RequestMethod.GET,params={"cancel"})
	 public ModelAndView cancelCredit(@RequestParam("cid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	
					List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
					Map company = dao.getcompanylist(session);
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("creditList",creditList);
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("txtype","cancel");
			    	return page;
				}
		
	}
	
	@RequestMapping(method = RequestMethod.GET,params={"cancelresult"})
	 public ModelAndView cancelCreditSuccess(@RequestParam("cid") int id,@RequestParam("stat") int stat,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	
					List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
					Map company = dao.getcompanylist(session);
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Cancel", "Cancel");
					status.put("Approved", "Approved");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("creditList",creditList);
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("txtype","cancel");
					if(stat == 4){
						
						page.addObject("msg","Cancelling of Credit Limit Failed. Please settle your Outstanding Balance first.");	
							
						}
					if(stat == 5){
						
						page.addObject("msg","Cancelling of Credit Limit Successful.");	
							
						}
			    	return page;
				}
		
	}

	
	@RequestMapping(method = RequestMethod.GET,params={"success"})
	 public ModelAndView successCredit(@RequestParam("cid") int id,@RequestParam("stat") int stat,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	
	
					List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
					List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
					Map company = dao.getcompanylist(session);
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Approved", "Approved");
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("creditList",creditList);
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("txtype","edit");
					page.addObject("success","yes");
					if(stat == 0){
						
						page.addObject("msg","Editing of Credit Limit Successful.");
						
					}
						
				
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
	}	
	
	@RequestMapping(method = RequestMethod.GET,params={"fail"})
	 public ModelAndView failCredit(@RequestParam("cid") int id,@RequestParam("err") int err,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
		HttpSession isSession = request.getSession();
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-edit");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");
					return loginpage;	
			
		} else
				 {		
	
	
					List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
					List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
					Map company = dao.getcompanylist(session);
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Approved", "Approved");
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("creditList",creditList);
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
					page.addObject("txtype","edit");
					page.addObject("success","no");
					
					if(err == 1){
						
						page.addObject("msg","Editing of Credit Limit Failed");
						
					}else
						if(err == 2){
							
						page.addObject("msg","Editing of Credit Limit Failed. Please settle your Outstanding Balance first.");	
							
						}
						else
							if(err == 3){
								
							page.addObject("msg","Editing of Credit Limit Failed. Not Enough PD Balance.");	
								
							}
					
			
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView editCL(@ModelAttribute("creditlimitmanagementForm") CreditLimitManagementModel model,  
            BindingResult result,HttpSession session,HttpServletRequest request) 
	{
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");

		int cResult = dao.editCreditLimit(session,model);
		
		ModelAndView Sucesspage = new ModelAndView("redirect:creditlimitmanagement-edit.html?success&cid="+model.getCreditlimitid()+"&stat="+cResult);
		ModelAndView Failpage = new ModelAndView("redirect:creditlimitmanagement-edit.html?fail&cid="+model.getCreditlimitid()+"&err="+cResult);
		
		ModelAndView CancelSucesspage = new ModelAndView("redirect:creditlimitmanagement-edit.html?cancelresult&cid="+model.getCreditlimitid()+"&stat="+cResult);
		ModelAndView CancelFailpage = new ModelAndView("redirect:creditlimitmanagement-edit.html?cancelresult&cid="+model.getCreditlimitid()+"&stat="+cResult);
		
		if(cResult == 0){
			
			return Sucesspage;
			
		}else 	if(cResult == 1){
			
			return Failpage;
			
		}else  	if(cResult == 2){
			
			return Failpage;
			
		} else  	if(cResult == 3){
			
			return Failpage;
			
		}else  	if(cResult == 4){
			
			return CancelFailpage;
			
		}else  	if(cResult == 5){
			
			return CancelSucesspage;
			
		}else{
			return Failpage;
		}
		
	

	}
	

	public void setServletContext(ServletContext context) {
		this.servletContext = context;	
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
