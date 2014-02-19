package com.circles.controller;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.circles.controller.LoginController;
import com.circles.model.LoginModel;
import com.paysetter.topup.amax.exceptions.AutoloadMaxException;
import com.circles.beans.UserBean;
import com.circles.dao.LoginDAO;


@Controller
@RequestMapping("login")
public class LoginController implements ServletContextAware{

	@Autowired
	private ServletContext servletContext;
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView topupView(HttpServletRequest request) throws NamingException {
		
		ModelAndView mainpage = new ModelAndView("login");
		ModelAndView loginpage = new ModelAndView("login");
		
		loginpage.addObject("loginForm", new LoginModel());
		mainpage.addObject("loginForm", new LoginModel());
		
		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {			
			
				return loginpage;	
			
		} else
				 {		
			    	mainpage.addObject("username",isSession.getAttribute("USER"));	
			    	mainpage.addObject("hide","yes");
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
		
		ModelAndView encoder = new ModelAndView("encodersuccess");
		ModelAndView manager = new ModelAndView("managersuccess");
		ModelAndView fail = new ModelAndView("login");
		
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
		
        if(bean!=null)
        { 	
        	
        	
            session.setAttribute("USER", bean.getUserName());
            
                    
            if(bean.getUserLevel().trim().equals("1"))
            {
            	  session.setAttribute("USERTYPE", "Manager");
            	  
            	  session.setAttribute("USERLEVEL", bean.getUserLevel().trim());
                  session.setMaxInactiveInterval(60*15);

                  logger.info("Login Success.");
                  logger.info("Username is "+ bean.getUserName() + ",Userlevel: "+bean.getUserLevel());

              	  return manager;
            	
            }
            else
            {
            	  session.setAttribute("USERTYPE", "Encoder");
            	 
            	  session.setAttribute("USERLEVEL", bean.getUserLevel().trim());
                  session.setMaxInactiveInterval(60*15);

                  logger.info("Login Success.");
                  logger.info("Username is "+ bean.getUserName() + ",Userlevel: "+bean.getUserLevel());

              	  return encoder;
            }
            
            
    
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
