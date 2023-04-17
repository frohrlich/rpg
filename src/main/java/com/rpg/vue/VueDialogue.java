package com.rpg.vue;

import java.util.List;

public class VueDialogue extends VueAvecPnj {

	public VueDialogue() {
		super();
	}

	public VueDialogue(String background, String texte, List<Option> options, Personnage joueur, Personnage pnj) {
		super(background, texte, options, joueur, pnj);
	}

	public VueDialogue(String background, String texte, Personnage joueur, Personnage pnj) {
		super(background, texte, joueur, pnj);
	}

}
