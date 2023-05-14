package com.projet.rpg.evenement;

import org.springframework.stereotype.Component;

import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class EvenementDialogue extends EvenementAvecPnj {

	public EvenementDialogue(String background, int butinArgent, Joueur joueur, Pnj pnj) {
		super(background, butinArgent, joueur, pnj);
	}

}
