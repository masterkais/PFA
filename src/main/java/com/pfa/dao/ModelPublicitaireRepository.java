package com.pfa.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.ModelPublicitaire;

public interface ModelPublicitaireRepository extends JpaRepository<ModelPublicitaire, Long> {
	
	@Query("select o from ModelPublicitaire o where o.nameTemplate=:nom ")
	public List<ModelPublicitaire> getModelByNom(@Param("nom") String nom);
	
	@Query("select COUNT(*) from ModelPublicitaire m INNER JOIN m.categorie c where c.nom_categorie=:nom")
	public int getNbrModelByCategorie(@Param("nom") String nom);
	

	
}
