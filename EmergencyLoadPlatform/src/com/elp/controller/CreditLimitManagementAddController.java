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
import com.elp.dao.transactions.LoginDAO;
import com.elp.dao.transactions.NetworkManagementDAO;
import com.elp.dao.transactions.UserManagementDAO;
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.LoginModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.PermissionModel;


@Controller
@RequestMapping("creditlimitmanagement-add")
public class CreditLimitManagementAddController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(CreditLimitManagementAddController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");

		ModelAndView page = new ModelAndView("creditlimitmanagement-add");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("username")) {		
				
				loginpage.addObject("login","no");
				return loginpage;	
			
		} else
				 {		
					int roleid = Integer.parseInt(session.getAttribute("roleid")+"");
	
					List<PermissionModel> permission = Ldao.getmodulePermission(isSession);
					Map company;
					if(roleid == 1 || roleid == 2 || roleid == 3 || roleid == 8 )
					{
						 company = dao.getPD(session);
					}else{
						 company = dao.getDSP(session);
					}
					
					Map referenceData = new HashMap();
					Map<String,String> status = new LinkedHashMap<String,String>();
					status.put("Approved", "Approved");
					status.put("Cancel", "Cancel");
					referenceData.put("statuslist", status);
					
					page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
					page.addObject("companylist",company);	
					page.addObject("statuslist",status);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
		
		
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("creditlimitmanagementForm") CreditLimitManagementModel model,  
            BindingResult result,HttpSession session,HttpServletRequest request) 
	{
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		
		ModelAndView page = new ModelAndView("creditlimitmanagement-add");
		
		int cResult = (dao.addCreditLimit(session,model));
		
		Map company = dao.getDSP(session);
		Map referenceData = new HashMap();
		Map<String,String> status = new LinkedHashMap<String,String>();
		status.put("Approved", "Approved");
		status.put("Cancel", "Cancel");
		referenceData.put("statuslist", status);
		

		page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
		page.addObject("companylist",company);	
		page.addObject("statuslist",status);
		page.addObject("username",session.getAttribute("username"));	
		page.addObject("rolename",session.getAttribute("rolename"));
		page.addObject("companyname",session.getAttribute("companyname"));
		page.addObject("roleid",session.getAttribute("roleid"));
		page.addObject("result","yes");
		
		if(cResult==0){
			
			page.addObject("msg","Adding of Credit Limit Failed.");
			logger.info("Adding of Credit Limit Failed.");
		}
		else if(cResult==-1){
		
			page.addObject("msg","Not Enough Balance.");
			logger.info("Not Enough Balance.");
			
		}else if(cResult==1){

			page.addObject("msg","Adding PD Credit Limit Successful.");
			logger.info("Adding PD Credit Limit Successful..");
			
		}else if(cResult==2){

			page.addObject("msg","Adding Retailer Credit Limit Successful.");
			logger.info("Adding Retailer Credit Limit Successful.");
			
		}else if(cResult==3){

			page.addObject("msg","Adding Retailer Credit Limit Fail. Please settle first the outstanding balance.");
			logger.info("Adding Retailer Credit Limit Fail. Please settle first the outstanding balance.");
		}
		
		
		
		
//		if(dao.addCreditLimit(session,model)){
//			
//			Map company = dao.getDSP(session);
//			Map referenceData = new HashMap();
//			Map<String,String> status = new LinkedHashMap<String,String>();
//			status.put("Approved", "Approved");
//			status.put("Cancel", "Cancel");
//			referenceData.put("statuslist", status);
//			
//
//			page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
//			page.addObject("companylist",company);	
//			page.addObject("statuslist",status);
//			page.addObject("username",session.getAttribute("username"));	
//			page.addObject("rolename",session.getAttribute("rolename"));
//			page.addObject("companyname",session.getAttribute("companyname"));
//			page.addObject("roleid",session.getAttribute("roleid"));
//			page.addObject("success","yes");
//			
//		}else{
//			
//			Map company = dao.getDSP(session);
//			Map referenceData = new HashMap();
//			Map<String,String> status = new LinkedHashMap<String,String>();
//			status.put("Approved", "Approved");
//			status.put("Cancel", "Cancel");
//			referenceData.put("statuslist", status);
//			
//
//			page.addObject("creditlimitmanagementForm",new CreditLimitManagementModel());
//			page.addObject("companylist",company);	
//			page.addObject("statuslist",status);
//			page.addObject("username",session.getAttribute("username"));	
//			page.addObject("rolename",session.getAttribute("rolename"));
//			page.addObject("companyname",session.getAttribute("companyname"));
//			page.addObject("roleid",session.getAttribute("roleid"));
//			page.addObject("success","no");
//			
//		}
		
		return page;

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
