package com.pfa.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AS")
public class AdminSysteme extends Utilisateur {
	private int adminSysteme  ;
	public AdminSysteme(String nom, String prenom, String login, String password, String email,int adminSysteme ) {
		super(nom, prenom, login, password, email);
		this.adminSysteme=1;
	}
	public AdminSysteme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAdminSysteme() {
		return adminSysteme;
	}
	public void setAdminSysteme(int adminSysteme) {
		this.adminSysteme = adminSysteme;
	}
	

}
