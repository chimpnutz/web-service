package com.payexchange.ws.utility;



import java.security.Security;
import java.util.Properties;

//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailParseException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class MailService {


    public void sendMail(String from, String to, String cc, String subject, String messageBody, String[] attachments) throws MessagingException {
        int i = 0;
        String mailServer = "smtp.gmail.com";
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = new Properties();
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        // Define a new mail message
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
        } catch (AddressException ex) {
            ex.printStackTrace();
        }
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        InternetAddress[] TheAddresses = InternetAddress.parse(cc);
        message.addRecipients(Message.RecipientType.CC, TheAddresses);
        message.setSubject(subject);

        // Create a message part to represent the body text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageBody);

        //use a MimeMultipart as we need to handle the file attachments
        Multipart multipart = new MimeMultipart();

        //add the message body to the mime message
        multipart.addBodyPart(messageBodyPart);

        // add any file attachments to the message
        addAtachments(attachments, multipart);

        // Put all message parts in the message
        message.setContent(multipart);

        message.saveChanges();
        // Send the message
        //Transport.send(message);

         String emailpassword = "111503"; // enter password for the email account to be use

        Transport transport = session.getTransport("smtp");
        transport.connect(mailServer, from, emailpassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    protected void addAtachments(String[] attachments, Multipart multipart)
                    throws MessagingException, AddressException
    {
        for(int i = 0; i<= attachments.length -1; i++)
        {
            String filename = attachments[i];
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            //use a JAF FileDataSource as it does MIME type detection
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            //assume that the filename you want to send is the same as the
            //actual file name - could alter this to remove the file path
            attachmentBodyPart.setFileName(filename);

            //add the attachment
            multipart.addBodyPart(attachmentBodyPart);
        }
    }
	  
	

	
}
