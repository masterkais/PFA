package com.pfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfa.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query("select o from Image o where o.path=:path ")
	public Image getImageByname(@Param("path") String name);
	
	
	@Query("select o from Image o where o.name=:name ")
	public Image getImageByPah(@Param("name") String name);

}
