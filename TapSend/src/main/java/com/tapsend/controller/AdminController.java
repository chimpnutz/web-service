package com.tapsend.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
@SessionAttributes("sess")
public class AdminController {
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="/superadmin-home", method=RequestMethod.GET)
	public ModelAndView viewAdminHome(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-home");
		
	
//		Collection s = null;
//		
//		s= userDAOImpl.findUser(user);			

//		Collection c = null;
//		Collection d = null;
//		
//		d= userDAOImpl.getallUser(user);
		List<User> c=userDAOImpl.getallUser2();

		model.addAttribute("parent",c);
		return modelAndView;
	}
	
	@RequestMapping(value="/superadmin-encoded", method=RequestMethod.GET)
	public ModelAndView viewAdminEncoded(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-encoded");
		
		modelAndView.addObject("roles", session.getAttribute("ROLE"));
		return modelAndView;
	}
	
	@RequestMapping(value="/superadmin-return", method=RequestMethod.GET)
	public ModelAndView viewAdminReturn(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-return");
		
		modelAndView.addObject("roles", session.getAttribute("ROLE"));
		return modelAndView;
	}
	
	@RequestMapping(value="/superadmin-application-member", method=RequestMethod.GET)
	public ModelAndView viewAdminApplication(Model model,HttpSession session, @RequestParam("userid") String id) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-application-member");
		
		 User user = new User();
		
		 
		 user.setId(id);
//		 ArrayList<User> userInfo = (ArrayList<User>) userDAOImpl.findUser(user);
		
		 Gson gson2 = new Gson();
		 
	     ArrayList<User> users = (ArrayList<User>) userDAOImpl.ViewUsersDetail(id);
	   
		 ArrayList<UserDetails> s = new ArrayList<UserDetails>();
	     
	     for(User a:users){
	    	 
	    	 	UserDetails details=gson2.fromJson(a.getDetails(),UserDetails.class);
	    	 	details.toString();
	    	    s.add(details);

	    	}
	     Collection b = null;
			
		b= userDAOImpl.findUser(user);
		
		Collection c = null;
		c= userDAOImpl.getEmployer(user);
		
		Collection d = null;
		d= userDAOImpl.getBeneficiary(user);
	     

		
		model.addAttribute("ben",d);
		model.addAttribute("emp",c);
	    model.addAttribute("user",b);
	    model.addAttribute("details",s);
		return modelAndView;
	}
	
	@RequestMapping(value="/superadmin-adduser", method=RequestMethod.GET)
	public ModelAndView AddAdminUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-adduser");
		
		modelAndView.addObject("roles", session.getAttribute("ROLE"));
		return modelAndView;
	}
	
	@RequestMapping(value="/superadmin-userlist", method=RequestMethod.GET)
	public ModelAndView ViewAdminUser(Model model,HttpSession session) throws SQLException{
		
		ModelAndView modelAndView = new ModelAndView("admin/superadmin-userlist");
		
		modelAndView.addObject("roles", session.getAttribute("ROLE"));
		return modelAndView;
	}
	
//	@RequestMapping(value="/superadmin-employer", method=RequestMethod.GET)
//	public ModelAndView viewEmployer(Model model,HttpSession session) throws SQLException{
//		
//		ModelAndView modelAndView = new ModelAndView("admin/superadmin-employer");
//		
//		User user = new User();
//		
//		Collection c = null;
//		c= userDAOImpl.getEmployer(user);
//	     
//		model.addAttribute("emp",c);
//		return modelAndView;
//	}

}
