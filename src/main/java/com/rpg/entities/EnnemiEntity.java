package com.rpg.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

// On fait du "code first"

@Table(name="ennemi")
@Entity
@PrimaryKeyJoinColumn(name="pnj_id")
public class EnnemiEntity extends PnjEntity {
	
	public EnnemiEntity() {
		super();
	}

	public EnnemiEntity(int inventaireId, int dialogueId, String nom, Sexe sexe, Role role, int niveau,
			int pv, int pvMax, int forcePersonnage, int agilite, int defense, int argent, String apparence) {
		super(inventaireId, dialogueId, nom, sexe, role, niveau, pv, pvMax, forcePersonnage, agilite, defense, argent,
				apparence);
	}

	@Override
	public String toString() {
		return super.toString() + "EnnemiEntity []";
	}

}
