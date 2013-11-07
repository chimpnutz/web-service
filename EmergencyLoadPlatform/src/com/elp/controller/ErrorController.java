package com.elp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("error")
public class ErrorController {
	
	private static final Logger logger = Logger.getLogger(ErrorController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView logout(ModelMap model,HttpServletRequest request,HttpSession session) {

		ModelAndView modelAndView = new ModelAndView("error");
		
		return modelAndView;
				
	}

}
