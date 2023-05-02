package com.rpg.thebest.vue;

import java.util.List;

import com.rpg.thebest.personnage.entities.Personnage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VueDialogue extends VueAvecPnj {

	public VueDialogue(String background, String texte, List<Option> options, Personnage joueur,
			Personnage pnj) {
		super(background, texte, options, joueur, pnj);
	}

	public VueDialogue(String background, String texte, Personnage joueur, Personnage pnj) {
		super(background, texte, joueur, pnj);
	}

}
