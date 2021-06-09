package com.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("select o from Client o where o.email=:email")
	public List<Client> getClientByEmail(@Param("email") String email);
	
	@Query("SELECT COUNT(*) FROM Client")
	public int getNbrClient();

	@Query("SELECT COUNT(*) FROM Client  o where o.paye=:paye")
	public int getNbrByPaye(@Param("paye") String paye);
	
	@Query("SELECT COUNT(*) FROM Client  o where o.region=:region")
	public int getNbrClientByRegion(@Param("region") String region);
	
	@Query("SELECT COUNT(*) FROM Client o where o.ville=:ville")
	public int getNbrClientVille(@Param("ville") String ville);
	
	@Query("SELECT COUNT(*) FROM Client  o where o.sexe=:sexe")
	public int getNbrClientBySexe(@Param("sexe") String sexe);
	
	
}
