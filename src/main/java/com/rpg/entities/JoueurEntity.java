package com.rpg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "joueur")
@Entity
public class JoueurEntity extends PersonnageEntity {
	@Column(name = "xp", nullable = false)
	int xp;

	public JoueurEntity() {
	}

	public JoueurEntity(int inventaireId, int dialogueId, String nom, String sexe, String classe, int niveau, int pv,
			int pvMax, int forcePersonnage, int agilite, int defense, int argent, int xp) {
		super(inventaireId, dialogueId, nom, sexe, classe, niveau, pv, pvMax, forcePersonnage, agilite, defense,
				argent);
		this.xp = xp;
	}

	//-------------------------- les getters/setters----------------------------------------
	
	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	// -------------------------- FIN les
	// getters/setters----------------------------------------

}
