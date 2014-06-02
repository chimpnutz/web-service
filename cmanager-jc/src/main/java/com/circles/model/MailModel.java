package com.circles.model;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.context.ServletContextAware;
/*
@Component
public class MailModel
{
	
	private static final Logger logger = LoggerFactory.getLogger(MailModel.class);
	
	
	private JavaMailSender mailSender;
	

	private VelocityEngine velocityEngine;
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		System.out.println("MailSender:"+mailSender);
		this.mailSender= mailSender;
	}
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		System.out.println("VelocityEngine:"+velocityEngine);
	      this.velocityEngine = velocityEngine;
	   }
 
	public void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		mailSender.send(message);	
		
	}
	public void sendMail(final Application application,final String from) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
	            try{
	            	MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		            Resource resource = new ClassPathResource("../properties/filepath.properties");
		    		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		            message.setTo(application.getEmail());
		            message.setFrom(from); // could be parameterized...
		            message.setSubject("Postpaid Plan Application");
		            Map model = new HashMap();
		            model.put("application", application);
		            System.out.println("Velocity:"+velocityEngine);
		            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/mail.vm", model);
		            logger.info("text={}", text);
		            message.setText(text, true);
		            System.out.println("TExt:"+text);
		            System.out.println("Message:"+message);
	            }catch(NullPointerException e){
	            	e.printStackTrace();
	            }
	         }
	      };
	      System.out.println("Preparator:"+preparator);
	      this.mailSender.send(preparator);
	   }
}

*/

@Component
public class MailModel implements ServletContextAware{ 

    private static final Logger logger = LoggerFactory.getLogger(MailModel.class);

    private  VelocityEngine velocityEngine;
    private  JavaMailSender mailSender;

    /**
     * Constructor
     */
    @Autowired
	public void setMailSender(JavaMailSender mailSender) {
		System.out.println("MailSender:"+mailSender);
		this.mailSender= mailSender;
	}
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		System.out.println("VelocityEngine:"+velocityEngine);
	      this.velocityEngine = velocityEngine;
	   }

    /**
     * Sends e-mail using Velocity template for the body and 
     * the properties passed in as Velocity variables.
     * 

     */
    @SuppressWarnings("deprecation")
	public void sendMail(final Application application, final HttpServletRequest request, 
			final String p_code, final String plan_code,final String date) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({ "unchecked" })
			public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               message.setTo(application.getEmail());
               message.setFrom("alert@payexchangeinc.com");
               message.setSubject("Postpaid Application");
               Map map = new HashMap();
               map.put("application", application);
               map.put("plan", plan_code);
               map.put("phone", p_code);
               map.put("date", date);
              
               String body ="";
               String is = null ;
               
               is = String.format("%s://%s:%d/Circles",request.getScheme(),  request.getServerName(), request.getServerPort());
               
               System.out.println(is+"/resources/images/smart.png");
               map.put("imageLocation",is);
               ClassLoader classLoader = Thread.currentThread()
                       .getContextClassLoader();
               System.out.println("classloader:"+classLoader);
               if (classLoader == null) {
                   classLoader = this.getClass().getClassLoader();
                
                   if (classLoader == null) {
                       System.out.println("IT IS NULL AGAIN!!!!");
                   }
               }

                
               System.out.println("status:"+application.getStatus());

               if(application.getStatus().equals("0")){
            	   
            	   body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail.vm", map); 
               }
               if(application.getStatus().equals("2")){
            	   
            	   body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail.vm", map); 
               }
               if(application.getStatus().equals("1")){
            
           
            	   body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail1.vm", map); 
               }

               if(application.getStatus().equals("3")){
            	   
            	   body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mail3.vm", map); 
               }

               
               
               logger.info("body={}", body);
               System.out.println(mailSender.toString());
               message.setText(body, true);
            }
         };
         
         mailSender.send(preparator);
         
    }
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
    
}