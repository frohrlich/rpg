package com.rpg.thebest.personnage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpg.thebest.personnage.entities.Joueur;

public interface JoueurRepository extends JpaRepository<Joueur, Integer> {}
