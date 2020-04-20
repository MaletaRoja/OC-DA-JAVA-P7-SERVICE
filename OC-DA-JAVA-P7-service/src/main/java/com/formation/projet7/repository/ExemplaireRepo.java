package com.formation.projet7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.projet7.model.Exemplaire;
import com.formation.projet7.model.ExemplaireDispo;

public interface ExemplaireRepo extends JpaRepository<Exemplaire, Integer> {
	
	
	// A revoir 
	@Query(value ="SELECT "
  			+ "new ExemplaireDispo(o.id, o.auteur_nom, o.auteur_prenom, o.edition, o.titre, e.id) "
			+ "FROM ouvrage o, exemplaire e", nativeQuery = true)
	List<ExemplaireDispo> selectExemplairesDispos();

	List<Exemplaire> findByDisponible(boolean b);
	
	List<Exemplaire> findByDisponibleAndOuvrageId(boolean b, Integer id);
	
	
	

}
