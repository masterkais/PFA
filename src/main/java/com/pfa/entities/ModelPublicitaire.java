package com.pfa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Entity
public class ModelPublicitaire implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nameTempate;
private Date dateCreation;
private Date dateenvoie;
private String EncodageTemplate;
@OneToMany(mappedBy = "model",fetch=FetchType.LAZY)
private Collection<Ligne_M_C> ligneMC;
@ManyToMany(mappedBy = "modelPublicitaires")
private Collection<Utilisateur> users;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "ID_CAT")  
private Categorie categorie;
public ModelPublicitaire(String nameTempate, Date dateCreation, Date dateenvoie, String encodageTemplate
		) {
	super();
	this.nameTempate = nameTempate;
	this.dateCreation = dateCreation;
	this.dateenvoie = dateenvoie;
	EncodageTemplate = encodageTemplate;
	
	
}

public ModelPublicitaire() {
	super();

}

public Categorie getCategorie() {
	return categorie;
}

public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNameTempate() {
	return nameTempate;
}
public void setNameTempate(String nameTempate) {
	this.nameTempate = nameTempate;
}
public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public Date getDateenvoie() {
	return dateenvoie;
}
public void setDateenvoie(Date dateenvoie) {
	this.dateenvoie = dateenvoie;
}
public String getEncodageTemplate() {
	return EncodageTemplate;
}
public void setEncodageTemplate(String encodageTemplate) {
	EncodageTemplate = encodageTemplate;
}
@JsonIgnore
public Collection<Ligne_M_C> getLigneMC() {
	return ligneMC;
}
public void setLigneMC(Collection<Ligne_M_C> ligneMC) {
	this.ligneMC = ligneMC;
}
@JsonIgnore
public Collection<Utilisateur> getUsers() {
	return users;
}
public void setUsers(Collection<Utilisateur> users) {
	this.users = users;
}
}
