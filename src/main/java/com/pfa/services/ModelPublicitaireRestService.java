package com.pfa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.dao.CategorieRepository;
import com.pfa.dao.ClientRepository;
import com.pfa.dao.Ligne_M_CRepository;
import com.pfa.entities.Categorie;
import com.pfa.entities.Client;
import com.pfa.entities.Ligne_M_C;
import com.pfa.entities.ModelPublicitaire;
import com.pfa.entities.Utilisateur;
import com.pfa.metier.CategorieMetier;
import com.pfa.metier.ClientMetier;
import com.pfa.metier.ModelPublicitaireMetier;
import com.pfa.metier.UtilisateurMetier;

@RestController
public class ModelPublicitaireRestService {
	@Autowired
	ModelPublicitaireMetier modelPublicitaireMetier;
@Autowired
CategorieMetier categorieMetier;
@Autowired
UtilisateurMetier utilisateurMetier;
@Autowired
ClientMetier clientMetier;
@Autowired
Ligne_M_CRepository ligne_M_CRepository;
	@RequestMapping(value = "modelPublicitaires", method = RequestMethod.POST)
	public ModelPublicitaire EnregistrerModel(ModelPublicitaire c,@RequestParam Long id_cat) {
		Categorie k=categorieMetier.getCategorie(id_cat);
		c.setCategorie(k);
		return modelPublicitaireMetier.EnregistrerModel(c);
	}
	@RequestMapping(value = "modelPublicitaires",method = RequestMethod.GET)
	public List<ModelPublicitaire> listModel() {
		return modelPublicitaireMetier.listModel();
	}
	@RequestMapping(value = "modelPublicitaires/{id}",method = RequestMethod.GET)
	public ModelPublicitaire getModel(@PathVariable Long id) {
		return modelPublicitaireMetier.getModel(id);
	}
	@RequestMapping(value = "modelPublicitaires/{id}",method = RequestMethod.DELETE)
	public String supprimerModel(@PathVariable Long id) {
		modelPublicitaireMetier.supprimerModel(id);
		return "supprission avec succée";
	}
	@RequestMapping(value = "modelPublicitaires",method = RequestMethod.PUT)
	public ModelPublicitaire modifierModel(@RequestParam Long id,
			@RequestParam Date date_Creation,
			@RequestParam Date dateenvoie,
			@RequestParam String encodageTemplate,
			@RequestParam String name_tempate,
			@RequestParam Long id_cat) {
		return modelPublicitaireMetier.modifierModel(id, date_Creation, dateenvoie, encodageTemplate, name_tempate,
				id_cat);
	}
	@RequestMapping(value = "/modelPublicitaireByName/{nom}",method = RequestMethod.GET)
	public List<ModelPublicitaire> getModelByNom(@PathVariable String nom) {
		return modelPublicitaireMetier.getModelByNom(nom);
	}
	
	@RequestMapping(value = "/modelPublicitairesEnvoie",method = RequestMethod.POST)
	public String envoieModel(@RequestParam Long id_c,@RequestParam Long id_u,@RequestParam Long id_m) {
		Utilisateur u=utilisateurMetier.getUtilisateur(id_u);
		Client c=clientMetier.getClient(id_c);
		ModelPublicitaire m=modelPublicitaireMetier.getModel(id_m);
		modelPublicitaireMetier.EnvoieModel(c, u, m);
		return "votre message a été envoyer avec sucée";
	}
	
	
	
	@RequestMapping(value = "/modelPublicitairesEnvoieAllClient",method = RequestMethod.POST)
	public String envoieModelMultiple(@RequestParam Long id_u,@RequestParam Long id_m) {
		System.out.println("************************************************");
		Utilisateur u=utilisateurMetier.getUtilisateur(id_u);
		System.out.println("************************************************");
		System.out.println("user :"+u.getEmail());
		System.out.println("************************************************");
		List<Client> c=clientMetier.listeClient();
		System.out.println("client :"+c.get(0).getEmail());
		ModelPublicitaire m=modelPublicitaireMetier.getModel(id_m);
		System.out.println("************************************************");
		System.out.println("model :"+m.getNameTempate());
		modelPublicitaireMetier.EnvoieModelToMultipleClient(c, u, m);
		return "votre message a été envoyer avec sucée";
	}
	@RequestMapping(value = "/modelPublicitairesEnvoieClientByCategorie/{lsIdc}",method = RequestMethod.POST)
	public String envoieModelMultipleByCategorie(@RequestParam Long id_u,@RequestParam Long id_m,@PathVariable List<Long> lsIdc) {
		System.out.println("************************************************");
		Utilisateur u=utilisateurMetier.getUtilisateur(id_u);
		System.out.println("************************************************");
		System.out.println("user :"+u.getEmail());
		System.out.println("************************************************");
		List<Client> c=new ArrayList<Client>();
		for(int i=0;i<lsIdc.size();i++) {
			c.add(clientMetier.getClient(lsIdc.get(i)));
		}
		ModelPublicitaire m=modelPublicitaireMetier.getModel(id_m);
		System.out.println("************************************************");
		System.out.println("model :"+m.getNameTempate());
		modelPublicitaireMetier.EnvoieModelToMultipleClient(c, u, m);
		return "votre message a été envoyer avec sucée";
	}
	
	@RequestMapping(value = "/modelPublicitairesHtmlImageEnvoie",method = RequestMethod.POST)
	public String envoieModelHtmlImage(@RequestParam Long id_c,@RequestParam Long id_u,@RequestParam Long id_m) {
		Utilisateur u=utilisateurMetier.getUtilisateur(id_u);
		Client c=clientMetier.getClient(id_c);
		ModelPublicitaire m=modelPublicitaireMetier.getModel(id_m);
		Collection<Ligne_M_C> lc =  m.getLigneMC();
		Ligne_M_C l=new Ligne_M_C(c, m, 0);
		lc.add(l);
		m.setLigneMC(lc);
		ligne_M_CRepository.save(l);
		Collection<Utilisateur> lu =  m.getUsers();
		 lu.add(u);
		 m.setUsers(lu);
		modelPublicitaireMetier.EnvoieModelHtmlETtImage(c, u, m);
		return "votre message a été envoyer avec sucée";
	}
	
	
}
