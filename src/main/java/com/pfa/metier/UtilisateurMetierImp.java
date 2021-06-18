package com.pfa.metier;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.dao.GlobalAdminRepository;
import com.pfa.dao.UtilisateurRepository;
import com.pfa.entities.Utilisateur;
@Service
public class UtilisateurMetierImp implements UtilisateurMetier {
@Autowired
UtilisateurRepository utilisateurRepository;
@Autowired 
GlobalAdminRepository globalAdminRepository;
	@Override
	public Utilisateur EnregistrerUtilisateur(Utilisateur c) {
		// TODO Auto-generated method stub
		c.setGlobalAdmin(null);
		return utilisateurRepository.save(c);
	}

	@Override
	public List<Utilisateur> listeUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur getUtilisateur(Long id) {
		// TODO Auto-generated method stub
	Utilisateur u= utilisateurRepository.findById(id).get();
	System.out.println("***********************************"+u.getLogin());
	int key=5;
	String password=u.getPassword();
	String ch="";
	char[] chars = password.toCharArray();
	for(char c:chars) {
		c-=key;
		ch+=c;
	}
u.setPassword(ch);
		return u;
	}

	@Override
	public void supprimerUtilisateur(Long id) {
		// TODO Auto-generated method stub
		System.out.println("********* id :"+id);
		Utilisateur c=utilisateurRepository.findById(id).get();
		c.setGlobalAdmin(null);
	
		utilisateurRepository.delete(c);;
	}

	@Override
	public Utilisateur modifierUtilisateur(Long id, String nom, String prenom, String email,String login, String password) {
		// TODO Auto-generated method stub
		System.out.println("**************************");
		Utilisateur c= utilisateurRepository.findById(id).get();
		System.out.println("**************************"+c.getNom());
		c.setLogin(login);
		c.setEmail(email);
		c.setNom(prenom);
		c.setPassword(password);
		c.setPrenom(prenom);
		return utilisateurRepository.save(c);

	}

	@Override
	public Utilisateur getUtilisateurByLoginAndPassword(String x, String y) {
		Utilisateur u=utilisateurRepository.getUtilisateurByLoginAndPassword(x, y);
		return u;
	}

	

	 
	
	
	
	
}
