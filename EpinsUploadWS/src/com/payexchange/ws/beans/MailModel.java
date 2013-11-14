package com.payexchange.ws.beans;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
public class MailModel
{
	private JavaMailSender mailSender;
 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
		
		MimeMessage mime = mailSender.createMimeMessage();
	try{	
		MimeMessageHelper message = new MimeMessageHelper(mime, true);
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		FileSystemResource file = new FileSystemResource("D:\\Epins.zip");
		message.addAttachment(file.getFilename(), file);
		
	  }catch (MessagingException e) {
			throw new MailParseException(e);	
	  }
		mailSender.send(mime);	
	}

	{
		// TODO Auto-generated method stub
		
	}

	
}
