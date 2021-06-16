package com.pfa.metier;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendHTMLEmail {
	private static void addAttachment(Multipart multipart, String filename) throws MessagingException
	{
		 BodyPart messageBodyPart = new MimeBodyPart();
	       String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
	       messageBodyPart.setContent(htmlText, "text/html");
	       // add it
	       multipart.addBodyPart(messageBodyPart);

	       // second part (the image)
	       messageBodyPart = new MimeBodyPart();
	       DataSource fds = new FileDataSource(filename);

	       messageBodyPart.setDataHandler(new DataHandler(fds));
	       messageBodyPart.setHeader("Content-ID", "<image>");

	       // add image to the multipart
	       multipart.addBodyPart(messageBodyPart);
	}
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
      String to = "kais.trabelsi@iit.ens.tn";

      // Sender's email ID needs to be mentioned
      String from = "kais2032@gmail.com";
      final String username = "kais2032@gmail.com";//change accordingly
      final String password = "1105430724811435@";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
	});

      try {
    	  
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

   	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));

	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));

	   // Set Subject: header field
	   message.setSubject("Testing Subject");

	   // Send the actual HTML message, as big as you like
	
	

	   
	   
	   
	   
	   
	   
	   MimeMultipart multipart = new MimeMultipart("related");

       // first part (the html)
      addAttachment(multipart, "C:/Users/ASUS/Desktop/iitlogo.png");
      addAttachment(multipart, "C:/Users/ASUS/Desktop/1.jpg");
      addAttachment(multipart, "C:/Users/ASUS/Desktop/iitlogo.png");
      addAttachment(multipart, "C:/Users/ASUS/Desktop/1.jpg");
      
       // put everything together
       message.setContent(multipart);
       // Send message
	   
	   
	   
	   
	   
	   // Send message
	   Transport.send(message);

	

      } catch (MessagingException e) {
	   e.printStackTrace();
	   throw new RuntimeException(e);
      }
   }
}