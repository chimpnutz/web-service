package com.tapsend.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tapsend.dao.impl.UserDAOImpl;
import com.tapsend.model.User;
import com.tapsend.model.UserDetails;

@Controller
@SessionAttributes("user_id")
public class SssController {

	@Autowired
	UserDAOImpl userDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(SssController.class);
	
	@RequestMapping(value="/sss-home", method=RequestMethod.GET)
	public ModelAndView viewSssHome(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-home");
		
		User user = new User();
		Collection s = null;
		
		s= userDAOImpl.findUser(user);			
		model.addAttribute("details",s);

		return modelAndView;			
	}
	
	@RequestMapping(value="/sss-encoded", method=RequestMethod.GET)
	public ModelAndView viewSssEncoded(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-encoded");

		return modelAndView;
	}
	
	@RequestMapping(value="/sss-return", method=RequestMethod.GET)
	public ModelAndView viewSssReturn(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-return");

		return modelAndView;
	}
	
	@RequestMapping(value="/sss-application-member", method=RequestMethod.GET)
	public ModelAndView viewSssApplication(Model model,HttpSession session, @RequestParam("userid") String id) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-application-member");
		
		 User user = new User();
		 user.setId(id);
		 
		 Gson gson2 = new Gson();
		 user.setType("pagibig");
	     ArrayList<User> users = (ArrayList<User>) userDAOImpl.ViewUsersDetail(id);
	     
		 ArrayList<UserDetails> s = new ArrayList<UserDetails>();
	     
	     for(User a:users){
	    	 
	    	 	UserDetails details=gson2.fromJson(a.getDetails(),UserDetails.class);
	    	 
	    	    s.add(details);

	    	}
	    	model.addAttribute("details",s);

		return modelAndView;
	}
	
	@RequestMapping(value="/sss-userlist", method=RequestMethod.GET)
	public ModelAndView viewSssUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-userlist");

		return modelAndView;
	}
	
	@RequestMapping(value="/sss-adduser", method=RequestMethod.GET)
	public ModelAndView addSssUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("sss/sss-adduser");

		return modelAndView;
	}
}
