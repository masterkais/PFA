package com.pfa.services;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.dao.ImageRepository;
import com.pfa.entities.Image;

@RestController
public class ImageRestService {
@Autowired
ImageRepository imageRepository;
@RequestMapping(value = "/images",method = RequestMethod.POST)
public Image EnregistrerImage(Image i) {
	return imageRepository.save(i);
}

@RequestMapping(value = "/images/{code}",method = RequestMethod.DELETE)
public String supprimerImg(@PathVariable Long code) {
	
	imageRepository.deleteById(code);
	return "Supprission avec succée";
}

@RequestMapping(value = "/images/{code}",method = RequestMethod.GET)
public Image getImg(@PathVariable Long code) {
	
	return imageRepository.findById(code).get() ;
}

@RequestMapping(value = "/images",method = RequestMethod.GET)
public List<Image> getAllImg() {
	
	return imageRepository.findAll() ;
}



}
