package com.formation.projet7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.formation.projet7.model.Ouvrage;

@Repository
public interface OuvrageRepo extends JpaRepository<Ouvrage, Integer> {
	
	@Query(value = "SELECT DISTINCT genre FROM ouvrage", nativeQuery = true)
	List<String> findRubriques();
	
	List<Ouvrage> findByGenre(String genre);

}
