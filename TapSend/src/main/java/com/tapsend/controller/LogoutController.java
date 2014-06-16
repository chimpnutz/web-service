package com.tapsend.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tapsend.controller.LogoutController;

@Controller
public class LogoutController {

	
		private static final Logger logger = Logger.getLogger(LogoutController.class);
			
		@RequestMapping(value="/logout", method=RequestMethod.GET)
		public ModelAndView logout(Model model,HttpSession session) throws SQLException{
			
		ModelAndView modelAndView = new ModelAndView("pagibig/logout");		
		
		modelAndView.addObject("logout", "yes");
		
		session.invalidate();
		
		return modelAndView;
		}
}
