package com.rpg.thebest.personnage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg.thebest.personnage.entities.Personnage;

public interface PersonnageRepository extends JpaRepository<Personnage, Integer> {}
