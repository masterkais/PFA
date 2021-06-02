package com.pfa.entities;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categorie {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id_cat;
private String nom_categorie;
@OneToMany(mappedBy = "categorie",fetch=FetchType.LAZY)
private List<ModelPublicitaire> lmp;
public Categorie(String nom_categorie, List<ModelPublicitaire> lmp) {
	super();
	this.nom_categorie = nom_categorie;
	this.lmp = lmp;
}
public Categorie() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId_cat() {
	return id_cat;
}
public void setId_cat(Long id_cat) {
	this.id_cat = id_cat;
}
public String getNom_categorie() {
	return nom_categorie;
}
public void setNom_categorie(String nom_categorie) {
	this.nom_categorie = nom_categorie;
}
@JsonIgnore
public List<ModelPublicitaire> getLmp() {
	return lmp;
}
public void setLmp(List<ModelPublicitaire> lmp) {
	this.lmp = lmp;
}

}
