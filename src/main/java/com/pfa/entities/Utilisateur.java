package com.pfa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_USER",discriminatorType = DiscriminatorType.STRING,length = 6)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,property = "type")
@JsonSubTypes({
	@Type(name = "AS",value = AdminSysteme.class),
	@Type(name = "SA",value = SimpleAdmin.class)
})
public class Utilisateur implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id_user;

private String nom;
private String prenom;
private String login;
private String password;
private String email;
@ManyToMany
@JoinTable(name="models")
private Collection<ModelPublicitaire> modelPublicitaires;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "GA_ID")  
private GlobalAdmin globalAdmin;
public Utilisateur() {
	super();
}

public Utilisateur(String nom, String prenom, String login, String password, String email) {
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
@JsonIgnore
public GlobalAdmin getGlobalAdmin() {
	return globalAdmin;
}
@JsonIgnore
public void setGlobalAdmin(GlobalAdmin globalAdmin) {
	this.globalAdmin = globalAdmin;
}

public Long getId_user() {
	return id_user;
}

public void setId_user(Long id_user) {
	this.id_user = id_user;
}

@JsonIgnore
public Collection<ModelPublicitaire> getModelPublicitaires() {
	return modelPublicitaires;
}
@JsonIgnore
public void setModelPublicitaires(Collection<ModelPublicitaire> modelPublicitaires) {
	this.modelPublicitaires = modelPublicitaires;
}





}
