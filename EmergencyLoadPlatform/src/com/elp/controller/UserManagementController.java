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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.elp.beans.UserBean;
import com.elp.dao.transactions.CreditLimitDAO;
import com.elp.dao.transactions.LoginDAO;
import com.elp.dao.transactions.PrivilegesManagementDAO;
import com.elp.dao.transactions.RolesManagementDAO;
import com.elp.dao.transactions.UserManagementDAO;
import com.elp.model.LoginModel;
import com.elp.model.PermissionModel;
import com.elp.model.PrivilegesManagementModel;
import com.elp.model.RoleManagementModel;
import com.elp.model.UserManagementModel;


@Controller
@RequestMapping("usermanagement")
public class UserManagementController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(UserManagementController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		UserManagementDAO dao = (UserManagementDAO)context.getBean("usermanagementDAO");

 		HttpSession isSession = request.getSession();
 		
		ModelAndView page = new ModelAndView("usermanagement");
		ModelAndView loginpage = new ModelAndView("redirect:sign-in.html");
		
		if (null == isSession.getAttribute("username")) {		
				
					loginpage.addObject("login","no");		

				return loginpage;	
			
		} else
				 {		
	
					List<UserManagementModel> users = dao.getUsers(session);
					Map companylist = dao.getcompanylist();
					Map userlist = dao.getUserNames();
					Map roles = dao.getrolelist();
					

					page.addObject("usermanagementForm",new UserManagementModel());
					page.addObject("companylist",companylist);
					page.addObject("userlistdrop",userlist);
					page.addObject("roleslist",roles);
					page.addObject("userlist",users);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
			    	//mainpage.addObject("roleid","0");
			    	return page;
				}
		
 		
 	
		
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	 public ModelAndView post(HttpServletRequest request,HttpSession session) throws NamingException {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		UserManagementDAO dao = (UserManagementDAO)context.getBean("usermanagementDAO");
	
		ModelAndView page = new ModelAndView("usermanagement");

	
	
					List<UserManagementModel> users = dao.getUsers(session);
					Map companylist = dao.getcompanylist();
					Map userlist = dao.getUserNames();
					Map roles = dao.getrolelist();
					

					page.addObject("usermanagementForm",new UserManagementModel());
					page.addObject("companylist",companylist);
					page.addObject("userlistdrop",userlist);
					page.addObject("roleslist",roles);
					page.addObject("userlist",users);
					page.addObject("username",session.getAttribute("username"));	
					page.addObject("rolename",session.getAttribute("rolename"));
					page.addObject("companyname",session.getAttribute("companyname"));
					page.addObject("roleid",session.getAttribute("roleid"));
			    	//mainpage.addObject("roleid","0");
			    	return page;
			
		
		
	
		
	}	

	
	@RequestMapping(method = RequestMethod.POST,params={"result"})
    public @ResponseBody List <UserManagementModel> getUsername(@RequestParam("uname") String uname, HttpSession session)
    {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		UserManagementDAO dao = (UserManagementDAO)context.getBean("usermanagementDAO");
		
		
		List <UserManagementModel> test = dao.getUsers(uname);
		
		return test;
    	

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