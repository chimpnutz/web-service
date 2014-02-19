package com.circles.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.circles.controller.LogoutController;

@Controller
@RequestMapping("logout")
public class LogoutController {

private static final Logger logger = Logger.getLogger(LogoutController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView logout(ModelMap model,HttpServletRequest request,HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("logout");		
		
		modelAndView.addObject("logout", "yes");
		
		session.invalidate();

		return modelAndView;
				
	}
	
}
