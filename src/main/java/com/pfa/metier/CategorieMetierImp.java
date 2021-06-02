package com.pfa.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.dao.CategorieRepository;
import com.pfa.entities.Categorie;
@Service
public class CategorieMetierImp implements CategorieMetier {
@Autowired
CategorieRepository categorieRepository;
	@Override
	public Categorie getCategorie(Long id) {
		// TODO Auto-generated method stub
		return categorieRepository.findById(id).get();
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		return categorieRepository.findAll();
	}

	@Override
	public String supprimerCategorie(Long id) {
		// TODO Auto-generated method stub
		categorieRepository.deleteById(id);
		return "supprission avec succée ";
	}

	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return categorieRepository.save(cat);
	}

	@Override
	public Categorie modifierCategorie(Long id, String nom) {
		// TODO Auto-generated method stub
		Categorie c=categorieRepository.findById(id).get();
		c.setNom_categorie(nom);
		return categorieRepository.save(c);
	}

}
