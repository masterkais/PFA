package com.pfa.metier;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pfa.entities.ModelPublicitaire;

public class SendThread extends Thread {
private String email;
private String from;
private Session session;
private ModelPublicitaire m;

	public SendThread(String email, String from, Session session, ModelPublicitaire m) {
	super();
	this.email = email;
	this.from = from;
	this.session = session;
	this.m = m;
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		   Message message = new MimeMessage(session);

		   // Set From: header field of the header.
		   try {
			message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(email));

		   // Set Subject: header field
		   message.setSubject(m.getNameTempate());

		   // Send the actual HTML message, as big as you like
		   message.setContent(
	          m.getEncodageTemplate(),
	         "text/html");

		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully for...."+email);
		   } catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
