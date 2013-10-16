package com.payexchange.ws.utility;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


public class MailService {
	
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}
	public void setMailSender(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String cc, String subject, String messageBody, String[] filenames){
		System.out.println("sendMail");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), from,to,cc,subject,messageBody,filenames));

			   FileSystemResource file = new FileSystemResource("D:\\xtian10002010Aug24153856.zip");
			   helper.addAttachment(file.getFilename(), file);

			  } catch (MessagingException e) {
			   throw new MailParseException(e);
			  }
			  mailSender.send(message);
		}
		
		
	
	  
	       
	  
	

	
}
