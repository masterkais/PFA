package com.pfa.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.entities.Client;
import com.pfa.metier.ClientMetier;

@RestController
public class ClientRestService {
	@Autowired
private ClientMetier clientMetier;
@RequestMapping(value = "/clients",method = RequestMethod.POST)
	public Client EnregitrerClient(Client c) {
		return clientMetier.EnregistrerClient(c);
	}
@RequestMapping(value = "/clients",method = RequestMethod.GET)
	public List<Client> listeClient() {
		return clientMetier.listeClient();
	}
@RequestMapping(value = "/clients/{code}",method = RequestMethod.DELETE)
public String SupprimerClient(@PathVariable Long code) {
	 clientMetier.supprimerClient(code);
	 return "Supprission avec succée";
}
@RequestMapping(value="/clients",method=RequestMethod.PUT)
public Client ModifierClient(@RequestParam Long id,
		@RequestParam String nom,
		@RequestParam String prenom,
		@RequestParam String tel,
		@RequestParam String email,
		@RequestParam Date dateNess,
		@RequestParam String paye,
		@RequestParam String region,
		@RequestParam String ville,
		@RequestParam String sexe
		) {
	 return clientMetier.modifierClient( id,  nom,  prenom,  tel,email,dateNess,paye,region,ville,sexe);
}
@RequestMapping(value = "/clients/{code}",method = RequestMethod.GET)
public Client getClient(@PathVariable Long code) {
	return clientMetier.getClient(code);
}
/**/

@RequestMapping(value = "/clients/{noms}/{prenoms}/{tels}/{emails}/{dateNess}/{payes}/{regions}/{villes}/{sexes}",method = RequestMethod.POST)
public List<Client> EnregitrerLidtClient(@PathVariable List<String> noms,
		@PathVariable List<String> prenoms,
		@PathVariable List<String> tels,
		@PathVariable List<String> emails,
		@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<Date> dateNess,
		@PathVariable List<String> payes,
		@PathVariable List<String> regions,
		@PathVariable List<String> villes,
		@PathVariable List<String> sexes) {
	List<Client> lc=new ArrayList<Client>();
	for(int i=0;i<noms.size();i++) {
		 String nom=noms.get(i);
		 String prenom=prenoms.get(i);
		 String tel=tels.get(i);
		 String email=emails.get(i);
		 System.out.println("********************"+dateNess.get(i));
		 Date dateNes=dateNess.get(i);
		
		 String paye=payes.get(i);
		 String region=regions.get(i);
		 String ville=villes.get(i);
		 String sexe=sexes.get(i);
	 Client c=new Client(nom, prenom, tel, email, dateNes, paye, region, ville, sexe);
	 lc.add(c);
	 clientMetier.EnregistrerClient(c);
	}
	return lc ;
}


}
