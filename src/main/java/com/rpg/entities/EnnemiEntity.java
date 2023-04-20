package com.rpg.entities;

import javax.persistence.*;

// On fait du "code first"

@Table(name="ennemi")
@Entity
@PrimaryKeyJoinColumn(name="pnj_id")
public class EnnemiEntity extends PnjEntity {
	
	public EnnemiEntity() {
		super();
	}

	public EnnemiEntity(int inventaireId, int dialogueId, String nom, Sexe sexe, Role role, int niveau,
			int pv, int pvMax, int forcePersonnage, int agilite, int defense, int argent) {
		super(inventaireId, dialogueId, nom, sexe, role, niveau, pv, pvMax, forcePersonnage, agilite, defense, argent);
	}

	@Override
	public String toString() {
		return "EnnemiEntity [getInventaireId()=" + getInventaireId() 
				+ ", getDialogueId()=" + getDialogueId()
				+ ", getNom()=" + getNom() 
				+ ", getSexe()=" + getSexe() 
				+ ", getClasse()=" + getRole()
				+ ", getNiveau()=" + getNiveau() 
				+ ", getPv()=" + getPv() 
				+ ", getPvMax()=" + getPvMax()
				+ ", getForcePersonnage()=" + getForcePersonnage() 
				+ ", getAgilite()=" + getAgilite() 
				+ ", getDefense()=" + getDefense()
				+ ", getArgent()=" + getArgent() 
				+ ", getId()=" + getId()
				+ "]";
	}
		
}
