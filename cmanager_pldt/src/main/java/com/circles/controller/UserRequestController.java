package com.circles.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

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
@SessionAttributes("userid")
public class UserRequestController {
	
	@Autowired
	private UserDAOImpl userDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRequestController.class);

	
	
	@RequestMapping(value="/getUsers", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getUsers(){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		 Collection s = userDAOImpl.findAllUser();		
		return s;
		
	}
	
	@RequestMapping(value="/selectAllUsers", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection getAlltUsers(){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		 Collection s = userDAOImpl.selectAllUser();		
		return s;
		
	}
	
	@RequestMapping(value="/selectUser", method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  Collection selectUser(
			@RequestParam(value="username",required=false)String username
			){
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		 User user = new User();
		 user.setUsername(username);

		 Collection s = userDAOImpl.findUser(user);		
		return s;
		
	}
	
	@RequestMapping(value="/authJson", method = RequestMethod.GET,produces="application/json")	
	public @ResponseBody  Collection getUser (
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "status", required = false) String status)throws NullPointerException{
		User userParams = new User();
		userParams.setId(id);
		userParams.setUsername(username);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		userParams.setStatus(status);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		Collection s = userDAOImpl.findUser(userParams);

		
		return s;
		
	}
	@RequestMapping(value="/saveJson", method = RequestMethod.GET,produces="application/json")	
	public @ResponseBody  int saveUser (

			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value= "password", required = true) String password,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = true) String role,
			@RequestParam(value = "status", required = false) String status)throws NullPointerException{
		User userParams = new User();
		Md5Hasher md = new Md5Hasher();
		String userUID = "{"+UUID.randomUUID().toString()+"}";
        userParams.setId(userUID);
        int isExisting = userDAOImpl.checkIfExists(userParams);
        if(isExisting == 1){
        	userUID = "{"+UUID.randomUUID().toString()+"}";
		}
        userParams.setId(userUID);
		userParams.setPassword(md.md5(password.trim()));
		userParams.setUsername(username);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		userParams.setStatus(status);
		String date  = ""+new Date().getTime()+"";
		//System.out.println(date);
		userParams.setDatecreated(date);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		int s = userDAOImpl.save(userParams);
		return s;	
	}
	@RequestMapping(value="/updateJson", method = RequestMethod.GET,produces="application/json")	
	public @ResponseBody  int updateUser (
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value= "password", required = false) String password,
			@RequestParam(value = "firstname", required = false) String firstName,
			@RequestParam(value = "middlename", required = false) String middleName,
			@RequestParam(value = "lastname", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "status", required = false) String status)throws NullPointerException{
		User userParams = new User();
		Md5Hasher md = new Md5Hasher();
		
		try{
			if(!password.isEmpty()||!password.equals(null)||!password.equals("")){
				userParams.setPassword(md.md5(password.trim()));
			}
		}catch(NullPointerException e){
			
		}
	
		userParams.setId(id);
		userParams.setUsername(username);
		userParams.setFirstName(firstName);
		userParams.setMiddleName(middleName);
		userParams.setLastName(lastName);
		userParams.setEmail(email);	
		userParams.setRole(role);
		userParams.setStatus(status);
		//int isExisting = userDAOImpl.checkIfExists(userParams);
		int s = userDAOImpl.update(userParams,id);
		return s;	
	}

}
