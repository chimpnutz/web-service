package com.pc2mweb.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
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

import com.paysetter.topup.amax.exceptions.AutoloadMaxException;
import com.paysetter.util.Property;
import com.pc2mweb.beans.MessageBean;
import com.pc2mweb.beans.UserBean;
import com.pc2mweb.dao.transactions.LoginDAO;
import com.pc2mweb.dao.transactions.TopupDAO;
import com.pc2mweb.model.LoginModel;
import com.pc2mweb.model.TopupModel;
import com.pc2mweb.model.UserManagementModel;
import com.pc2mweb.utility.function.pc2mwebFunc;

@Controller
@RequestMapping("loginpage")
public class LoginController implements ServletContextAware{
	
	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(HttpServletRequest request) throws NamingException {
		
		ModelAndView mainpage = new ModelAndView("loginpage");
		ModelAndView loginpage = new ModelAndView("loginpage");
		
		loginpage.addObject("loginForm", new LoginModel());
		mainpage.addObject("loginForm", new LoginModel());
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
			
				return loginpage;	
			
		} else
				 {		
			    	mainpage.addObject("username",isSession.getAttribute("USER"));	
			    	mainpage.addObject("hide","yes");
			    	mainpage.addObject("user",isSession.getAttribute("USERLEVEL"));
			    	mainpage.addObject("usertype",isSession.getAttribute("USERTYPE"));
			    	return mainpage;
				}
			
		}		
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginForm") LoginModel loginForm,  
            BindingResult result,HttpSession session,HttpServletRequest request) throws AutoloadMaxException 
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		LoginDAO logindao = (LoginDAO) context.getBean("loginDAO");
		
		ModelAndView success = new ModelAndView("loginsuccess");
		ModelAndView fail = new ModelAndView("loginpage");
		
		if(loginForm.getUserName().equals("")){
			
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
		

		UserBean bean = logindao.login(loginForm.getUserName(), loginForm.getPassword());
		
        if(bean!=null)
        { 	
        	
        	
        	if(bean.getUserLevel().equalsIgnoreCase("processor"))
        	{
        		
              	
        	    	fail.addObject("success", "no");
        	    	fail.addObject("msg", "Access Denied. No permission to use application.");
        	    	return fail;
    
        	}
            session.setAttribute("USER", bean.getUserName());
                    
            if(bean.getUserLevel().trim().equals("manager"))
            {
            	  session.setAttribute("USERTYPE", "Sub-Dealer");
            }else 
            {
            	  session.setAttribute("USERTYPE", "Retailer");
            }
            
            session.setAttribute("USERLEVEL", bean.getUserLevel().trim());
            session.setAttribute("PID", bean.getPid());
            session.setAttribute("AID", bean.getAid());
            session.setAttribute("type", bean.getType());
            session.setAttribute("walletid", bean.getWalletid());
            session.setAttribute("paymenttype", bean.getPaymenttype());
            session.setAttribute("runmode", bean.getRunmode());
            session.setAttribute("partnername", bean.getPartnername());
            session.setMaxInactiveInterval(60*15);

            logger.info("Login Success.");
            logger.info("Username is "+ bean.getUserName() + ",Userlevel: "+bean.getUserLevel()+", pid: "+bean.getPid()+",aid: "+bean.getAid()+",type: "+bean.getPaymenttype() +",walletid: "+bean.getWalletid());

        	return success;
    
	    }else{
	    	logger.info("Login Failed. Please Try Again");
	    	fail.addObject("success", "no");
	    	fail.addObject("msg", "*Incorrect Username or Password.");
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
