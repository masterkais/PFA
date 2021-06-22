package com.pfa.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pfa.dao.CategorieRepository;
import com.pfa.dao.ClientRepository;
import com.pfa.dao.ImageRepository;
import com.pfa.dao.Ligne_M_CRepository;
import com.pfa.dao.ModelPublicitaireRepository;
import com.pfa.entities.Client;
import com.pfa.entities.Image;
import com.pfa.entities.Ligne_M_C;
import com.pfa.entities.ModelPublicitaire;
import com.pfa.entities.Utilisateur;

@Service
public class ModelPublicitaireMetierImpl implements ModelPublicitaireMetier {
@Autowired
ModelPublicitaireRepository modelPublicitaireRepository;
@Autowired
CategorieRepository categorieRepository;
@Autowired
JavaMailSender javaMailSender;
@Autowired
ImageRepository imageRepository;
@Autowired
ClientRepository clientRepository;
@Autowired
Ligne_M_CRepository ligne_M_CRepository;
static int randomOneOrMinusOne() {
    Random rand = new Random();
    if (rand.nextBoolean()) return 1;
    else return -1;
}
@Override
public ModelPublicitaire EnregistrerModel(ModelPublicitaire c) {
	// TODO Auto-generated method stub
	return modelPublicitaireRepository.save(c);
}

@Override
public List<ModelPublicitaire> listModel() {
	// TODO Auto-generated method stub
	return modelPublicitaireRepository.findAll();
}

@Override
public ModelPublicitaire getModel(Long id) {
	// TODO Auto-generated method stub
	return modelPublicitaireRepository.findById(id).get();
}

@Override
public String supprimerModel(Long id) {
	// TODO Auto-generated method stub
	ModelPublicitaire m=modelPublicitaireRepository.findById(id).get();
	m.setCategorie(null);
	m.setLigneMC(null);
	m.setUsers(null);
	modelPublicitaireRepository.delete(m);
	return "supprission avec succée";
	
}

@Override
public ModelPublicitaire modifierModel(Long id,Date dateCreation,String encodageTemplate,String nameTemplate,Long id_cat) {
	// TODO Auto-generated method stub
ModelPublicitaire p=modelPublicitaireRepository.findById(id).get();
p.setDateCreation(dateCreation);

p.setEncodageTemplate(encodageTemplate);
p.setNameTemplate(nameTemplate);
p.setCategorie(categorieRepository.findById(id_cat).get());
return modelPublicitaireRepository.save(p);
}

@Override
public List<ModelPublicitaire> getModelByNom(String nom) {
	// TODO Auto-generated method stub
	return modelPublicitaireRepository.getModelByNom(nom);
}

@Override
public String EnvoieModel(Client c, Utilisateur u, ModelPublicitaire m) {
	
	 String to = c.getEmail();

     // Sender's email ID needs to be mentioned
     String from = u.getEmail();
     final String username = u.getEmail();//change accordingly
     final String password = "24811435110543072032@@";//change accordingly
     // Assuming you are sending email through relay.jangosmtp.net
     String host = "smtp.gmail.com";

     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", "25");
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
	   message.setSubject(m.getNameTemplate());

	   // Send the actual HTML message, as big as you like
	   message.setContent(
           m.getEncodageTemplate(),
          "text/html");

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

     } catch (MessagingException e) {
  	   e.printStackTrace();
  	   throw new RuntimeException(e);
     }
	
	return "Votre message a été envoyé avec succée";
}

@Override
public String EnvoieModelHtmlETtImage(Client c, Utilisateur u, ModelPublicitaire m) {
	// TODO Auto-generated method stub
	
	
	try {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); // true indicates
													   // multipart message
		helper.setSubject("h");
		helper.setTo(c.getEmail());
		helper.setText(m.getEncodageTemplate(), true); // true indicaes html
		// continue using helper object for more functionalities like adding attachments, etc.  
		
		FileSystemResource f=new FileSystemResource("C:/Users/ASUS/Desktop/GG.png");
		helper.addAttachment("GG.png",f);
		//message.addHeader ( "Disposition-Notification-To" , "kais2032@gmail.com" ) ;
		
		//SMTPMessage smtpMsg = new SMTPMessage(message);
		//smtpMsg.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
		message.setHeader("Disposition-Notification-To",u.getEmail());
		message.setHeader("Return-Receipt-To",u.getEmail());
		javaMailSender.send(message);

	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	
	return "Votre message a été envoyé avec succée";
}


public String EnvoieModelToMultipleClient(List<Client> lc, Utilisateur u, ModelPublicitaire m) {
	
	 List<String> lmail = new ArrayList<String>();
	 for(int i=0;i<lc.size();i++) {
		lmail.add(lc.get(i).getEmail());
	 }

    // Sender's email ID needs to be mentioned
    String from = u.getEmail();
    final String username = u.getEmail();//change accordingly
    final String password = "24811435110543072032@@";//change accordingly
    // Assuming you are sending email through relay.jangosmtp.net
    String host = "smtp.gmail.com";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "25");
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password);
               }
   	});
    try {
    	
    	
    	
    	
    	
    	for(int i=0;i<lmail.size();i++) {
    		SendThread s=new SendThread(lmail.get(i), from, session, m);
    		s.start();
    	 	List<Client> c=clientRepository.getClientByEmail(lmail.get(i));
          
            
        	Ligne_M_C lmm=new Ligne_M_C(c.get(0),m,randomOneOrMinusOne());
        		Ligne_M_C lm=ligne_M_CRepository.save(lmm);
        
         	}
    } catch(Exception e) {
    	e.printStackTrace();
    }
	
	return "Votre message a été envoyé avec succée";
}

@Override
public Image GetImageByPath(String path) {
	// TODO Auto-generated method stub
	return imageRepository.getImageByPah(path);
}
@Override
public Image EnregistrerImage(Image i) {
	// TODO Auto-generated method stub
	return imageRepository.save(i);
}

@Override
public Image GetImageByName(String name) {
	System.out.println("dans metierim getImagebyname"+name);
	// TODO Auto-generated method stub
	return imageRepository.getImageByname(name);
}
@Override
public void ModifierDateEnvoi(Long id,Date d) {
	ModelPublicitaire p=modelPublicitaireRepository.findById(id).get();
p.setDateenvoie(new Date());

	 modelPublicitaireRepository.save(p);
	
}
}
