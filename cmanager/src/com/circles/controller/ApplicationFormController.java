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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.circles.dao.EncoderDAO;
import com.circles.model.ApplicationModel;

@Controller
@RequestMapping("applicationform")
public class ApplicationFormController {
	
	private static final Logger logger = Logger.getLogger(ApplicationFormController.class);

	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(method = RequestMethod.GET,params={"appid"})
	 public ModelAndView ApplicationView(@ModelAttribute("applicationForm") ApplicationModel applicationForm,BindingResult result,@RequestParam("appid") int id,ModelMap model,HttpServletRequest request,HttpSession usersession) {
		
		ApplicationContext  context = new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
		ModelAndView modelAndView = new ModelAndView("applicationform");
		ModelAndView redirect = new ModelAndView("redirect:login.html");
		
		modelAndView.addObject("applicationForm");
		
		EncoderDAO dao = (EncoderDAO) context.getBean("encoderDAO");

		HttpSession isSession = request.getSession();

		if (null == isSession.getAttribute("USER")) {	
			
			redirect.addObject("login", "no");
			return redirect;	
		
		} else {
			
			List<ApplicationModel>appList = dao.getApplicationList(usersession, id);
			modelAndView.addObject("applist", appList);
			modelAndView.addObject("user",isSession.getAttribute("USERLEVEL"));
	
			return modelAndView;
					
		}
		
					
	}
}
