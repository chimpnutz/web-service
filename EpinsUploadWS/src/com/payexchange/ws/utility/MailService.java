package com.payexchange.ws.utility;



import java.io.File;


//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailParseException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;


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
 
	public void sendMail(String hello, String content) {
 
	   MimeMessage message = mailSender.createMimeMessage();
	   File f = new File("D:\\Epins.zip");
	   try{
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
		helper.setFrom(simpleMailMessage.getFrom());
		helper.setTo(simpleMailMessage.getTo());
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(String.format(simpleMailMessage.getText(), hello, content));
 
		FileSystemResource file = new FileSystemResource(f.getAbsolutePath());
		helper.addAttachment(file.getFilename(), file);
 
	     }catch (MessagingException e) {
		throw new MailParseException(e);
	     }
	     mailSender.send(message);
         }
	
}
