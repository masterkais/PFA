package com.pfa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.pfa.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	//methode 1
	/*
	@Query("delete o from Utilisateur o where o.id_user=:x")
	public void SupprimerUser(@Param("x") Long x);
	*/
	@Query("select o from Utilisateur o where o.login=:x and o.password=:y")
	public Utilisateur getUtilisateurByLoginAndPassword(@Param("x") String x,@Param("y") String y);
	
	
	@Query("select o from Utilisateur o where o.email=:email")
	public Utilisateur getUtilisateurByEmail(@Param("email") String email);
	
	
	@Query("select o from Utilisateur o where o.login=:login")
	public Utilisateur getUtilisateurByLogin(@Param("login") String login);
	
}
