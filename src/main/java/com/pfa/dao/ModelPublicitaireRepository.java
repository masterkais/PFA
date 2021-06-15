package com.pfa.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.ModelPublicitaire;
import com.pfa.entities.Utilisateur;

public interface ModelPublicitaireRepository extends JpaRepository<ModelPublicitaire, Long> {
	
	@Query("select o from ModelPublicitaire o where o.nameTempate=:nom ")
	public List<ModelPublicitaire> getModelByNom(@Param("nom") String nom);
	
}
