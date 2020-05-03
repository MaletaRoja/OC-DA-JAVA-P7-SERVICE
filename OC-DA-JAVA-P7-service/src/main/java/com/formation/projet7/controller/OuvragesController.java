package com.formation.projet7.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.EmpruntAuxMail;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.OuvrageService;

@RestController
@RequestMapping("/biblio")
public class OuvragesController {
	
	@Autowired
	OuvrageService ouvrageService;
	
	@Autowired
	EmpruntService empruntService;
	
	@GetMapping("/ouvrage/liste")
	public List<OuvrageAux> tousLesOuvrages(@RequestHeader("Authorization") String token){
		
		List<Ouvrage> ouvrages = ouvrageService.listerOuvrages();
		List<OuvrageAux> listeOuvragesAux = new ArrayList<OuvrageAux>();
		for (Ouvrage ouvrage: ouvrages) {
			
			OuvrageAux o = new OuvrageAux(ouvrage);
			listeOuvragesAux.add(o);
			
		}
 		return listeOuvragesAux; 
	}
	
	@GetMapping("/ouvrage/{id}")
	public ResponseEntity<?> unOuvrage(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
		
		Ouvrage ouvrage = ouvrageService.obtenirOuvrage(id);
		return new ResponseEntity<>(ouvrage, HttpStatus.OK);
	}
	

	@GetMapping("/ouvrage/rubriques")
	public List<String> toutesLesRubriques(@RequestHeader("Authorization") String token){
		
		List<String> genres = ouvrageService.genres();
		return genres;
	}
	
	@GetMapping("/ouvrage/liste/rubrique/{rubrique}")
	public List<OuvrageAux> tousLesOuvragesParRubrique(@PathVariable  String rubrique, @RequestHeader("Authorization") String token){
		List<Ouvrage> ouvrages = ouvrageService.listerOuvragesParRubrique(rubrique);
		List<OuvrageAux> ouvragesAux = ouvrageService.obtenirOuvragesAux(ouvrages);
		return ouvragesAux;
	}
	
	@GetMapping("/ouvrage/emprunts/mail")
	public List<EmpruntAuxMail> obtenirEmpruntsActif(@RequestHeader("Authorization") String token){
		
		LocalDateTime date = LocalDateTime.now();
		List<EmpruntAuxMail> empruntsAux = empruntService.obtenirEmpruntActifParDate(date);
		System.out.println("Taille liste des emprunts envoyés au service mail: " + empruntsAux.size());
		return empruntsAux;
	}
	
	

}
