package com.circles.controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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

import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.CommentDAOImpl;
import com.circles.dao.impl.UserDAOImpl;
import com.circles.utils.*;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")
public class ManagerController {
	
	@Autowired
	ApplicationDAOImpl applicationDAOImpl;
	@Autowired
	UserDAOImpl userDAOImpl;
	@Autowired
	CommentDAOImpl commentDAOImpl;
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesmtd", method = RequestMethod.GET)
	public String mtd(Model model,HttpSession session){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		ArrayList<String> user = (ArrayList<String>) session.getAttribute("sess");

		if(user.get(0)!="" && user.get(1)!="" && user.get(2)!="" && !user.get(0).isEmpty() && !user.get(1).isEmpty() && !user.get(2).isEmpty()){
			if(user.get(1).equals("1")){
				return "manager/salesmtd";
			}		
		}

		return "error1045";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesytd", method = RequestMethod.GET)
	public String ytd(Model model,HttpSession session){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		ArrayList<String> user = (ArrayList<String>) session.getAttribute("sess");
		if(user.get(0)!="" && user.get(1)!="" && user.get(2)!="" && !user.get(0).isEmpty() && !user.get(1).isEmpty() && !user.get(2).isEmpty()){
			if(user.get(1).equals("1")){
				return "manager/salesytd";
			}		
		}

		return "error1045";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesmonthly", method = RequestMethod.GET)
	public String salesmonthly(Model model,HttpSession session){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		ArrayList<String> user = (ArrayList<String>) session.getAttribute("sess");
		if(user.get(0)!="" && user.get(1)!="" && user.get(2)!="" && !user.get(0).isEmpty() && !user.get(1).isEmpty() && !user.get(2).isEmpty()){
			if(user.get(1).equals("1")){
				return "manager/salesmonthly";
			}		
		}

		return "error1045";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesyearly", method = RequestMethod.GET)
	public String salesyearly(Model model,HttpSession session){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		ArrayList<String> user = (ArrayList<String>) session.getAttribute("sess");
		if(user.get(0)!="" && user.get(1)!="" && user.get(2)!="" && !user.get(0).isEmpty() && !user.get(1).isEmpty() && !user.get(2).isEmpty()){
			if(user.get(1).equals("1")){
				return "manager/salesyearly";
			}		
		}

		return "error1045";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String getExistingApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,Model model) throws SQLException{
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection s = null;	
		int role = 0;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication(application);

		}
		model.addAttribute("application",s);
		return "manager/recent"; 
	}
	@RequestMapping(value="/search2", produces="application/json")
	public @ResponseBody Collection getExistingApplication1(
			@RequestParam(value="application_id",required=true) String applicationid,Model model) throws SQLException{
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection s = null;	
		int role = 0;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication(application);

		}
		model.addAttribute("application",s);
		return s; 
	}
	
	@RequestMapping(value="/recent2", produces="application/json")
	public @ResponseBody Collection getExistingApplications2(Model model){
		Application application = new Application();
		Collection s = null;	
		s= applicationDAOImpl.findAllApplication();

		model.addAttribute("application",s);
		return s;		
	}
	@RequestMapping(value="/recent", method = RequestMethod.GET)
	public String getExistingApplications(Model model) throws SQLException{
		Application application = new Application();
		
		Collection s = null;
		Collection c = null;
		s= applicationDAOImpl.findAllApplication();
		c= commentDAOImpl.findAllComment();
		model.addAttribute("application",s);
		model.addAttribute("comment",c);
		return "manager/recent";		
	}
	@RequestMapping(value="/approved", method = RequestMethod.GET)
	public String viewApproved(Model model) throws SQLException{
		Application application = new Application();
		application.setStatus("1");

		Collection s = null;	
	
			s= applicationDAOImpl.findApplication(application);
		    
//			--------------asdas
		model.addAttribute("application",s);
		return "manager/approved";		
	}
	@RequestMapping(value="/notapproved", method = RequestMethod.GET)
	public String viewDenied(Model model) throws SQLException{
		Application application = new Application();
		application.setStatus("2");

		Collection s = null;	

			s= applicationDAOImpl.findApplication(application);
		

		model.addAttribute("application",s);
		return "manager/notapproved";		
	}
	@RequestMapping(value="/ongoing", method = RequestMethod.GET)
	public String viewPending(Model model) throws SQLException{
		Application application = new Application();
		application.setStatus("2");

		Collection s = null;	

			s= applicationDAOImpl.findApplication(application);
		

		model.addAttribute("application",s);
		return "manager/encoded";		
	}
	@RequestMapping(value="/view", method = RequestMethod.GET,produces="application/json")
	public String viewApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,Model model) throws SQLException{
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Comment comment = new Comment();
		comment.setApplication_id(applicationid);		
		Collection s = null;
		Collection c = null;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication(application);
			c = commentDAOImpl.findComment(comment);
		}
		
		
		model.addAttribute("application",s);
		model.addAttribute("comment",c);
		return "manager/view";			
	}
	@RequestMapping(value="/login3",produces="application/json")
	public @ResponseBody String getLogin(
			@RequestParam(value="user",required=true) String userid,
			@RequestParam(value="password",required=true) String password,Model model){
		User user = new User();
		user.setUserid(userid);
		
		Md5Hasher hasher = new Md5Hasher();
		user.setPassword(hasher.md5(password));
		int isExisting = userDAOImpl.checkIfExists(user);

		if(isExisting == 1){
			return "successs";
		}	
		return "Something went terribly wrong";
	}






}
