package com.projet.rpg.evenement;

import com.projet.rpg.joueur.Joueur;
import com.projet.rpg.pnj.Pnj;

public abstract class EvenementAvecPnj extends Evenement {
	Pnj pnj;

	public EvenementAvecPnj() {
		super();
	}

	public EvenementAvecPnj(String background, int butinArgent, Joueur joueur, Pnj pnj) {
		super(background, butinArgent, joueur);
		this.pnj = pnj;
	}

	public Pnj getPnj() {
		return pnj;
	}

	public void setPnj(Pnj pnj) {
		this.pnj = pnj;
	}

}
