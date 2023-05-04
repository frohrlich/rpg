package com.projet.rpg.personnage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnageRepository extends JpaRepository<Personnage, Integer> {

	Optional<Personnage> findByNom(String name);
}

	
