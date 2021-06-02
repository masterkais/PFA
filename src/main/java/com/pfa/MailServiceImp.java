package com.pfa;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ws.mime.MimeMessage;

@Service
public class MailServiceImp implements MailService {
@Autowired
private JavaMailSender javaMailSender;

@Override
public void send(String toAdress, String fromAddress, String subject, String content)  {
	// TODO Auto-generated method stub
	
	try {
	javax.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper;
	
		helper = new MimeMessageHelper(message,true );
	
	helper.setFrom(fromAddress);
	helper.setSubject(subject);
	helper.setText(content);
	javaMailSender.send(message);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // true indicates
}


}
