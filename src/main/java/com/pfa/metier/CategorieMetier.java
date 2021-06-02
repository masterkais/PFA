package com.pfa.metier;

import java.util.List;

import com.pfa.entities.Categorie;

public interface CategorieMetier {
Categorie getCategorie(Long id);
List<Categorie> getAllCategorie();
String supprimerCategorie(Long id);
Categorie ajouterCategorie(Categorie cat);
Categorie modifierCategorie(Long id,String nom);
}
