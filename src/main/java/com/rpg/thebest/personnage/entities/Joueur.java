package com.rpg.thebest.personnage.entities;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("joueur")
public class Joueur extends Personnage {

	@Column
	int xp;

	// A voir si ce constructeur est bien nécessaire	
	public Joueur(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv,
			int pvMax, int forcePersonnage, int agilite, int defense, int argent,
			String apparence, int xp) {
		// TODO Auto-generated constructor stub
	}
}
