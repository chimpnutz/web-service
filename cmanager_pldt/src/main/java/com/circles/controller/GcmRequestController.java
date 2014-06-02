package com.circles.controller;

import java.io.IOException;
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
import com.circles.dao.impl.GcmDAOImpl;
import com.circles.utils.*;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")
public class GcmRequestController {
	
	@Autowired
	private GcmDAOImpl gcmDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(GcmRequestController.class);

	private static final String GOOGLE_SERVER_KEY = "AIzaSyCCoMqyTb6-CZPDssq4OPDJj0bOdhgP62Y";
	
	
	

	@RequestMapping(value="/Activate",produces="application/json")	
	public @ResponseBody  String saveUser (

			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value= "regid", required = true) String regid,
			@RequestParam(value = "activation", required = false) String activation,
			@RequestParam(value = "imei", required = false) String imei,
			@RequestParam(value = "mac", required = false) String mac,
			@RequestParam(value = "phone", required = false) String phone)throws NullPointerException{
		Gcm gcm = new Gcm();
		Comment comment = new Comment();
		String commentUID = "{"+UUID.randomUUID().toString()+"}";
		gcm.setUsername(username);
		gcm.setActivation(activation);
		//int isExisting = gcmDAOImpl.checkIfExists(userParams);ys
		System.out.println("Activation code? "+username);
		System.out.println("Activation code? "+regid);
		System.out.println("Activation code? "+activation);
		System.out.println("Activation code? "+imei);
		System.out.println("Activation code? "+phone);
		
		int ifActivationExists = gcmDAOImpl.checkActivationIfExists(gcm);
		System.out.println("Activation code? "+ifActivationExists);
		
		
		
		if(ifActivationExists == 1)
		{
			
			gcm.setId(commentUID);
			
			gcm.setRegid(regid);
			
			gcm.setImei(imei);
			gcm.setMac(mac);
			String date  = ""+new Date().getTime()+"";
			gcm.setCreated(date);
			gcm.setPhone(phone);
			System.out.println(imei);
			int check = gcmDAOImpl.checkIfExists(gcm);
			System.out.println("Existing?"+check);
			int s = 0;
			
			if(check == 1)
			{
				gcm = new Gcm();
				gcm.setRegid(regid);
				gcm.setImei(imei);
				gcm.setId(commentUID);
				gcm.setMac(mac);
				gcm.setUsername(username);
				
				gcm.setActivation(activation);
				s = gcmDAOImpl.update(gcm);
			}
			else{
				s = gcmDAOImpl.update2(gcm);
			}
			
			
			if(s == 1){
				return "1";		
			}
			else return "-1";
		}
		else return "-1";
		
	}
	
	@RequestMapping(value="/registerGCM",produces="application/json")	
	public @ResponseBody  String registerUser (

			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value= "regid", required = true) String regid,
			@RequestParam(value = "activation", required = false) String activation,
			@RequestParam(value = "imei", required = false) String imei)throws NullPointerException, IOException{
		Gcm gcm = new Gcm();
		Comment comment = new Comment();
		Result result = null;

		String commentUID = "{"+UUID.randomUUID().toString()+"}";
		 Sender sender = new Sender(GOOGLE_SERVER_KEY);
	        Message messages = new Message.Builder().timeToLive(30)
	            .delayWhileIdle(true).addData("m", "Test").build();
	        result = sender.send(messages, regid, 1);
	        
		return result.toString();
	}


}
