package com.rpg.thebest.personnage.entities;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;

@Table("ennemi")
@AllArgsConstructor
public class Ennemi extends Pnj {

	// A voir si ce constructeur est bien nécessaire
	public Ennemi(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv,
		int pvMax, int forcePersonnage, int agilite, int defense, int argent,
		String apparence) {
		// TODO Auto-generated constructor stub
	}

}
