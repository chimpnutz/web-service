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
import com.google.gson.GsonBuilder;
import com.tapsend.dao.impl.UserDAOImpl;
import com.tapsend.model.User;
import com.tapsend.model.UserDetails;

@Controller
@SessionAttributes("user_id")
public class PhilhealthController {
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(PhilhealthController.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/philhealth-home", method=RequestMethod.GET)
	public ModelAndView viewPhilhealthHome(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-home");
		
		User user = new User();
		Collection s = null;
		
		s= userDAOImpl.findUser(user);			
		model.addAttribute("details",s);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/philhealth-encoded", method=RequestMethod.GET)
	public ModelAndView viewPhilhealthEncoded(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-encoded");

		return modelAndView;
	}
	
	@RequestMapping(value="/philhealth-return", method=RequestMethod.GET)
	public ModelAndView viewPhilhealthReturn(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-return");

		return modelAndView;
	}
	
	@RequestMapping(value="/philhealth-application", method=RequestMethod.GET)
	public ModelAndView viewPhilhealthApplication(Model model,HttpSession session, @RequestParam("userid") String id) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-application");
		
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
	
	@RequestMapping(value="/philhealth-userlist", method=RequestMethod.GET)
	public ModelAndView viewPhilhealthUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-userlist");

		return modelAndView;
	}
	
	@RequestMapping(value="/philhealth-adduser", method=RequestMethod.GET)
	public ModelAndView addPhilhealthUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("philhealth/philhealth-adduser");

		return modelAndView;
	}

}
