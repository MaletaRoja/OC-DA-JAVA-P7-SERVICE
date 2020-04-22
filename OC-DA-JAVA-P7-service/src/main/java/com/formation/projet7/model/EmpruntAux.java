package com.formation.projet7.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class EmpruntAux {

	private Integer id;
	private Exemplaire exemplaire;
	private Utilisateur emprunteur;
	private LocalDateTime debut;
	private LocalDateTime fin;
	private boolean prolongation; 
	private boolean actif;
	
	public EmpruntAux() {
		
	}

	public EmpruntAux(Integer id, Exemplaire exemplaire, Utilisateur emprunteur, LocalDateTime debut, LocalDateTime fin,
			boolean prolongation, boolean actif) {
		super();
		this.id = id;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.debut = debut;
		this.fin = fin;
		this.prolongation = prolongation;
		this.actif = actif;
	}
	
		public EmpruntAux(Emprunt emprunt) {
		
		this.id = emprunt.getId();
		this.exemplaire = emprunt.getExemplaire();
		this.emprunteur = emprunt.getEmprunteur();
		this.debut = emprunt.getDebut();
		this.fin = emprunt.getFin();
		this.prolongation = emprunt.isProlongation();
		this.actif = emprunt.isActif();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public LocalDateTime getDebut() {
		return debut;
	}

	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public boolean isProlongation() {
		return prolongation;
	}

	public void setProlongation(boolean prolongation) {
		this.prolongation = prolongation;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
}
