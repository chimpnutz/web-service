package com.circles.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.circles.model.*;
import com.circles.dao.impl.UserDAOImpl;
import com.circles.utils.*;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("sess")

public class LoginController {
	
	@Autowired
	private UserDAOImpl userDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@RequestMapping(value="/auth")
	public String auth(
			@ModelAttribute("loginForm") User user,
			Model model){
		//System.out.println(userid+" "+password);
		Md5Hasher md = new Md5Hasher();
		User checkThisUser = new User();
		checkThisUser.setUserid(user.getUserid().trim());
		checkThisUser.setPassword(md.md5(user.getPassword().trim()));
		int isExisting = userDAOImpl.checkIfExists(checkThisUser);
		ArrayList<String> sess = new ArrayList<String>();
		User userContainer = new User();
		ArrayList<User> thisList = new ArrayList<User>();
		if(isExisting == 1){
			thisList = (ArrayList<User>) userDAOImpl.findUser(checkThisUser);
			Md5Hasher md5 = new Md5Hasher();
			

			sess.add(thisList.get(0).getUserid());
			sess.add(thisList.get(0).getRole());
			sess.add(UUID.randomUUID().toString().toUpperCase());
			model.addAttribute("sess",sess);
			if(thisList.get(0).getRole().equals("1")){
				return "manager/salesmtd";
			}
			return "encoder/encoderrecent";
					
		}else{
			model.addAttribute("error","");
			
			return "index";
		}
		
	}


}
