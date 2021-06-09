package com.pfa.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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

import org.springframework.beans.factory.annotation.Autowired;

import com.pfa.dao.ClientRepository;
import com.pfa.dao.Ligne_M_CRepository;
import com.pfa.dao.ModelPublicitaireRepository;
import com.pfa.dao.UtilisateurRepository;
import com.pfa.entities.Client;
import com.pfa.entities.Image;
import com.pfa.entities.Ligne_M_C;
import com.pfa.entities.ModelPublicitaire;
import com.pfa.entities.Utilisateur;

public class SendThread extends Thread {
	private String email;
	private String from;
	private Session session;
	private ModelPublicitaire m;
@Autowired
ClientRepository cr;
@Autowired
UtilisateurRepository ur;
@Autowired
Ligne_M_CRepository lmr;
	public SendThread(String email, String from, Session session, ModelPublicitaire m) {
		super();
		this.email = email;
		this.from = from;
		this.session = session;
		this.m = m;
	}
	
	private static void addAttachment(Multipart multipart, String filename) throws MessagingException
	{
	    DataSource source = new FileDataSource(filename);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message message = new MimeMessage(session);

		// Set From: header field of the header.
		try {
			message.setFrom(new InternetAddress(from));

			message.setHeader("Disposition-Notification-To", from);
			message.setHeader("Return-Receipt-To", from);
			message.addHeader("Disposition-Notification-To",from);
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			// Set Subject: header field
			message.setSubject(m.getNameTemplate());
		
			// Send the actual HTML message, as big as you like
			message.setContent(m.getEncodageTemplate(), "text/html");
			 MimeMultipart multipart = new MimeMultipart("related");

		       // first part (the html)
		       BodyPart messageBodyPart = new MimeBodyPart();
		       String htmlText = m.getEncodageTemplate();
		       messageBodyPart.setContent(htmlText, "text/html");
		       // add it
		       multipart.addBodyPart(messageBodyPart);

		       // second part (the image)
		       messageBodyPart = new MimeBodyPart();
		       DataSource fds = new FileDataSource("C:/Users/ASUS/Desktop/iitlogo.png");

		       messageBodyPart.setDataHandler(new DataHandler(fds));
		       messageBodyPart.setHeader("Content-ID", "<image>");

		       // add image to the multipart
		       multipart.addBodyPart(messageBodyPart);
		      // List<Image> li=new ArrayList<Image>();
		    // li =  (List<Image>) m.getLm();
		    // System.out.println("heda path "+li.get(0).getPath());
		    //   for(Image i :li) {
		   //  System.out.println(i.getPath());

		    //   }
		       // put everything together
		       message.setContent(multipart);
		       // Send messag
		
			// Send message
			Transport.send(message);
	
		
			
			System.out.println("Sent message successfully for...." + email);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
