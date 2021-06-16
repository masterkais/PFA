package com.pfa.metier;

import java.util.Date;
import java.util.List;

import com.pfa.entities.Client;
import com.pfa.entities.Image;
import com.pfa.entities.ModelPublicitaire;
import com.pfa.entities.Utilisateur;



public interface ModelPublicitaireMetier {
	 ModelPublicitaire EnregistrerModel(ModelPublicitaire c);
	 List<ModelPublicitaire> listModel();
	 ModelPublicitaire getModel(Long id);
	 String supprimerModel(Long id);
	 ModelPublicitaire modifierModel(Long id,Date dateCreation,String encodageTemplate,String nametemplate,Long id_cat);
	 List<ModelPublicitaire> getModelByNom(String nom);
	 String EnvoieModel(Client c,Utilisateur u,ModelPublicitaire m);
	 String EnvoieModelHtmlETtImage(Client c,Utilisateur u,ModelPublicitaire m);
	 String EnvoieModelToMultipleClient(List<Client> lc, Utilisateur u, ModelPublicitaire m);
	 Image GetImageByPath(String path);
	 Image EnregistrerImage(Image i);
	 Image GetImageByName(String name);
	 void ModifierDateEnvoi(Long id,Date d);
}
