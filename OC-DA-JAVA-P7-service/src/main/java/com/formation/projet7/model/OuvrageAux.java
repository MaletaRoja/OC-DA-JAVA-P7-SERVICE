package com.formation.projet7.model;

import java.util.ArrayList;
import java.util.List;

public class OuvrageAux {
	
	private Integer id;
	private String titre;
	private String auteur_nom;
	private String auteur_prenom;
	private String edition;
	private String genre;
	private ExemplaireAux[] exemplairesAux;
	
	public OuvrageAux() {
		
	}

	public OuvrageAux(Ouvrage ouvrage) {
		
		this.id = ouvrage.getId();
		this.titre = ouvrage.getTitre();
		this.auteur_nom = ouvrage.getAuteur_nom();
		this.auteur_prenom = ouvrage.getAuteur_prenom();
		this.edition = ouvrage.getEdition();
		this.genre = ouvrage.getGenre();
		//this.exemplairesAux = new Converter().convertExemplaires(ouvrage.getExemplaires());
		this.exemplairesAux = ouvrage.getExemplaires().toArray(new ExemplaireAux[ouvrage.getExemplaires().size()]);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur_nom() {
		return auteur_nom;
	}

	public void setAuteur_nom(String auteur_nom) {
		this.auteur_nom = auteur_nom;
	}

	public String getAuteur_prenom() {
		return auteur_prenom;
	}

	public void setAuteur_prenom(String auteur_prenom) {
		this.auteur_prenom = auteur_prenom;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public ExemplaireAux[] getExemplairesAux() {
		return exemplairesAux;
	}

	public void setExemplairesAux(ExemplaireAux[] exemplairesAux) {
		this.exemplairesAux = exemplairesAux;
	}

	

	
	

}


