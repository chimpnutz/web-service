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
import com.tapsend.controller.PagibigController;
import com.tapsend.dao.impl.ApplicationDAOImpl;
import com.tapsend.dao.impl.UserDAOImpl;
import com.tapsend.model.User;
import com.tapsend.model.UserDetails;

@Controller
@SessionAttributes("user_id")
public class PagibigController {
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Autowired
	ApplicationDAOImpl applicationDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(PagibigController.class);
	
	@RequestMapping(value="/pagibig-home", method=RequestMethod.GET)
	public ModelAndView viewPagibigHome(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-home");
		
		User user = new User();
		Collection s = null;
		
		s= userDAOImpl.findUser(user);			
		model.addAttribute("details",s);
	   
	
		return modelAndView;
	}
	
	@RequestMapping(value="/pagibig-return", method=RequestMethod.GET)
	public ModelAndView viewPagibigReturn(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-return");

		return modelAndView;
	}
	
	@RequestMapping(value="/pagibig-encoded", method=RequestMethod.GET)
	public ModelAndView viewPagibigEncoded(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-encoded");

		return modelAndView;
	}
	
	@RequestMapping(value="/pagibig-application", method=RequestMethod.GET)
	public ModelAndView viewPagibigApplicationForm(Model model,HttpSession session,
			@RequestParam("userid") String id) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-application");
		
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
	
	@RequestMapping(value="/pagibig-application-employer", method=RequestMethod.GET)
	public ModelAndView viewPagibigApplicationFormEmployer(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-application-employer");

		return modelAndView;
	}
	@RequestMapping(value="/pagibig-adduser", method=RequestMethod.GET)
	public ModelAndView PagibigAddUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-adduser");

		return modelAndView;
	}
	
	@RequestMapping(value="/pagibig-userlist", method=RequestMethod.GET)
	public ModelAndView viewPagibigUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("pagibig/pagibig-userlist");

		return modelAndView;
	}
	

}
