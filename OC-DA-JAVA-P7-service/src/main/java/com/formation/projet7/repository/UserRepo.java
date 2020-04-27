package com.formation.projet7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.projet7.model.Utilisateur;

public interface UserRepo extends JpaRepository<Utilisateur, Integer> {
	
	@Query(value = "select * from user u where u.nom like ?1 or u.prenom like ?1", nativeQuery = true)
	Utilisateur findByIdentity(String string);

	Utilisateur findByUsername(String email);

	Utilisateur findByUsernameAndPassword(String user, String password);

}
