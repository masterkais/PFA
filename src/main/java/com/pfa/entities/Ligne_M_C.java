package com.pfa.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Ligne_M_C implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Client_ID")  
	private Client client;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Model_ID")
	private ModelPublicitaire model;
	private int etat;
	public Ligne_M_C(Client client, ModelPublicitaire model, int etat) {
		super();
		this.client = client;
		this.model = model;
		this.etat = etat;
	}
	public Ligne_M_C() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ModelPublicitaire getModel() {
		return model;
	}
	public void setModel(ModelPublicitaire model) {
		this.model = model;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	


}
