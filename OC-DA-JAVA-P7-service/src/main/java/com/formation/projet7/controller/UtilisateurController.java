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
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.UserRepo;
import com.formation.projet7.service.jpa.UserService;

@RestController
@RequestMapping("/biblio")
public class UtilisateurController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<Utilisateur> tousLesUtilisateurs(){
		
		List<Utilisateur> users = userService.listerUsers();
		return users;
		
	}
	
	@PostMapping("/users/{id}")
	public ResponseEntity<?> oneUser(@PathVariable Integer id) {
		
		Utilisateur user = userService.obtenirUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
