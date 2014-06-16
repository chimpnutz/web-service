package com.circles.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.circles.model.*;
import com.circles.dao.impl.ApplicationDAOImpl;
//import com.circles.dao.impl.LoginDAOImpl;
import com.circles.dao.impl.UserDAOImpl;
import com.circles.utils.*;



/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")

public class LoginController {
	
	@Autowired
	private UserDAOImpl userDAOImpl;
	@Autowired
	private ApplicationDAOImpl applicationDAOImpl;
	@Autowired
//	private LoginDAOImpl loginDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/auth", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("loginForm") User loginForm,BindingResult result,HttpServletRequest request){
	
		ModelAndView mainpage = new ModelAndView("index");
		ModelAndView loginpage = new ModelAndView("index");
		
		loginpage.addObject("loginForm",new User());
		mainpage.addObject("loginForm",new User());
		
		HttpSession isSession = request.getSession();
		
		if (null == isSession.getAttribute("USER")) {			
			
			return loginpage;	
		
		} else
			 {		
		    	mainpage.addObject("username",isSession.getAttribute("USER"));	
		    	return mainpage;
			}
		
	}
	@RequestMapping(value="/auth" ,method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginForm") User loginForm,
			BindingResult result,HttpSession session,Model model) throws SQLException{
		
		ModelAndView encoder = new ModelAndView("encoder/encoderrecent");
		ModelAndView manager = new ModelAndView("manager/salesmtd");
		ModelAndView fail = new ModelAndView("index");
		
		if(loginForm.getUsername().equals("")){
			
	    	logger.info("Please input your Username.");
	    	fail.addObject("blankUser", "yes");
	    	fail.addObject("msg", "*Please input your username");
	    	return fail;
			
		}
		
		if(loginForm.getPassword().equals("")){
			
			logger.info("Please input your Password");
			fail.addObject("blankPass", "yes");
	    	fail.addObject("msg", "*Please input your password.");
	    	return fail;
			
		}
		
		Md5Hasher md = new Md5Hasher();
		User mrBean = new User();	
	
		mrBean.setUsername(loginForm.getUsername().trim());
		mrBean.setPassword(md.md5(loginForm.getPassword().trim()));
			
		System.out.println("username: "+mrBean.getUsername());
		System.out.println("password: "+mrBean.getPassword());
		
		int bean2 = userDAOImpl.checkIfExists(mrBean);
		System.out.println("bean: "+bean2);
		
		if(bean2 == 1){
			
			UserBean bean =  userDAOImpl.getUser(mrBean.getUsername(),mrBean.getPassword());
			
			if(bean!=null){
				
				session.setAttribute("USER",loginForm.getUsername());
				
				if(bean.getRole().equals("1")){
					session.setAttribute("ROLE", "manager");
					model.addAttribute("user",session.getAttribute("USER"));
					model.addAttribute("role",session.getAttribute("ROLE"));
					return manager;
				}
				else if(bean.getRole().equals("2")){
					session.setAttribute("ROLE", "encoder");
					Application application = new Application();
					Collection s = null;
					
					s= applicationDAOImpl.getRecentApplication(application);
					model.addAttribute("application",s);
					model.addAttribute("role",session.getAttribute("ROLE"));
					model.addAttribute("user",session.getAttribute("USER"));
					return encoder;
				}
				else{
					fail.addObject("validate","invalid");
					fail.addObject("error","Please Enter Username or Password");
					return fail;
				}
			}else{
				fail.addObject("validate","invalid");
				fail.addObject("error","Please Enter Username or Password");
				return fail;
			}
						
		}else{
			fail.addObject("validate","invalid");
			fail.addObject("error","Please Enter Username or Password");
			return fail;
		}
	
		
	}
	
	
	
	
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value="/auth")
//	public String auth(
//			@ModelAttribute("loginForm") User user,
//			Model model,HttpServletRequest request,HttpSession issession, BindingResult result){
//		try{
//			issession.setAttribute("USER", user.getUsername().trim());
//			System.out.println("username:"+issession.getAttribute("USER"));
//			
//			
//			HttpSession session = request.getSession();
//		
//		if(null == session.getAttribute("USER")){
//			return "index";
//		}
//
//		else
//		{
//			Md5Hasher md = new Md5Hasher();
//			User checkThisUser = new User();
//			checkThisUser.setUsername(user.getUsername().trim());
//			System.out.println(user.getUsername().trim());
//			checkThisUser.setPassword(md.md5(user.getPassword().trim()));
//			int isExisting = userDAOImpl.checkIfExists(checkThisUser);
//
//			ArrayList<String> sess = new ArrayList<String>();
//			User userContainer = new User();
//			ArrayList<User> thisList = new ArrayList<User>();
//		
//			if(isExisting == 1){
//
//				thisList = (ArrayList<User>) userDAOImpl.findUser(checkThisUser);
//				Md5Hasher md5 = new Md5Hasher();
//				
//
//				sess.add(thisList.get(0).getId());
//				sess.add(thisList.get(0).getUsername());
//				sess.add(thisList.get(0).getRole());
//				sess.add(UUID.randomUUID().toString().toUpperCase());
//				
//				issession.setAttribute("ROLE", thisList.get(0).getRole());
//				
//				if(thisList.get(0).getRole().equals("1")){
//					return "manager/salesmtd";
//				}
//				Application application = new Application();
//				Collection s = null;
//				
//				
//				s= applicationDAOImpl.getRecentApplication(application);				
//				model.addAttribute("application",s);
//				return "encoder/encoderrecent";
//			
//			
//			
//					
//			}else{
//				model.addAttribute("validate","invalid");
//				model.addAttribute("error","Please Enter Username or Password");
//				return "index";
//				 }
//		}
//	  }catch(NullPointerException e){
//		  return "index";
//	  }
//		
//	}

}
