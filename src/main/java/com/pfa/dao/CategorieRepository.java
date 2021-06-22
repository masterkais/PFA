package com.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{
	@Query("select c.nom_categorie from  Categorie c")
	public List<String> getNomsCategories();
}
