package com.pfa.metier;

import java.util.Date;
import java.util.List;

import com.pfa.entities.Client;

public interface ClientMetier {
	public Client EnregistrerClient(Client c);
	public List<Client> listeClient();
	public Client getClient(Long id);
	public void supprimerClient(Long id);
	public Client modifierClient(Long id,String nom, String prenom, String tel, String email, Date dateNess, String paye, String region,
			String ville, String sexe);
}
