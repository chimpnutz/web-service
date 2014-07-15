package com.circles.controller;

import com.circles.dao.impl.ApplicationDAOImpl;
import com.circles.dao.impl.CommentDAOImpl;
import com.circles.dao.impl.GcmDAOImpl;
import com.circles.dao.impl.ImageDAOImpl;
import com.circles.dao.impl.PhoneDAOImpl;
import com.circles.dao.impl.PlanDAOImpl;
import com.circles.dao.impl.ProductDAOImpl;
import com.circles.model.AddressDetails;
import com.circles.model.Application;
import com.circles.model.Comment;
import com.circles.model.Devices;
import com.circles.model.Gcm;
import com.circles.model.Image;
import com.circles.model.MailModel;
import com.circles.model.Phone;
import com.circles.model.Plan;
import com.circles.model.Products;
import com.circles.model.User;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userid"})
public class EncoderController
{

  @Autowired
  ApplicationDAOImpl applicationDAOImpl;

  @Autowired
  CommentDAOImpl commentDAOImpl;

  @Autowired
  ImageDAOImpl imageDAOImpl;

  @Autowired
  GcmDAOImpl gcmDAOImpl;

  @Autowired
  ProductDAOImpl productDAOImpl;

  private JavaMailSenderImpl mailSender;
  private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

  @RequestMapping(value={"/applicationform"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, params={"appid"})
  public String viewMobile(@ModelAttribute("loginForm") User loginForm,BindingResult result,Model model, @RequestParam("appid") String id, HttpSession session) throws SQLException {
    Application application = new Application();
    Collection s = null;
    Comment comment = new Comment();
    
    if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
    	return "index";
    }
    
    else{

		    comment.setApplication_id(id);
		    application.setApplication_id(id);
		
		    s = this.applicationDAOImpl.findApplication3(application);
		    
		    model.addAttribute("role",session.getAttribute("ROLE"));
    	 	model.addAttribute("user",session.getAttribute("USER"));
		    model.addAttribute("application", s);
		    ArrayList a = new ArrayList(s);
//		    System.out.println("SSS:" + ((Application)a.get(0)).getDoc_identity_sss_no());
		    ArrayList user = (ArrayList)session.getAttribute("sess");
		    model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
		    
		    Application app = new Application();
	 
			application.setApplication_id(id);
			return "encoder/applicationform";
    }
   	 	


    
  }

  @RequestMapping(value={"/encoderrecent"}, method = RequestMethod.GET)
  public String viewEncoder(@ModelAttribute("loginForm") User loginForm,Model model,HttpSession session,BindingResult result)
    throws SQLException
  {
    Application application = new Application();
    Collection s = null;
    
    if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
    	return "index";
    }
    
