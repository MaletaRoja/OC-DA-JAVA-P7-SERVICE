package com.formation.projet7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAux;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.service.jpa.EmpruntService;
import com.formation.projet7.service.jpa.UserService;

@RestController
@RequestMapping("/biblio")
public class EmpruntController {
	
	@Autowired
	EmpruntService empruntService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/emprunts/{id}")
	public ResponseEntity<?> tousLesEmprunts(@PathVariable Integer id){
		
		Utilisateur user = userService.obtenirUser(id);
		List<Emprunt> emprunts = empruntService.listerUserEmprunt(user);
		return new ResponseEntity<>(emprunts, HttpStatus.OK);
		
	}
	
	@PutMapping("/emprunts/save")
	public void enregistrerEmprunt(@RequestBody EmpruntAux empruntAux) {
		
		System.out.println("UserId: " +  empruntAux.getIdUser());
		System.out.println("Rubrique: " + empruntAux.getRubrique());
		System.out.println("num: " + empruntAux.getNumero());
		
		empruntService.enregistrerEmprunt(empruntAux);
	}
	

}
