package com.circles.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.circles.controller.LogoutController;
import com.circles.model.User;

@Controller

public class LogoutController {

private static final Logger logger = Logger.getLogger(LogoutController.class);
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	 public String logout(ModelMap model,HttpServletRequest request,HttpSession session,@ModelAttribute("loginForm") User user) {

		ModelAndView modelAndView = new ModelAndView("logout");		
		
		modelAndView.addObject("logout", "yes");
		
		session.invalidate();

		return "index";
				
	}
	
}
