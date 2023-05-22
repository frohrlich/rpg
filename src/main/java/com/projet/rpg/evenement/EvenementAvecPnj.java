package com.projet.rpg.evenement;

/**
 * Classe fille de 'Evenement' : on y ajouter un Pnj.
 * Classe mère de 'EvenementCombat, 'EvenementDialogue'...
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
public abstract class EvenementAvecPnj extends Evenement {
	protected Pnj pnj;

	public EvenementAvecPnj(String background, int butinArgent, Joueur joueur, Pnj pnj) {
		super(background, butinArgent, joueur);
		this.pnj = pnj;
	}

	

}
