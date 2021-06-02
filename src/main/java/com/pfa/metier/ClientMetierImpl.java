package com.pfa.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.dao.ClientRepository;
import com.pfa.entities.Client;

@Service
public class ClientMetierImpl implements ClientMetier {
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client EnregistrerClient(Client c) {
		// TODO Auto-generated method stub
		return clientRepository.save(c);
	}

	@Override
	public List<Client> listeClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id).get();
	}

	@Override
	public void supprimerClient(Long id) {
		// TODO Auto-generated method stub
		clientRepository.deleteById(id);
	}

	@Override
	public Client modifierClient(Long id, String nom, String prenom, String tel, String email, Date dateNess,
			String paye, String region, String ville, String sexe) {
		Client c = clientRepository.findById(id).get();
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setTel(tel);
		c.setEmail(email);
		c.setDateNess(dateNess);
		c.setPaye(paye);
		c.setRegion(region);
		c.setVille(ville);
		c.setSexe(sexe);
		return clientRepository.save(c);

	}

}
