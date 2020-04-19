package com.formation.projet7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.projet7.model.Ouvrage;

public interface OuvrageRepo extends JpaRepository<Ouvrage, Integer> {

}
