package com.formation.projet7.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.projet7.model.Emprunt;
import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.Ouvrage;
import com.formation.projet7.model.Utilisateur;
import com.formation.projet7.repository.EmpruntRepo;
import com.formation.projet7.repository.OuvrageRepo;
import com.formation.projet7.service.IEmpruntService;

@Service
public class EmpruntService implements IEmpruntService {
	
	@Autowired
	EmpruntRepo empruntRepo;
	
	@Autowired
	OuvrageRepo ouvrageRepo;
	
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

}
