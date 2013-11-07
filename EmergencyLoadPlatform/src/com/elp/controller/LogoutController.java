package com.elp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.elp.model.LoginModel;

@Controller
@RequestMapping("logout")
public class LogoutController {
	
	private static final Logger logger = Logger.getLogger(LogoutController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView logout(ModelMap model,HttpServletRequest request,HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("redirect");
		//ModelAndView modelAndView = new ModelAndView("redirect:main.html");
		
		//modelAndView.addObject("loginForm", new LoginModel());
		modelAndView.addObject("logout", "yes");
		

		session.invalidate();

		return modelAndView;
				
	}
	
	@RequestMapping(method = {RequestMethod.GET},params="expire=yes")
		 public ModelAndView sessionexpire(ModelMap model,HttpServletRequest request,HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("redirect");
		
		modelAndView.addObject("logoutRed", "yes");
		
		session.invalidate();

		return modelAndView;
				
	}
	
	@RequestMapping(method = RequestMethod.GET,params={"redirect"})
	 public ModelAndView logoutRedirect(@RequestParam("redirect") String redirect,
	    		HttpServletRequest request,HttpSession session) 
	{

	ModelAndView modelAndView = new ModelAndView("redirect");
	
	modelAndView.addObject("redirect", redirect);
	
	session.invalidate();

	return modelAndView;
			
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
