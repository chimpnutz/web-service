package com.tapsend.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

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

import com.tapsend.dao.impl.UserDAOImpl;
import com.tapsend.model.Application;
import com.tapsend.model.User;
import com.tapsend.utils.Md5Hasher;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("sess")

public class LoginController {
	
	@Autowired
	private UserDAOImpl userDAOImpl;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/auth")
	public String auth(@ModelAttribute("loginForm") User user, BindingResult result, Model model, HttpSession session){
		try{
			Md5Hasher md = new Md5Hasher();
			User checkThisUser = new User();
			System.out.println(user.getUser_id());
			System.out.println(user.getPassword());
			checkThisUser.setUser_id(user.getUser_id().trim());
			checkThisUser.setPassword(md.md5(user.getPassword().trim()));
			
			int isExisting = userDAOImpl.checkIfExists(checkThisUser);
			ArrayList<String> sess = new ArrayList<String>();
			User userContainer = new User();
			ArrayList<User> thisList = new ArrayList<User>();
			System.out.println(isExisting);
			if(isExisting == 1)
			{
				thisList = (ArrayList<User>) userDAOImpl.findUser(checkThisUser);
				Md5Hasher md5 = new Md5Hasher();
				

				sess.add(thisList.get(0).getUser_id());
				sess.add(thisList.get(0).getRole());
				sess.add(thisList.get(0).getCompany_id());
				sess.add(thisList.get(0).getType());
				sess.add(UUID.randomUUID().toString().toUpperCase());
				model.addAttribute("sess",sess);
				
				if(thisList.get(0).getRole().equals("0")){
				
					User users = new User();
					Collection s = null;
					
					s= userDAOImpl.getallUser2();			
					model.addAttribute("parent",s);	
					
					return "admin/superadmin-home";
				}
				else if(thisList.get(0).getRole().equals("1")){
					
					if(thisList.get(0).getCompany_id().equals("pagibig")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);					
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "pagibig/pagibig-home";
					}
					if(thisList.get(0).getCompany_id().equals("sss")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "sss/sss-home";
					}
					if(thisList.get(0).getCompany_id().equals("philhealth")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "philhealth/philhealth-home";
					}
				
				}
				else if(thisList.get(0).getRole().equals("2")){
					
					if(thisList.get(0).getCompany_id().equals("pagibig")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "pagibig/pagibig-home";
					}
					if(thisList.get(0).getCompany_id().equals("sss")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "sss/sss-home";
					}
					if(thisList.get(0).getCompany_id().equals("philhealth")){
						
						User users = new User();
						Collection s = null;
						
						s= userDAOImpl.findUser(users);			
						model.addAttribute("details",s);
						model.addAttribute("usertype", thisList.get(0).getType());
						model.addAttribute("role",thisList.get(0).getRole());
						model.addAttribute("company",thisList.get(0).getCompany_id());
						session.setAttribute("ROLES", thisList.get(0).getRole());
						return "philhealth/philhealth-home";
					}
				
				}
				else{
					
					model.addAttribute("error2","Unauthorized Accesss");
					model.addAttribute("role",thisList.get(0).getRole());
				}
				
			}else{
				model.addAttribute("error","Please Enter Username or Password");
				
				return "loginpage";
			}
		}catch(NullPointerException e){
			
		}
		
		
		return "loginpage";

	}
	@RequestMapping(value="/")
	public String loginPage(@ModelAttribute("loginForm") User user, BindingResult result, Model model){
		return "loginpage";
		
	}


}

