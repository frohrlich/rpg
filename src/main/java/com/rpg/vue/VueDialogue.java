package com.rpg.vue;

import java.util.List;

import com.rpg.entities.PersonnageEntity;

public class VueDialogue extends VueAvecPnj {

	public VueDialogue() {
		super();
	}

	public VueDialogue(String background, String texte, List<Option> options, PersonnageEntity joueur,
			PersonnageEntity pnj) {
		super(background, texte, options, joueur, pnj);
	}

	public VueDialogue(String background, String texte, PersonnageEntity joueur, PersonnageEntity pnj) {
		super(background, texte, joueur, pnj);
	}

}
