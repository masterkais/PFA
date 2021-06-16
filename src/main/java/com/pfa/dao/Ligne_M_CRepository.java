package com.pfa.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.Ligne_M_C;

public interface Ligne_M_CRepository extends JpaRepository<Ligne_M_C, Long> {

	@Query("SELECT COUNT(*) FROM Ligne_M_C")
	public int getNbrLigneConversation();
	
	@Query("select COUNT(*) from Ligne_M_C o where o.etat=1 ")
	public int getNbrVuTotal();
	
	@Query("select COUNT(*) from Ligne_M_C o where o.etat=-1 ")
	public int getNbrNoVuTotal();
	@Query("select COUNT(*) from Ligne_M_C l ,Client c where Client_ID=c.id_client and l.etat=1 and c.paye=:paye ")
	public int getNbrVuParPaye(@Param("paye") String paye);
	
	@Query("select COUNT(*) from Ligne_M_C l ,Client c where Client_ID=c.id_client and l.etat=1 and c.region=:region ")
	public int getNbrVuParRegion(@Param("region") String region);
	
	@Query("select COUNT(*) from Ligne_M_C l ,Client c where Client_ID=c.id_client and l.etat=1 and c.ville=:ville ")
	public int getNbrVuParVille(@Param("ville") String ville);
	
	@Query("select COUNT(*) from Ligne_M_C l , Client c where Client_ID=c.id_client and l.etat=-1 and c.sexe=:sexe ")
	public int getNbrVuParSexe(@Param("sexe") String sexe);
	
}
