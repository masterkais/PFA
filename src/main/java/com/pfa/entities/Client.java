package com.pfa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_client;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private Date dateNess;
	private String paye;
	private String region;
	private String ville;
	private String sexe;
	@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
	private Collection<Ligne_M_C> ligneMC;
	public Client(String nom, String prenom, String tel, String email, Date dateNess, String paye, String region,
			String ville, String sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.dateNess = dateNess;
		this.paye = paye;
		this.region = region;
		this.ville = ville;
		this.sexe = sexe;
		
	}
	public Client() {
		super();
	}
	public Long getId_client() {
		return id_client;
	}
	public void setId_client(Long id_client) {
		this.id_client = id_client;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateNess() {
		return dateNess;
	}
	public void setDateNess(Date dateNess) {
		this.dateNess = dateNess;
	}
	public String getPaye() {
		return paye;
	}
	public void setPaye(String paye) {
		this.paye = paye;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	@JsonIgnore
	public Collection<Ligne_M_C> getLigneMC() {
		return ligneMC;
	}
	public void setLigneMC(Collection<Ligne_M_C> ligneMC) {
		this.ligneMC = ligneMC;
	}


	
	
}
