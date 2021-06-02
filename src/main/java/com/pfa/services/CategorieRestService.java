package com.pfa.services;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.entities.Categorie;
import com.pfa.metier.CategorieMetier;

@RestController
public class CategorieRestService {
@Autowired 
CategorieMetier categorieMetier;
@RequestMapping(value = "/categories/{id}",method = RequestMethod.GET)
public Categorie getCategorie(@PathVariable Long id) {
	return categorieMetier.getCategorie(id);
}
@RequestMapping(value = "/categories",method = RequestMethod.GET)
public List<Categorie> getAllCategorie() {
	return categorieMetier.getAllCategorie();
}
@RequestMapping(value = "/categories/{id}",method = RequestMethod.DELETE)
public String supprimerCategorie(Long id) {
	return categorieMetier.supprimerCategorie(id);
}
@RequestMapping(value = "/categories",method = RequestMethod.POST)
public Categorie ajouterCategorie(Categorie cat) {
	return categorieMetier.ajouterCategorie(cat);
}
@RequestMapping(value = "/categories",method = RequestMethod.PUT)
public Categorie modifierCategorie(@RequestParam Long id_cat,@RequestParam String nom_categorie) {
	return categorieMetier.modifierCategorie(id_cat,nom_categorie);
}

}
