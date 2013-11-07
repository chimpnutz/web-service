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
import com.elp.dao.transactions.LoginDAO;
import com.elp.model.LoginModel;
import com.elp.model.NetworkManagementModel;
import com.elp.model.PermissionModel;


@Controller
@RequestMapping("main")
public class SignInController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(SignInController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(HttpServletRequest request) throws NamingException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		LoginDAO dao = (LoginDAO) context.getBean("loginDAO");
		
		ModelAndView mainpage = new ModelAndView("main");
		ModelAndView loginpage = new ModelAndView("main");
		
		
		
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("username")) {			
				
				loginpage.addObject("loginForm", new LoginModel());
				return loginpage;	
			
		} else
				 {		


					List<PermissionModel> permission = dao.getmodulePermission(isSession);
					mainpage.addObject("loginForm", new LoginModel());
			    	mainpage.addObject("username",isSession.getAttribute("username"));	
			    	mainpage.addObject("rolename",isSession.getAttribute("rolename"));
			    	mainpage.addObject("companyname",isSession.getAttribute("companyname"));
			    	mainpage.addObject("roleid",isSession.getAttribute("roleid"));
			    	//mainpage.addObject("roleid","0");
			    	return mainpage;
				}
		
	}	
	
	@RequestMapping(method = RequestMethod.GET,params={"login"})
	 public ModelAndView topupView(@RequestParam("login") String login,HttpServletRequest request) throws NamingException {
		
		ModelAndView mainpage = new ModelAndView("main");
		mainpage.addObject("loginForm", new LoginModel());
	   	mainpage.addObject("login","no");
	
		return mainpage;
		
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginForm") LoginModel loginForm,  
            BindingResult result,HttpSession session,HttpServletRequest request) 
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		LoginDAO logindao = (LoginDAO) context.getBean("loginDAO");
	
		
		ModelAndView success = new ModelAndView("loginsuccess");
		
		ModelAndView fail = new ModelAndView("main");
		

		if(loginForm.getUsername().equals("")){
			
	    	logger.info("Please input your Username.");
	    	fail.addObject("blankUser", "yes");
	    	fail.addObject("msg", "*Please input your username");
	    	return fail;
			
		}
		
		if(loginForm.getPassword().equals("")){
			
			logger.info("Please input your Password");
	    	fail.addObject("blankPass", "yes");
	    	fail.addObject("msg", "*Please input your password.");
	    	return fail;
			
		}
		

		UserBean bean = logindao.login(loginForm.getUsername(), loginForm.getPassword());
		
        if(bean!=null){ 	
        	
            session.setAttribute("username", bean.getUserName());
            session.setAttribute("rolename", bean.getRolename());
            session.setAttribute("companyname", bean.getCompanyname());
            session.setAttribute("levelid", bean.getLevelid());
            session.setAttribute("roleid", bean.getRoleid());
            session.setMaxInactiveInterval(60*50);

            logger.info("Login Success.");
   
        	return success;
    
	    }else{
	    	logger.info("Login Failed. Please Try Again");
	    	fail.addObject("success", "no");
	    	return fail;
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
