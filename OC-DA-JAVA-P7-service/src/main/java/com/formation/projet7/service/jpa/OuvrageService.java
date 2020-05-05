package com.formation.projet7.service.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.OuvrageAux;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.service.IOuvrageService;

@Service
public class OuvrageService implements IOuvrageService {

	@Autowired
	OuvrageRepo ouvrageRepo;
	
	@Override
	public List<Ouvrage> listerOuvrages() {
		
		List<Ouvrage> ouvrages = ouvrageRepo.findAll();
		return ouvrages;
	}

	@Override
	public Ouvrage obtenirOuvrage(Integer id) {
		
		Ouvrage ouvrage = ouvrageRepo.getOne(id);
		return ouvrage;
	}

	@Override
	public void ajouterOuvrage(Ouvrage ouvrage) {
		ouvrageRepo.save(ouvrage);
		
	}

	@Override
	public void modifierOuvrage(Ouvrage ouvrage) {
		ouvrageRepo.save(ouvrage);
		
	}

	@Override
	public void supprimerOuvrage(Ouvrage ouvrage) {
		ouvrageRepo.delete(ouvrage);
		
	}

	@Override
	public List<String> genres() {
		 List<String> genres = ouvrageRepo.findRubriques();
		return genres;
	}

	@Override
	public List<Ouvrage> listerOuvragesParRubrique(String rubrique) {
		
		List<Ouvrage> ouvrages = ouvrageRepo.findByGenre(rubrique);
		return ouvrages;
	}
	
	public List<OuvrageAux> obtenirOuvragesAux(List<Ouvrage> ouvrages){
		
		List<OuvrageAux> listeOuvragesAux = new ArrayList<OuvrageAux>();
		for (Ouvrage ouvrage: ouvrages) {
			
			OuvrageAux o = new OuvrageAux(ouvrage);
			listeOuvragesAux.add(o);
			
		}
			return listeOuvragesAux; 
	}
	
	public List<OuvrageAux> rechercherSimple (String phrase){
		
		System.out.println("Phrase: " + phrase);
		List<Ouvrage> ouvrages = ouvrageRepo.findByPhraseLike(phrase);
		List<OuvrageAux> ouvragesAux = obtenirOuvragesAux(ouvrages);
		return ouvragesAux;
	}
	
	
	
	String convertString(String phrase) {

		try {
			char car0 = phrase.charAt(0);
			String stringCar0 = String.valueOf(car0);
			String stringCar0Low = stringCar0.toUpperCase();
			String reste = phrase.substring(1);
			String phrase1 = stringCar0Low + reste + "%";
			System.out.println("phrase1: " + phrase1);
			return phrase1;

		} catch (StringIndexOutOfBoundsException e) {

			return "StringIndexOutOfBoundsException";
		}
	}
	

}
