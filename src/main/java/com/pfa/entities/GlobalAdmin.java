package com.pfa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class GlobalAdmin implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id_user;
private String nom;
private String prenom;
private String login;
private String password;
private String email;
@OneToMany(mappedBy = "globalAdmin",fetch=FetchType.LAZY)
private List<Utilisateur> lusers ;
public GlobalAdmin() {
	super();
}

public GlobalAdmin(String nom, String prenom, String login, String password, String email) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.login = login;
	this.password = password;
	this.email = email;
}

public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String motDePasse) {
	this.password = motDePasse;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public Long getId_user() {
	return id_user;
}

public void setId_user(Long id_user) {
	this.id_user = id_user;
}

public List<Utilisateur> getLusers() {
	return lusers;
}

public void setLusers(List<Utilisateur> lusers) {
	this.lusers = lusers;
}






}
