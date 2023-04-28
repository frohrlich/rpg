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

	public JoueurEntity(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv,
			int pvMax, int forcePersonnage, int agilite, int defense, int argent, String apparence, int xp) {
		super(inventaireId, nom, sexe, role, niveau, pv, pvMax, forcePersonnage, agilite, defense,
				argent, apparence);
		this.xp = xp;
	}

	//-------------------------- les getters/setters----------------------------------------
	
	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	@Override
	public String toString() {
		return super.toString() + " JoueurEntity [xp=" + xp + "]";
	}
	
}
