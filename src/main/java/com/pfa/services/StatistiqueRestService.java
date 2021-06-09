package com.pfa.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/nbrClient", method = RequestMethod.GET)
	public int getNbrClient() {
		return cr.getNbrClient();
	}

	@RequestMapping(value = "/nbrClientByPaye/{code}", method = RequestMethod.GET)
	public int getNbrClientByPaye(@PathVariable String code) {
		return cr.getNbrByPaye(code);
	}

	@RequestMapping(value = "/nbrClientByRegion/{code}", method = RequestMethod.GET)
	public int getNbrClientByRegion(@PathVariable String code) {
		return cr.getNbrClientByRegion(code);
	}

	@RequestMapping(value = "/nbrClientByVille/{code}", method = RequestMethod.GET)
	public int getNbrClientByVille(@PathVariable String code) {
		return cr.getNbrClientVille(code);
	}

	@RequestMapping(value = "/nbrClientBySexe/{code}", method = RequestMethod.GET)
	public int getNbrClientBySexe(@PathVariable String code) {
		return cr.getNbrClientBySexe(code);
	}

	@RequestMapping(value = "/nbrVu", method = RequestMethod.GET)
	public int getNbrvu() {
		return ligne_M_CRepository.getNbrVuTotal();
	}

	@RequestMapping(value = "/nbrNoVu", method = RequestMethod.GET)
	public int getNbrNovu() {
		return ligne_M_CRepository.getNbrNoVuTotal();
	}

	@RequestMapping(value = "/nbrLigneConv", method = RequestMethod.GET)
	public int getNbrLigneConversation() {
		return ligne_M_CRepository.getNbrLigneConversation();
	}

	@RequestMapping(value = "/nbrVuByPaye/{paye}", method = RequestMethod.GET)
	public int getNbrVuByPaye(@PathVariable String paye) {

		return ligne_M_CRepository.getNbrVuParPaye(paye);
	}

	@RequestMapping(value = "/nbrVuByRegion/{region}", method = RequestMethod.GET)
	public int getNbrVuByRgion(@PathVariable String region) {

		return ligne_M_CRepository.getNbrVuParRegion(region);
	}

	@RequestMapping(value = "/nbrVuByVille/{ville}", method = RequestMethod.GET)
	public int getNbrVuByVille(@PathVariable String ville) {

		return ligne_M_CRepository.getNbrVuParVille(ville);
	}

	@RequestMapping(value = "/nbrVuBySexe/{sexe}", method = RequestMethod.GET)
	public int getNbrVuBySexe(@PathVariable String sexe) {

		return ligne_M_CRepository.getNbrVuParSexe(sexe);
	}

	@RequestMapping(value = "/getNomsCat", method = RequestMethod.GET)
	public List<String> getgetNomsCat() {
		return catr.getNomsCategories();
	}

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
