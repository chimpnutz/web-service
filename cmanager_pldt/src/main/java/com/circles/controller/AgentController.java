package com.circles.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.circles.dao.impl.AgentDAOImpl;
import com.circles.dao.impl.GcmDAOImpl;
import com.circles.model.Gcm;
import com.circles.model.MailModel;
import com.circles.model.User;
import com.circles.utils.Md5Hasher;

@Controller
@SessionAttributes("userid")
public class AgentController {
	
	@Autowired
	AgentDAOImpl agentDAOImpl;
	
	@Autowired
	GcmDAOImpl gcmDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@RequestMapping(value="/addagent",method = RequestMethod.GET)
	public String viewAddAgent(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
		}
		
		else{
			
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			
			return "manager/addagent";
		}
	}
	
	@RequestMapping(value="/saveAgent",method = RequestMethod.POST)
	public String saveAgent(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result,HttpServletRequest request,
			@RequestParam(value="add",required=false) String add,
			@RequestParam(value="agent_user",required=false) String agent_user,
			@RequestParam(value="agent_email",required=false) String agent_email,
			@RequestParam(value="agent_name",required=false) String agent_name,
			@RequestParam(value="agent_last",required=false) String agent_last,
			@RequestParam(value="agent_pass",required=false) String agent_pass,
			@RequestParam(value="agent_repass",required=false) String agent_repass){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
		}
		
		else{
			
			if(add!=null){
				
				String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				
				String agentUID = "{"+UUID.randomUUID().toString()+"}";
				
				User user = new User();
				
				Gcm gcm = new Gcm();
				
				String date = ""+new Date().getTime()+"";
				
				user.setUsername(agent_user);
				
				int check = agentDAOImpl.checkUser(user);
				System.out.println(check);
				
				System.out.println("is "+agent_pass+" is equals to "+agent_repass+" ?");
				
				if(!agent_email.matches(EMAIL_REGEX)){
					model.addAttribute("check","fail");
					model.addAttribute("valid", "Please input a proper email address");
					model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));
				 	logger.info("Error, Please input a proper email address");
					System.out.println("Error, Please input a proper email address");
					return "manager/addagent";
				}
				else if(!agent_pass.equalsIgnoreCase(agent_repass)){
					model.addAttribute("check","fail");
					model.addAttribute("valid", "Password did not match!");
					model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));
				 	logger.info("Error, Password did not match!");
					System.out.println("Error, Password did not match!");				 	
				 	return "manager/addagent";
				}
				else if(check==1){
					model.addAttribute("check","fail");
					model.addAttribute("valid", "username is already exist!");
					model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));		 	
				 	return "manager/addagent";
				}
				else{
					Md5Hasher md = new Md5Hasher();
					
					user.setId(agentUID);
					user.setEmail(agent_email);
					user.setPassword(md.md5(agent_pass).trim());
					user.setFirstName(agent_name);
					user.setLastName(agent_last);
					user.setRole("3");
					user.setDatecreated(date);
					user.setStatus("0");	
					
					gcm.setUsername(agent_user);
					gcm.setId(agentUID);
					gcm.setActivation(RandomStringUtils.randomAlphanumeric(6));
					gcm.setCreated(date);
					
					int save = gcmDAOImpl.save(gcm);
					int save2 = agentDAOImpl.saveAgent(user);
					
					ApplicationContext a = new ClassPathXmlApplicationContext("Spring-Mail.xml");
					System.out.println(a);
					MailModel mail = (MailModel) a.getBean("mail");
					System.out.println("email sent!");
					
					mail.sendMailRegistration(gcm,user,request);
					logger.info("success");
					
					
					model.addAttribute("check","success");
					model.addAttribute("valid", "agent has been created!");
					model.addAttribute("role",session.getAttribute("ROLE"));
				 	model.addAttribute("user",session.getAttribute("USER"));		 	
				 	return "manager/addagent";
					
				}
			}
			
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			
			return "manager/addagent";
		}
	}
	
}
