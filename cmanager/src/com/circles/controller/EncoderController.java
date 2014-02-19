package com.circles.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.circles.controller.EncoderController;
import com.circles.model.ApplicationModel;
import com.circles.dao.EncoderDAO;
import com.circles.model.ApplicationModel;

@Controller
@RequestMapping("encoder")
public class EncoderController {
	
	private static final Logger logger = Logger.getLogger(EncoderController.class);

	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET)
	 public ModelAndView WalletView(ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ModelAndView modelAndView = new ModelAndView("encoder");
		ModelAndView redirect = new ModelAndView("redirect:login.html");
		
		EncoderDAO dao = (EncoderDAO) context.getBean("encoderDAO");

		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {	
			
			redirect.addObject("login", "no");
			return redirect;	
		
		} else {

			List<ApplicationModel>appList = dao.getRecentEncoderList(usersession);
			modelAndView.addObject("applist", appList);
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));

			return modelAndView;
				
		}

		
	}
	
	
}
