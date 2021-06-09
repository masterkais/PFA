package com.pfa.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_image;
	private String path;
	private String name;
	@ManyToMany
	@JoinTable(name="models")
	private Collection<ModelPublicitaire> modelPublicitaires;
	public Collection<ModelPublicitaire> getModelPublicitaires() {
		return modelPublicitaires;
	}

	public void setModelPublicitaires(Collection<ModelPublicitaire> modelPublicitaires) {
		this.modelPublicitaires = modelPublicitaires;
	}

	public Image(String path, String name) {
		super();
		this.path = path;
	
		this.name = name;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getId_image() {
		return id_image;
	}
	public void setId_image(Long id_image) {
		this.id_image = id_image;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