    else{
    	 s = this.applicationDAOImpl.getRecentApplication(application);
    	 	model.addAttribute("role",session.getAttribute("ROLE"));
    	 	model.addAttribute("user",session.getAttribute("USER"));
    	    model.addAttribute("application", s);
    	    return "encoder/encoderrecent";
    }

   
  }

  @RequestMapping(value={"/encoderreturn"}, method = RequestMethod.GET)
  public String viewEncoderReturn(@ModelAttribute("loginForm") User loginForm,BindingResult result,Model model,HttpSession session) throws SQLException {
    Application application = new Application();
    
    if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
    	return "index";
    }
    
    else{

	    Collection s = this.applicationDAOImpl.getReturnApplication(application);
	    model.addAttribute("role",session.getAttribute("ROLE"));
	 	model.addAttribute("user",session.getAttribute("USER"));
	    model.addAttribute("application", s);
	
	    return "encoder/encoderreturn";
    }
  }

  @RequestMapping(value={"/encoderencode"}, method = RequestMethod.GET)
  public String viewEncoderEncode(@ModelAttribute("loginForm") User loginForm,BindingResult result,Model model,HttpSession session) throws SQLException {
    Application application = new Application();
    if(null == session.getAttribute("USER")||null == session.getAttribute("ROLE")){
    	return "index";
    }
    
    else{

	    Collection s = this.applicationDAOImpl.getOngoingApplication(application);
	    model.addAttribute("application", s);
	    model.addAttribute("role",session.getAttribute("ROLE"));
	 	model.addAttribute("user",session.getAttribute("USER"));
	 	
	    return "encoder/encoderencode";
    }
  }

  @RequestMapping({"/saveCommentE"})
  public String saveCommente(@RequestParam(value="id", required=false) String id,
		  @RequestParam(value="application_id", required=false) String application_id, 
		  @RequestParam(value="user_id", required=false) String user_id, 
		  @RequestParam(value="ispushed", required=false) String ispushed, 
		  @RequestParam(value="edited_by", required=false) String edited_by, 
		  @RequestParam(value="created", required=false) String created,
		  @RequestParam(value="updated", required=false) String updated,
		  @RequestParam(value="version", required=false) String version, 
		  @RequestParam(value="content", required=false) String content, Model model,
		  HttpSession session)
    throws IOException
  {

    Comment comment = new Comment();
    String commentUID = "{" + UUID.randomUUID().toString() + "}";

    comment.setId(commentUID);
    comment.setApplication_id(application_id);
    comment.setUser_id(user_id);
    comment.setIspushed("0");
    comment.setEdited_by(edited_by);
    String date = new Date().getTime()+"";
    comment.setCreated(date);
    comment.setUpdated(date);
    comment.setVersion("1");
    comment.setContent(content);
//    Resource resource = new ClassPathResource("../properties/filepath.properties");
//    Properties props = PropertiesLoaderUtils.loadProperties(resource);
    String GOOGLE_SERVER_KEY = "AIzaSyCCoMqyTb6-CZPDssq4OPDJj0bOdhgP62Y ";
    Gcm gcm = new Gcm();
    Application application = new Application();
    ArrayList copy = null;
    ArrayList copy2 = null;
    Collection s = null;
    Collection app_user = null;
    String regid = "";
 
    int isSaved = 0;
    int isExisting = this.commentDAOImpl.checkIfExists(comment);
    System.out.println("asdasds"+isExisting);
    application.setApplication_id(application_id);
    if (isExisting == 0) {
      try {
    	  System.out.println("asdasds"+application.getApplication_id());
        if (!application_id.isEmpty()) {
          isSaved = this.commentDAOImpl.save(comment);
          System.out.println("isave: "+ isSaved);
        }
      }
      catch (NullPointerException localNullPointerException)
      {
      }
    }

 
    	
    	 if (isSaved == 1) {
         	System.out.println("comment testing..........");
           MulticastResult result2 = null;
           Result result = null;
           try {
        	      app_user = this.applicationDAOImpl.findApplication3(application);
                  copy2 = new ArrayList<Application>(app_user);
                  System.out.println("printing: "+copy2.get(0));
                  gcm.setUsername(session.getAttribute("username")+"");
         
//                  gcm.setUsername(((Application)copy2.get(0)).getEditedBy());
                  s = this.gcmDAOImpl.findGcm(application_id);
           }
           catch (NullPointerException | SQLException e)
           {
        	model.addAttribute("role",session.getAttribute("ROLE"));
       	 	model.addAttribute("user",session.getAttribute("USER"));
        	   return "encoder/applicationform";
           }
     
           copy = new ArrayList<Gcm>(s);
           
           
           
           
           System.out.println("hahahaha try.....");
       
           regid = ((Gcm)copy.get(0)).getRegid();
           Sender sender = new Sender(GOOGLE_SERVER_KEY);
           Message messages = new Message.Builder().delayWhileIdle(false).addData("table", "comment").build();
           ArrayList<String> devices = new ArrayList<String>();
           devices.add(regid);
           System.out.println("message: "+messages);
           result2 = sender.send(messages, devices, 5);
           System.out.println("regid: "+regid);
           System.out.println("result: "+result2.toString());
           //resource = null;
           Image image = new Image();
           image.setApplication_id(application_id);
           model.addAttribute("application", app_user);
           comment = new Comment();
           comment.setApplication_id(application_id);
           
           try {
 			model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         
           model.addAttribute("images", this.imageDAOImpl.getImages(image));
           System.out.println(isSaved);
           model.addAttribute("role",session.getAttribute("ROLE"));
   	 	model.addAttribute("user",session.getAttribute("USER"));
           return "encoder/applicationform";
         }
    	
         Image image = new Image();
         image.setApplication_id(application_id);
         model.addAttribute("application", app_user);
         comment = new Comment();
         comment.setApplication_id(application_id);
         try {
        
 			model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
       
         model.addAttribute("images", this.imageDAOImpl.getImages(image));
         model.addAttribute("role",session.getAttribute("ROLE"));
 	 	 model.addAttribute("user",session.getAttribute("USER"));
         return "encoder/applicationform";
  	  
  }

  @RequestMapping({"/updateStatus"})
  public String updateStatus(@RequestParam(value="application_id", required=false) String application_id,
		  @RequestParam(value="status", required=false) String status, 
		  @RequestParam(value="prod_name", required=false) String prod_name,
		  @RequestParam(value="dev_name", required=false) String dev_name,
		  Model model, HttpServletRequest request,HttpSession session)
    throws SQLException, IOException
  {
	String dated  = ""+new Date().getTime()+"";
    Application getApp = new Application();
    Collection s = null;
    getApp.setApplication_id(application_id);
    s = this.applicationDAOImpl.findApplication3(getApp);

    ArrayList appVersion = new ArrayList(s);

    Application application = new Application();
    application.setApplication_id(application_id);
    application.setStatus(status);
    System.out.println(s);
    System.out.println(status);
    int i = Integer.parseInt(((Application)appVersion.get(0)).getVersion());;
    i++;
    System.out.println("version: " + i);
    application.setEditedBy(session.getAttribute("USER")+"");
    application.setVersion(i+"");
    application.setIspushed("0");
    application.setUpdate(dated);
    application.setCreated(dated);
    int isUpdated = this.applicationDAOImpl.update2(application);
    Resource resource = new ClassPathResource("../properties/filepath.properties");
//    Properties props = PropertiesLoaderUtils.loadProperties(resource);
    String GOOGLE_SERVER_KEY = "AIzaSyCCoMqyTb6-CZPDssq4OPDJj0bOdhgP62Y ";
//    		props.getProperty("GOOGLE_API_KEY");
    Gcm gcm = new Gcm();
    String regid = "";
    ArrayList copy = null;
    Collection gcms = null;
    Collection app_user = null;
    if (isUpdated == 1) {
      MulticastResult result2 = null;
      Result result = null;
      Application newApp = new Application();
      newApp.setApplication_id(application_id);
      app_user = this.applicationDAOImpl.findApplication3(application);
      ArrayList copy2 = new ArrayList(app_user);
      gcm.setUsername(((Application)copy2.get(0)).getEditedBy());
      System.out.println(((Application)copy2.get(0)).getEditedBy());
      newApp.setFirstName(((Application)copy2.get(0)).getFirstName());
      newApp.setLastName(((Application)copy2.get(0)).getLastName());
      newApp.setEmail(((Application)copy2.get(0)).getEmail());
      newApp.setCreated(((Application)copy2.get(0)).getCreated());
      newApp.setStatus(status);
      System.out.println(status);
      if ((status.equals("2")) || (status.equals("1")) || (status.equals("3"))) {
        sendEmail(newApp, request,application_id,prod_name,dev_name);
        System.out.println("An email has been sent to " + ((Application)copy2.get(0)).getEmail() + " on" + new Date().getDate());
      }

      gcms = this.gcmDAOImpl.findGcm(application_id);
      copy = new ArrayList(gcms);
      regid = ((Gcm)copy.get(0)).getRegid();
      Sender sender = new Sender(GOOGLE_SERVER_KEY);
      Message messages = new Message.Builder().delayWhileIdle(false).addData("table", "application").build();

      ArrayList devices = new ArrayList();
      devices.add(regid);
      System.out.println(messages);
      result2 = sender.send(messages, devices, 5);
      System.out.println(regid);
      System.out.println(result2.toString());
      resource = null;
      Image image = new Image();
      image.setApplication_id(application_id);
      model.addAttribute("application", app_user);
      Comment comment = new Comment();
      comment.setApplication_id(application_id);
      System.out.println("Updated status of application " + application_id + "to " + status);
      model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
      model.addAttribute("images", this.imageDAOImpl.getImages(image));
      model.addAttribute("role",session.getAttribute("ROLE"));
	  model.addAttribute("user",session.getAttribute("USER"));
      return "encoder/applicationform";
    }

    Image image = new Image();
    image.setApplication_id(application_id);
    model.addAttribute("application", this.applicationDAOImpl.findApplication3(application));
    Comment comment = new Comment();
    comment.setApplication_id(application_id);
    model.addAttribute("comment", this.commentDAOImpl.findComment(comment));
    model.addAttribute("images", this.imageDAOImpl.getImages(image));
    model.addAttribute("role",session.getAttribute("ROLE"));
 	 model.addAttribute("user",session.getAttribute("USER"));
    return "encoder/applicationform";
  }

  @SuppressWarnings({ "rawtypes", "rawtypes", "rawtypes" })
public boolean sendEmail(Application application, HttpServletRequest request,String id,String prod_name,String dev_name) throws SQLException
  {
    ApplicationContext a = new ClassPathXmlApplicationContext("classpath:Spring-Mail.xml");
    String date  = ""+new Date().getTime()+"";
    application.setCreated(date);
    System.out.println("email "+application.getCreated());

    Devices dev = new Devices();
    Products pd = new Products();
    pd.setProduct_id(application.getProduct_id());
    dev.setDeviceid(application.getDeviceid());
    application.setApplication_id(id);
    System.out.println("APPLICATION ID: "+id);
    long unix = Long.parseLong(application.getCreated());
    Date dates = new Date(unix);
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

    String formattedDate = sdf.format(dates);
    System.out.println("Device/Hardware:" + id);
    Collection s = null;
    s = this.productDAOImpl.viewSelectDevice2(dev,id);
    ArrayList<Devices> devApp = new ArrayList<Devices>(s);
    String dev_code = "";
    s = this.productDAOImpl.viewSelectedProduct(pd,id);
    ArrayList<Products> prodApp = new ArrayList<Products>(s);
    
    String prod_code = "";
//    try {
//    	
//    	dev_code = (devApp.get(0)).getDevice_name();
//      System.out.println("Device/Hardware:" + dev_code);
//    }
//    catch (IllegalArgumentException localIllegalArgumentException) {
//    }
//    try {
//        prod_code = (prodApp.get(0)).getProduct_name();
//        System.out.println("Plan:" + prod_code);
//      }
//      catch (IllegalArgumentException localIllegalArgumentException1) {
//      }
      MailModel mail = (MailModel)a.getBean("mail");

      mail.sendMail(application, request, dev_name, prod_name, formattedDate);

      return true;
  }
}