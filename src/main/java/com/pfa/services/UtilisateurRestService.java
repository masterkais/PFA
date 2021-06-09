package com.pfa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.dao.GlobalAdminRepository;
import com.pfa.dao.UtilisateurRepository;
import com.pfa.entities.AdminSysteme;
import com.pfa.entities.GlobalAdmin;
import com.pfa.entities.SimpleAdmin;
import com.pfa.entities.Utilisateur;
import com.pfa.metier.ClientMetier;
import com.pfa.metier.UtilisateurMetier;

@RestController
public class UtilisateurRestService {
	@Autowired
	UtilisateurRepository urr;
	@Autowired
	UtilisateurMetier utilisateurMetier;
	@Autowired
	ClientMetier clientMetier;
	@Autowired
	GlobalAdminRepository globalAdminRepository;
	@Autowired
	UtilisateurRepository ur;

	@RequestMapping(value = "/adminSystem/{code}", method = RequestMethod.POST)
	public Utilisateur EnregistrerAdminSystem(AdminSysteme c, @PathVariable Long code) {
		// TODO Auto-generated method stub
		GlobalAdmin a = globalAdminRepository.findById(code).get();
		c.setGlobalAdmin(a);
		c.setAdminSysteme(1);
		return utilisateurMetier.EnregistrerUtilisateur(c);
	}

	@RequestMapping(value = "/simpleAdmin/{code}", method = RequestMethod.POST)
	public Utilisateur EnregistrerSimpleAdmin(SimpleAdmin c, @PathVariable Long code) {
		// TODO Auto-generated method stub
		GlobalAdmin a = globalAdminRepository.findById(code).get();
		c.setGlobalAdmin(a);
		c.setSimpleAdmin(1);
		return ur.save(c);
	}

	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
	public List<Utilisateur> listUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurMetier.listeUtilisateur();
	}

	@RequestMapping(value = "/utilisateurs/{code}", method = RequestMethod.GET)
	public Utilisateur getUtilisateur(@PathVariable Long code) {
		System.out.println("****************************" + code);
		// TODO Auto-generated method stub
		return utilisateurMetier.getUtilisateur(code);
	}

	@RequestMapping(value = "/utilisateurs/{x}", method = RequestMethod.DELETE)
	public String supprimerUtilisateur(@PathVariable Long x) {
		System.out.println("*********" + x);
		utilisateurMetier.supprimerUtilisateur(x);
		return "Supprission avec succée";
	}

	@RequestMapping(value = "/utilisateurs", method = RequestMethod.PUT)
	public Utilisateur modifierUtilisateur(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom,
			@RequestParam String email, @RequestParam String login, @RequestParam String password) {
		// TODO Auto-generated method stub
		return utilisateurMetier.modifierUtilisateur(id, nom, prenom, email, login, password);
	}

	
	/* login by login et password */
	
	@RequestMapping(value = "/utilisateurs/{x}/{y}", method = RequestMethod.GET)
	public Utilisateur getUtilisateurByLoginAndPassword(@PathVariable String x, @PathVariable String y) {
		return utilisateurMetier.getUtilisateurByLoginAndPassword(x, y);
	}

	/*get user by login*/
	
	@RequestMapping(value = "/utilisateurs/{x}", method = RequestMethod.GET)
	public Utilisateur getUtilisateurByLoginAndPassword(@PathVariable String x) {
		return urr.getUtilisateurByLogin(x);
	}
}
