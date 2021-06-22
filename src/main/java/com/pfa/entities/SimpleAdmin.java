package com.pfa.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SA")
public class SimpleAdmin extends Utilisateur{
	private int simpleAdmin  ;
	public SimpleAdmin(String nom, String prenom, String login, String password, String email,int simpleAdmin,GlobalAdmin g) {
		super(nom, prenom, login, password, email,g);
		this.setSimpleAdmin(1);
	}
	public SimpleAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSimpleAdmin() {
		return simpleAdmin;
	}
	public void setSimpleAdmin(int simpleAdmin) {
		this.simpleAdmin = simpleAdmin;
	}
}
