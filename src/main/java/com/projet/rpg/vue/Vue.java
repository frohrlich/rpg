package com.projet.rpg.vue;

import java.util.ArrayList;
import java.util.List;

import com.projet.rpg.personnage.Personnage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vue {
	protected String background;
	protected String texte;
	protected List<Option> options;
	protected Personnage joueur;

	// Constructor without options
	public Vue(String background, String texte, Personnage joueur) {
		this.background = background;
		this.texte = texte;
		this.options = new ArrayList<Option>();
		this.joueur = joueur;
	}

}
