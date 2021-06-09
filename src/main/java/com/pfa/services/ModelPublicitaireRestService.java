package com.pfa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.dao.Ligne_M_CRepository;
import com.pfa.entities.Categorie;
import com.pfa.entities.Client;
import com.pfa.entities.Image;
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

	/*
	 * enregistrer une template : tu donne seulement le nom des image dans le "path"
	 * et dans le "bodyparam" : objet Model , id_cat : categorie ou appartient (pour
	 * filtre by categorie") Nb ******** SANS PASSER LE DATE D'ENVOIE IL PREND NULL
	 * *******************
	 */

	@RequestMapping(value = "modelPublicitaires/{ls}", method = RequestMethod.POST)
	public ModelPublicitaire EnregistrerModel(ModelPublicitaire c, @RequestParam Long id_cat,
			@PathVariable List<String> ls) {
		Categorie k = categorieMetier.getCategorie(id_cat);
		c.setCategorie(k);
		List<Image> im = new ArrayList<Image>();

		for (int i = 0; i < ls.size(); i++) {
			String name = ls.get(i);
			Image ii = modelPublicitaireMetier.GetImageByName(name);
			im.add(ii);

		}
		c.setLm(im);
		return modelPublicitaireMetier.EnregistrerModel(c);
	}

	/* model publicitaire save sans images */
	@RequestMapping(value = "modelPublicitaires", method = RequestMethod.POST)
	public ModelPublicitaire EnregistrerModelV2(ModelPublicitaire c, @RequestParam Long id_cat) {
		Categorie k = categorieMetier.getCategorie(id_cat);
		c.setCategorie(k);
		c.setLm(null);
		return modelPublicitaireMetier.EnregistrerModel(c);
	}

	/* LISTER TOUS LES model qui existe */

	@RequestMapping(value = "modelPublicitaires", method = RequestMethod.GET)
	public List<ModelPublicitaire> listModel() {
		return modelPublicitaireMetier.listModel();
	}

	/* get un Model par id ******passer l'id de model dans le path**** */

	@RequestMapping(value = "modelPublicitaires/{id}", method = RequestMethod.GET)
	public ModelPublicitaire getModel(@PathVariable Long id) {
		return modelPublicitaireMetier.getModel(id);
	}

	/* supprimer un Model par id ******passer l'id de model dans le path**** */

	@RequestMapping(value = "modelPublicitaires/{id}", method = RequestMethod.DELETE)
	public String supprimerModel(@PathVariable Long id) {
		modelPublicitaireMetier.supprimerModel(id);
		return "supprission avec succée";
	}

	/*
	 * modifer un model avec passer tous les attributs dans le "bodyparam" sans
	 * passer le date d'envoie et les images
	 */

	/* NB ****passe l'id de model dans le ******bodyparam******** */

	@RequestMapping(value = "modelPublicitaires", method = RequestMethod.PUT)
	public ModelPublicitaire modifierModel(@RequestParam Long id, @RequestParam Date dateCreation,
			@RequestParam String EncodageTemplate, @RequestParam String nameTemplate, @RequestParam Long id_cat) {
		return modelPublicitaireMetier.modifierModel(id, dateCreation, EncodageTemplate, nameTemplate, id_cat);
	}

	/*
	 * get objet model par le nom du template avec passer le nom du template dans le
	 * path params
	 */

	@RequestMapping(value = "/modelPublicitaireByName/{nom}", method = RequestMethod.GET)
	public List<ModelPublicitaire> getModelByNom(@PathVariable String nom) {
		return modelPublicitaireMetier.getModelByNom(nom);
	}

	/*
	 * envoyer model publicitaire a un seul client avec passée les attribut
	 * 
	 * id utilisateur et id model publicitaire et id de client dans le bodyparams
	 */

	@RequestMapping(value = "/modelPublicitairesEnvoie", method = RequestMethod.POST)
	public String envoieModel(@RequestParam Long id_c, @RequestParam Long id_u, @RequestParam Long id_m) {
		Utilisateur u = utilisateurMetier.getUtilisateur(id_u);
		Client c = clientMetier.getClient(id_c);
		ModelPublicitaire m = modelPublicitaireMetier.getModel(id_m);
		modelPublicitaireMetier.EnvoieModel(c, u, m);
		Ligne_M_C lmc = new Ligne_M_C(c, m, 1);
		ligne_M_CRepository.save(lmc);
		modelPublicitaireMetier.ModifierDateEnvoi(id_m, new Date());
		return "votre message a été envoyer avec sucée";
	}

	/*
	 * envoyer model publicitaire a tous les client dans la base de donnée avec
	 * passée les attribut
	 * 
	 * id utilisateur et id model publicitaire dans le bodyparams
	 */

	@RequestMapping(value = "/modelPublicitairesEnvoieAllClient", method = RequestMethod.POST)
	public String envoieModelMultiple(@RequestParam Long id_u, @RequestParam Long id_m) {
		System.out.println("************************************************");
		Utilisateur u = utilisateurMetier.getUtilisateur(id_u);
		System.out.println("************************************************");
		System.out.println("user :" + u.getEmail());
		System.out.println("************************************************");
		List<Client> c = clientMetier.listeClient();
		System.out.println("client :" + c.get(0).getEmail());
		ModelPublicitaire m = modelPublicitaireMetier.getModel(id_m);
		System.out.println("************************************************");
		System.out.println("model :" + m.getNameTemplate());
		modelPublicitaireMetier.EnvoieModelToMultipleClient(c, u, m);
		modelPublicitaireMetier.ModifierDateEnvoi(id_m, new Date());
		return "votre message a été envoyer avec sucée";
	}

	/*
	 * envoyer model publicitaire a une liste des clients (une partie de la base
	 * client)
	 */
	/* passer une list des id de clients dans le pathparam */
	/* passer id_utilisateur et id_model dans le bodyparam */

	@RequestMapping(value = "/modelPublicitairesEnvoieClientByCategorie/{lsIdc}", method = RequestMethod.POST)
	public String envoieModelMultipleByCategorie(@RequestParam Long id_u, @RequestParam Long id_m,
			@PathVariable List<Long> lsIdc) {
		Utilisateur u = utilisateurMetier.getUtilisateur(id_u);
		System.out.println("user :" + u.getEmail());
		List<Client> c = new ArrayList<Client>();
		for (int i = 0; i < lsIdc.size(); i++) {
			c.add(clientMetier.getClient(lsIdc.get(i)));
		}
		ModelPublicitaire m = modelPublicitaireMetier.getModel(id_m);
		modelPublicitaireMetier.EnvoieModelToMultipleClient(c, u, m);
		modelPublicitaireMetier.ModifierDateEnvoi(id_m, new Date());
		return "votre message a été envoyer avec sucée";
	}

	/* pas important */

	@RequestMapping(value = "/modelPublicitairesHtmlImageEnvoie", method = RequestMethod.POST)
	public String envoieModelHtmlImage(@RequestParam Long id_c, @RequestParam Long id_u, @RequestParam Long id_m) {
		Utilisateur u = utilisateurMetier.getUtilisateur(id_u);
		Client c = clientMetier.getClient(id_c);
		ModelPublicitaire m = modelPublicitaireMetier.getModel(id_m);
		Collection<Ligne_M_C> lc = m.getLigneMC();
		Ligne_M_C l = new Ligne_M_C(c, m, 0);
		lc.add(l);
		m.setLigneMC(lc);
		ligne_M_CRepository.save(l);
		Collection<Utilisateur> lu = m.getUsers();
		lu.add(u);
		m.setUsers(lu);
		modelPublicitaireMetier.EnvoieModelHtmlETtImage(c, u, m);
		return "votre message a été envoyer avec sucée";
	}

}
