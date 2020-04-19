package com.formation.projet7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.service.jpa.OuvrageService;

@RestController
@RequestMapping("/biblio")
public class OuvragesController {
	
	@Autowired
	OuvrageService ouvrageService;
	
	@GetMapping("/ouvrage/liste")
	public List<Ouvrage> tousLesOuvrages(){
		
		List<Ouvrage> ouvrages = ouvrageService.listerOuvrages();
 		return ouvrages; 
	}
	
	@GetMapping("/ouvrage/{id}")
	public ResponseEntity<?> unOuvrage(@PathVariable Integer id) {
		
		Ouvrage ouvrage = ouvrageService.obtenirOuvrage(id);
		return new ResponseEntity<>(ouvrage, HttpStatus.OK);
	}
	

	@GetMapping("/ouvrage/rubriques")
	public List<String> toutesLesRubriques(){
		
		List<String> genres = ouvrageService.genres();
		return genres;
	}
	
	

}
