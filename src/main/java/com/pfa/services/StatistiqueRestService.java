package com.pfa.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.dao.CategorieRepository;
import com.pfa.dao.ClientRepository;
import com.pfa.dao.Ligne_M_CRepository;
import com.pfa.dao.ModelPublicitaireRepository;
import com.pfa.dao.UtilisateurRepository;

@RestController
public class StatistiqueRestService {
	@Autowired
	ClientRepository cr;
	@Autowired
	ModelPublicitaireRepository mr;
	@Autowired
	UtilisateurRepository ur;
	@Autowired
	CategorieRepository catr;
	@Autowired
	Ligne_M_CRepository ligne_M_CRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrClient", method = RequestMethod.GET)
	public int getNbrClient() {
		return cr.getNbrClient();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrClientByPaye/{code}", method = RequestMethod.GET)
	public int getNbrClientByPaye(@PathVariable String code) {
		return cr.getNbrByPaye(code);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrClientByRegion/{code}", method = RequestMethod.GET)
	public int getNbrClientByRegion(@PathVariable String code) {
		return cr.getNbrClientByRegion(code);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrClientByVille/{code}", method = RequestMethod.GET)
	public int getNbrClientByVille(@PathVariable String code) {
		return cr.getNbrClientVille(code);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrClientBySexe/{code}", method = RequestMethod.GET)
	public int getNbrClientBySexe(@PathVariable String code) {
		return cr.getNbrClientBySexe(code);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrVu", method = RequestMethod.GET)
	public int getNbrvu() {
		return ligne_M_CRepository.getNbrVuTotal();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrNoVu", method = RequestMethod.GET)
	public int getNbrNovu() {
		return ligne_M_CRepository.getNbrNoVuTotal();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrLigneConv", method = RequestMethod.GET)
	public int getNbrLigneConversation() {
		return ligne_M_CRepository.getNbrLigneConversation();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrVuByPaye/{paye}", method = RequestMethod.GET)
	public int getNbrVuByPaye(@PathVariable String paye) {

		return ligne_M_CRepository.getNbrVuParPaye(paye);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrVuByRegion/{region}", method = RequestMethod.GET)
	public int getNbrVuByRgion(@PathVariable String region) {

		return ligne_M_CRepository.getNbrVuParRegion(region);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrVuByVille/{ville}", method = RequestMethod.GET)
	public int getNbrVuByVille(@PathVariable String ville) {

		return ligne_M_CRepository.getNbrVuParVille(ville);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/nbrVuBySexe/{sexe}", method = RequestMethod.GET)
	public int getNbrVuBySexe(@PathVariable String sexe) {

		return ligne_M_CRepository.getNbrVuParSexe(sexe);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getNomsCat", method = RequestMethod.GET)
	public List<String> getgetNomsCat() {
		return catr.getNomsCategories();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getNombreModelByCategories", method = RequestMethod.GET)
	public HashMap<String, Integer> getNombreModelByCategories() {
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		List<String> lnc = getgetNomsCat();
		for (int i = 0; i < lnc.size(); i++) {
			hmap.put(lnc.get(i), mr.getNbrModelByCategorie(lnc.get(i)));

		}
		return hmap;
	}

}
