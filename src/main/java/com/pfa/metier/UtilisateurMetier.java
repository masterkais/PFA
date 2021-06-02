package com.pfa.metier;

import java.util.Date;
import java.util.List;

import com.pfa.entities.Utilisateur;



public interface UtilisateurMetier {
	 Utilisateur EnregistrerUtilisateur(Utilisateur c);
	 List<Utilisateur> listeUtilisateur();
	 Utilisateur getUtilisateur(Long id);
	 void supprimerUtilisateur(Long id);
	 Utilisateur modifierUtilisateur(Long id, String nom, String prenom, String email,String login, String password);
     Utilisateur getUtilisateurByLoginAndPassword(String login,String password);
}
