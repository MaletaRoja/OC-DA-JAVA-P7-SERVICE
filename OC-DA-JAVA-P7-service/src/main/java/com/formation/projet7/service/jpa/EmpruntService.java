package com.formation.projet7.service.jpa;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.constants.Constants;
import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.EmpruntAux;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.EmpruntRepo;
import com.formation.projet7.repository.ExemplaireRepo;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.service.IEmpruntService;
import com.formation.projet7.service.utils.OuvrageOutil;

@Service
public class EmpruntService implements IEmpruntService {
	
	@Autowired
	EmpruntRepo empruntRepo;
	
	@Autowired
	OuvrageRepo ouvrageRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ExemplaireRepo exemplaireRepo;
	
	@Override
	public List<Emprunt> listerUserEmprunt(Utilisateur user) {
		
		List<Emprunt> emprunts = empruntRepo.findByEmprunteur(user);
		return emprunts;
	}

	@Override
	public List<Exemplaire> listerOuvrageEmprunts(Ouvrage ouvrage) {
		
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		return exemplaires;
	}

	/*
	@Override
	public void enregistrerEmprunt(Emprunt emprunt) {
		empruntRepo.save(emprunt);
		
	}
	*/
	
	@Override
	public void enregistrerEmprunt(String rubrique, Integer id, Utilisateur utilisateur) {
		
		LocalDateTime debut =  LocalDateTime.now();
		//LocalDateTime fin = debut.plus(Constants.DELAY_MOUNTS, ChronoUnit.MONTHS);
		LocalDateTime fin = debut.plus(Constants.DELAY_MIN, ChronoUnit.MINUTES);
		Emprunt emprunt = new Emprunt();
		emprunt.setActif(true);
		emprunt.setDebut(debut);
		emprunt.setFin(fin);
		emprunt.setEmprunteur(utilisateur);
		emprunt.setProlongation(false);
		
		List<Ouvrage> ouvrages = ouvrageRepo.findByGenre(rubrique);
		Ouvrage ouvrage = ouvrages.get(id);
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		
		List<Exemplaire> livrables = new OuvrageOutil().exemplairesLivrables(exemplaires);
		Exemplaire ex = livrables.get(0);
		emprunt.setExemplaire(ex);
		ex.setDisponible(false);
		exemplaireRepo.save(ex);
		System.out.println("date début: " + debut);
		System.out.println("date fin:   " + fin);
		
		empruntRepo.save(emprunt);
	}

	public void enregistrerEmprunt(EmpruntAux empruntAux) {
	
		Utilisateur utilisateur = userService.obtenirUser(empruntAux.getIdUser());
		System.out.println("nom utilisateur: " + utilisateur.getNom());
		Integer id = empruntAux.getNumero();
		String rubrique = empruntAux.getRubrique();
		
		LocalDateTime debut =  LocalDateTime.now();
		//LocalDateTime fin = debut.plus(Constants.DELAY_MOUNTS, ChronoUnit.MONTHS);
		LocalDateTime fin = debut.plus(Constants.DELAY_MIN, ChronoUnit.MINUTES);
		
		Emprunt emprunt = new Emprunt();
		emprunt.setActif(true);
		emprunt.setDebut(debut);
		emprunt.setFin(fin);
		emprunt.setEmprunteur(utilisateur);
		emprunt.setProlongation(false);
		List<Ouvrage> ouvrages = new ArrayList<Ouvrage>();
		if (rubrique.equals("toutes")) {
			
			ouvrages = ouvrageRepo.findAll();
			System.out.println("rubrique: toutes");
			System.out.println("taille ouvrages: " + ouvrages.size());
			
		}else {
			
			ouvrages = ouvrageRepo.findByGenre(rubrique);
			System.out.println("***rubrique: " + rubrique);
			System.out.println("taille ouvrages: " + ouvrages.size());
		}
		
		Ouvrage ouvrage = ouvrages.get(id);
		
		System.out.println("titre: " + ouvrage.getTitre());
		
		List<Exemplaire> exemplaires = ouvrage.getExemplaires();
		System.out.println("taille exemplaires: " + exemplaires.size());
		List<Exemplaire> livrables = new OuvrageOutil().exemplairesLivrables(exemplaires);
		Exemplaire ex = livrables.get(0);
		emprunt.setExemplaire(ex);
		ex.setDisponible(false);
		exemplaireRepo.save(ex);
		
		System.out.println("date début: " + debut);
		System.out.println("date fin:   " + fin);
		
		empruntRepo.save(emprunt);
		
	}

}
