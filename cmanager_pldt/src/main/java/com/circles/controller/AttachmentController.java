package com.circles.controller;

import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.ImageDAOImpl;
import com.circles.model.Application;
import com.circles.model.Image;
import com.circles.model.User;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userid"})
public class AttachmentController
{

  @Autowired
  ApplicationDAOImpl applicationDAOImpl;

  @Autowired
  ImageDAOImpl imageDAOImpl;

  @RequestMapping(value={"/attachments2"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, params={"appid"})
  public String viewSalesMonthly(@ModelAttribute("loginForm") User loginForm,BindingResult result,HttpSession session,Model model, @RequestParam("appid") String id)
    throws SQLException
  {
	  if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
	    	return "index";
	    }
	  else{
		    Application application = new Application();
		
		    Collection s = null;
		    application.setApplication_id(id);
		    s = this.applicationDAOImpl.findApplication3(application);
		    Image image = new Image();
		    image.setApplication_id(id);
		
		    Collection s2 = this.imageDAOImpl.getImages(image);
		    ArrayList a = new ArrayList(s2);
		//    System.out.println(((Image)a.get(1)).getType());
		    ArrayList b = new ArrayList(s);
//		    System.out.println("SSS:" + ((Application)b.get(0)).getDoc_identity_sss_no());
		    model.addAttribute("images", this.imageDAOImpl.getImages(image));
		
		    model.addAttribute("application", s);
		    model.addAttribute("role",session.getAttribute("ROLE"));
		 	model.addAttribute("user",session.getAttribute("USER"));
		    return "encoder/attachments2";
	  }
  }

  @RequestMapping(value={"/attachments3"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, params={"appid"})
  public String viewAttachmentsManager(@ModelAttribute("loginForm") User loginForm,BindingResult result,HttpSession session,Model model, @RequestParam("appid") String id) throws SQLException {
    Application application = new Application();
    
    if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
    	return "index";
    }
    else{
    
	    Collection s = null;
	    application.setApplication_id(id);
	    s = this.applicationDAOImpl.findApplication3(application);
	    Image image = new Image();
	    image.setApplication_id(id);
	
	    Collection s2 = this.imageDAOImpl.getImages(image);
	    ArrayList a = new ArrayList(s2);
	//    System.out.println(((Image)a.get(1)).getType());
	    ArrayList b = new ArrayList(s);
	   
	    model.addAttribute("images", this.imageDAOImpl.getImages(image));
	
	    model.addAttribute("application", s);
	    model.addAttribute("role",session.getAttribute("ROLE"));
	 	model.addAttribute("user",session.getAttribute("USER"));
	    return "manager/attachmentmanager";
    }
  }
}