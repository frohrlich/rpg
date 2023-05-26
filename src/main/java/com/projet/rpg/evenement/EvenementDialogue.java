package com.projet.rpg.evenement;

/**
 * Classe fille de 'EvenementAvecPnj'.
 * N'ajoute rien donc potentiellement à supprimer...
 */

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
