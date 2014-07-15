package com.circles.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.circles.model.*;
import com.circles.dao.impl.*;
import com.circles.utils.*;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.android.gcm.server.MulticastResult;

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
	ImageDAOImpl imageDAOImpl;
	@Autowired
	ProductDAOImpl productDAOImpl;
	@Autowired
	CommentDAOImpl commentDAOImpl;
	@Autowired
	GcmDAOImpl gcmDAOImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model){
		//int isExisting = userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		return "home";
	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesmtd", method = RequestMethod.GET)
	public String mtd(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
	
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	 	model.addAttribute("role",session.getAttribute("ROLE"));
	    	 	model.addAttribute("user",session.getAttribute("USER"));
	    	 	return "manager/salesmtd";
	    }
	
				

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesytd", method = RequestMethod.GET)
	public String ytd(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	 	model.addAttribute("role",session.getAttribute("ROLE"));
	    	 	model.addAttribute("user",session.getAttribute("USER"));
	    	 	return "manager/salesytd";
	    }
		
	
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesmonthly", method = RequestMethod.GET)
	public String salesmonthly(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	 	model.addAttribute("role",session.getAttribute("ROLE"));
	    	 	model.addAttribute("user",session.getAttribute("USER"));
	    	 	return "manager/salesmonthly";
	    }

				

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/salesyearly", method = RequestMethod.GET)
	public String salesyearly(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
	    	 	model.addAttribute("role",session.getAttribute("ROLE"));
	    	 	model.addAttribute("user",session.getAttribute("USER"));
	    	 	return "manager/salesyearly";

	    }
		
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String getExistingApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,
			Model model,
			HttpSession session) throws SQLException{
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection s = null;	
		int role = 0;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication2(application);

		}
		model.addAttribute("application",s);
		return "manager/recent"; 
	}
	@RequestMapping(value="/search2", produces="application/json")
	public @ResponseBody Collection getExistingApplication1(
			@RequestParam(value="application_id",required=true) String applicationid,
			Model model,
			HttpSession session) throws SQLException,ConversionNotSupportedException{
//		checksession(session);
		Application application = new Application();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection s = null;	
		int role = 0;
		if(isExisting == 1){
			s= applicationDAOImpl.findApplication2(application);

		}
		model.addAttribute("application",s);
		return s; 
	}
	
	public boolean checksession(HttpSession session) throws ConversionNotSupportedException
	{
		boolean flg = true;

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		String imei	= (String) session.getAttribute("imei");
		if (username == null || imei == null)
			{
			 throw new ConversionNotSupportedException("unauthorized", null, null);
			}
		return flg;
	}
	
	@RequestMapping(value="/recent2", produces="application/json")
	public @ResponseBody Collection getExistingApplications2(Model model,
			HttpSession session)throws ConversionNotSupportedException{
		checksession(session);
		Application application = new Application();
		Collection s = null;	
		s= applicationDAOImpl.findAllApplication();

		model.addAttribute("application",s);
		return s;		
	}
	@RequestMapping(value="/recent")
	public String getExistingApplications(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{

			Application application = new Application();
			Collection s = null;	
			
			s= applicationDAOImpl.getRecentApplication(application);
		
			
			model.addAttribute("application",s);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/recent";
	    }
	}
	
	@RequestMapping(value="/agent", method = RequestMethod.GET)
	public String getExistingAgents(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result){
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
		
			Application application = new Application();
			User users = new User();
			Collection s = null;	
			Collection a = null;
			ArrayList<String> user = (ArrayList<String>) session.getAttribute("sess");
		
			s= applicationDAOImpl.findAllApplication();
			a= userDAOImpl.getAgents(users);
			model.addAttribute("application",s);
			model.addAttribute("agent",a);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/agent";
	    }
	}
	
	//cut products
	
	@RequestMapping(value="/approved", method = RequestMethod.GET)
	public String viewApproved(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
			Application application = new Application();
			application.setStatus("1");
	
			Collection s = null;	
		
				s= applicationDAOImpl.getEncodedApplication(application);
			
	
			model.addAttribute("application",s);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/approved";	
	    }
	}
	@RequestMapping(value="/notapproved", method = RequestMethod.GET)
	public String viewDenied(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
			Application application = new Application();
			application.setStatus("2");
	
			Collection s = null;	
	
				s= applicationDAOImpl.getReturnApplication(application);
			
	
			model.addAttribute("application",s);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/notapproved";	
	    }
	}
	@RequestMapping(value="/ongoing", method = RequestMethod.GET)
	public String viewPending(@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result) throws SQLException{
		
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
			Application application = new Application();
			application.setStatus("2");
	
			Collection s = null;	
	
				s= applicationDAOImpl.getOngoingApplication(application);
			
				
			model.addAttribute("application",s);
			model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
			return "manager/encoded";	
	    }
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewApplication(
			@RequestParam(value="applicationid",required=true) String applicationid,
			@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result) throws SQLException{
			   
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
				Application application = new Application();
			    Comment comment = new Comment();
			    application.setApplication_id(applicationid);
			    int isExisting = this.applicationDAOImpl.checkIfExists(application);
			    Collection s = null;
			    if (isExisting == 1) {
			      s = this.applicationDAOImpl.findApplication3(application);
			    }
			    Image image = new Image();
			    image.setApplication_id(applicationid);
			    comment.setApplication_id(applicationid);
			    model.addAttribute("images", this.imageDAOImpl.getImages(image));
			    model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
			    model.addAttribute("application", s);
			    try {
//			      model.addAttribute("userid", session.getAttribute("username"));
			      model.addAttribute("role",session.getAttribute("ROLE"));
				  model.addAttribute("user",session.getAttribute("USER"));
//			      System.out.println(session.getAttribute("username"));
			    }
			    catch (IllegalArgumentException e) {
			      e.printStackTrace(); 
			      return "index";
			    }

			    return "manager/view";
			  }
		}	
	@RequestMapping(value="/view", method=RequestMethod.POST)
	public String viewApplication2(
			@RequestParam(value="applicationid",required=true) String applicationid,
			@ModelAttribute("loginForm") User loginForm,Model model,
			HttpSession session,BindingResult result) throws SQLException {
			   
		if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
			return "index";
	    }
	    
	    else{
				Application application = new Application();
			    Comment comment = new Comment();
			    application.setApplication_id(applicationid);
			    int isExisting = this.applicationDAOImpl.checkIfExists(application);
			    Collection s = null;
			    if (isExisting == 1) {
			      s = this.applicationDAOImpl.findApplication3(application);
			    }
			    Image image = new Image();
			    image.setApplication_id(applicationid);
			    comment.setApplication_id(applicationid);
			    model.addAttribute("images", this.imageDAOImpl.getImages(image));
			    model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
			    model.addAttribute("application", s);
			    model.addAttribute("applicationid", applicationid);
			    try {
			      model.addAttribute("role",session.getAttribute("ROLE"));
				  model.addAttribute("user",session.getAttribute("USER"));
			      model.addAttribute("userid", session.getAttribute("username"));
			      System.out.println(session.getAttribute("username"));
			    }
			    catch (IllegalArgumentException e) {
			      e.printStackTrace(); return "index";
			    }

			    return "manager/view";
			  }
		}
	
	//@RequestMapping(value="/refreshComments", produces="")
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveCommentM")
	public String saveComment(
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="application_id",required=false) String application_id,
			@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="ispushed",required=false) String ispushed,
			@RequestParam(value="edited_by",required=false)String edited_by,
			@RequestParam(value="created",required=false) String created,
			@RequestParam(value="updated",required=false) String updated,
			@RequestParam(value="version",required=false)String version,
			@RequestParam(value="content",required=false)String content,
			HttpSession session,
			Model model) throws SQLException, IOException{

		Comment comment = new Comment();
		String commentUID = "{"+UUID.randomUUID().toString()+"}";
		
		System.out.println("userid: "+user_id);
		System.out.println("edited_by: "+edited_by);
			
		comment.setId(commentUID);
		comment.setApplication_id(application_id);
		comment.setUser_id(user_id);
		comment.setIspushed("0");
		comment.setEdited_by(edited_by);
		String date = ""+new Date().getTime()+"";
		comment.setCreated(date);
		comment.setUpdated(date);
		comment.setVersion("1");
		comment.setContent(content);
		Resource resource = new ClassPathResource("../properties/filepath.properties");
		
//		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String GOOGLE_SERVER_KEY = "AIzaSyCCoMqyTb6-CZPDssq4OPDJj0bOdhgP62Y";
//				props.getProperty("GOOGLE_API_KEY");
		Gcm gcm = new Gcm();
		Application application = new Application();
		ArrayList<Gcm> copy = null;
		ArrayList<Application> copy2 = null;
		
		String regid ="";
		Collection s = null;
		Collection app_user = null;
		int isSaved = 0;
		int isExisting = commentDAOImpl.checkIfExists(comment);
		System.out.println(isExisting);
		application.setApplication_id(application_id);
		if(isExisting == 0){
			try{
				if(!application_id.isEmpty()){
					isSaved = commentDAOImpl.save(comment);
				}
			}catch(NullPointerException e){
				
			}
		}
		
		
		if(isSaved == 1){
			System.out.println("testing comment of: "+application_id);
			MulticastResult result2 = null;
			Result result = null;
			app_user = applicationDAOImpl.findApplication3(application);
			copy2 = new ArrayList<Application>(app_user);
//			gcm.setUsername(copy2.get(0).getEditedBy());
			gcm.setUsername(session.getAttribute("username")+"");
			System.out.println("testing comment of: "+copy2.get(0).getEditedBy());
			System.out.println("asdsad"+gcm.getUsername());
			s = gcmDAOImpl.findGcm(application_id);
			copy = new ArrayList<Gcm>(s); 
			regid = copy.get(0).getRegid();
			Sender sender = new Sender(GOOGLE_SERVER_KEY);
	        Message messages = new Message.Builder().delayWhileIdle(false).addData("table", "comment").build();
	        //result = sender.send(messages, regid, 5);
	        ArrayList<String> devices = new ArrayList<String>();
	        devices.add(regid);
	        System.out.println("message: "+messages);	
	        System.out.println("regid: "+regid);
	       
	       // result2 = sender.send(messages, devices, 5);
	        result = sender.send(messages, regid, 4);
	        System.out.println("regid: "+regid);
	        System.out.println("result: "+ result.toString());
	        resource = null;
	        Image image = new Image();
	        image.setApplication_id(application_id);
	        model.addAttribute("application",app_user);
	        comment = new Comment();
	        comment.setApplication_id(application_id);
	        model.addAttribute("comment",commentDAOImpl.findComment(comment));
	        model.addAttribute("images",imageDAOImpl.getImages(image));
	        System.out.println(isSaved);
	        model.addAttribute("role",session.getAttribute("ROLE"));
			  model.addAttribute("user",session.getAttribute("USER"));
		      model.addAttribute("userid", session.getAttribute("username"));
	        return "manager/view";
		}
		Image image = new Image();
        image.setApplication_id(application_id);
        model.addAttribute("application",app_user);
        comment = new Comment();
        comment.setApplication_id(application_id);
        model.addAttribute("comment",commentDAOImpl.findComment(comment));
        model.addAttribute("images",imageDAOImpl.getImages(image));
        model.addAttribute("role",session.getAttribute("ROLE"));
		  model.addAttribute("user",session.getAttribute("USER"));
	      model.addAttribute("userid", session.getAttribute("username"));
		return "manager/view";
	}
	
	

	
	public void sendRequest(String request) throws IOException{
		URL url = new URL(request); 
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
	    connection.setDoOutput(true); 
	    connection.setInstanceFollowRedirects(false); 
	    connection.setRequestMethod("POST"); 
	    connection.setRequestProperty("Content-Type", "text/plain"); 
	    connection.setRequestProperty("charset", "utf-8");
	    connection.connect();
	}
	
	
	
	
	
	@RequestMapping(value="/login3",produces="application/json")
	public @ResponseBody String getLogin(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,
			Model model,HttpServletRequest request) throws ConversionNotSupportedException{
		//checksession(session);
		User user = new User();
		Gcm gcm = new Gcm();
		user.setUsername(username);		
		
		Md5Hasher hasher = new Md5Hasher();
		user.setPassword(hasher.md5(password));
		int isExisting = userDAOImpl.checkIfExists(user);

		HttpSession session = request.getSession();
		System.out.println("agent username: "+session.getAttribute("username"));
		if(null == session.getAttribute("username"))
		{
			if(isExisting == 1){
				
				session.setAttribute("user_id", user.getId());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				session.setAttribute("imei", gcm.getImei());
				
				
				return "1";
			}	else
			{
			
					 throw new ConversionNotSupportedException("unauthorized", null, null);
				
				
			}
			
			
		}else{
			if(isExisting == 1){
				
				session.setAttribute("user_id", user.getId());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				session.setAttribute("imei", gcm.getImei());
				
				
				return "1";
			}	else
			{
			
					 throw new ConversionNotSupportedException("unauthorized", null, null);
				
				
			}
		}
		
	
//		if(isExisting == 1){
//			
//			session.setAttribute("user_id", user.getId());
//			session.setAttribute("username", user.getUsername());
//			session.setAttribute("role", user.getRole());
//			session.setAttribute("imei", gcm.getImei());
//			
//			
//			return "1";
//		}	
//		return "-1";
	}
		






}
