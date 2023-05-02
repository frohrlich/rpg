package com.rpg.thebest.personnage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pnj extends Personnage {

	@Column(length = 8192)
	private String dialogue;
	
	public Pnj(int i, String string, Sexe m, Role ep, int j, int k, int l, int n, int o, int p, int q, String string2) {
		// TODO Auto-generated constructor stub
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
