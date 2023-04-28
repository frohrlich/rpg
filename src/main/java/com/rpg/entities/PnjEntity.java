package com.rpg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "pnj")
@Entity
public class PnjEntity extends PersonnageEntity {

	// Attributs
	@Column(name = "dialogue", length = 8192, nullable = true)
	private String dialogue;

	public PnjEntity() {
		super();
	}

	public PnjEntity(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv, int pvMax,
			int forcePersonnage, int agilite, int defense, int argent, String apparence) {
		super(inventaireId, nom, sexe, role, niveau, pv, pvMax, forcePersonnage, agilite, defense, argent, apparence);
	}

	public String getDialogue() {
		return dialogue;
	}

	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	@Override
	public String toString() {
		return super.toString() + "PnjEntity [dialogue=" + dialogue + "]";
	}

	// Méthode qui prendrait la suite des textes, des options et des récompenses et
	// qui permettrait de créer le contenu json
	public void dialogueCreation(String[] textes, int pv, int xp) {
		String contenu = "{";
		for (int i = 0; i < textes.length; i++) {
			contenu += "\"texte" + (i + 1) + "\":" + "\"" + textes[i] + "\",";
		}
		contenu += "\"pv" + "\":" + "\"" + pv + "\", " + "\"xp" + "\":" + "\"" + xp + "\"}";
		this.setDialogue(contenu);
	}

}
