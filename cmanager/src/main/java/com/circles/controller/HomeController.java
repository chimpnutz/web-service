package com.circles.controller;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.UserDAOImpl;
import com.circles.utils.*;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")
public class HomeController {
	
	@Autowired
	ApplicationDAOImpl applicationDAOImpl;
	@Autowired
	UserDAOImpl userDAOImpl;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/")
	public String home(@ModelAttribute("loginForm") User user, Model model,
			@RequestParam(value="invalid",required =false) String invalid){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		model.addAttribute("error","display:none");
		return "index";
	}






}
