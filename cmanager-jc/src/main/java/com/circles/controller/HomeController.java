package com.circles.controller;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;


import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.UserDAOImpl;


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
			@RequestParam(value="invalid",required =false) String invalid) throws IOException{
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd

		model.addAttribute("error","display:none");
		return "index";
	}






}
