package com.projet.rpg.evenement;

import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
