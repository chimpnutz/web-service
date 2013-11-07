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
import com.elp.model.CreditLimitManagementModel;
import com.elp.model.LoginModel;
import com.elp.model.OpenSimReplenishmentModel;
import com.elp.model.PermissionModel;


@Controller
@RequestMapping("creditlimitmanagement-view")
public class CreditLimitManagementViewController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(CreditLimitManagementViewController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(@RequestParam("cid") int id,HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		CreditLimitManagementDAO dao = (CreditLimitManagementDAO)context.getBean("creditlimitmanagementDAO");
		
		
		LoginDAO Ldao = (LoginDAO) context.getBean("loginDAO");
		 
 		HttpSession isSession = request.getSession();
 		
		ModelAndView page = new ModelAndView("creditlimitmanagement-view");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");		

					return loginpage;	
			
		} else
				 {		
	
	

			List<CreditLimitManagementModel> creditList = dao.getindividualcredit(id);
			int companyid = creditList.get(0).getCompanyid();
			
			System.out.println(companyid);

			List<OpenSimReplenishmentModel> openlist = dao.getopensimreplenishmentlist(companyid);

			page.addObject("creditList",creditList);
			page.addObject("openlist",openlist);
			page.addObject("username",session.getAttribute("username"));	
			page.addObject("rolename",session.getAttribute("rolename"));
			page.addObject("companyname",session.getAttribute("companyname"));
			page.addObject("roleid",session.getAttribute("roleid"));

	    	return page;
				}
		
		
	}
		
	
	


	public void setServletContext(ServletContext context) {
		this.servletContext = context;	
	}



}
