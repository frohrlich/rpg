package com.rpg.evenement;

import com.rpg.entities.JoueurEntity;
import com.rpg.entities.PnjEntity;

public abstract class EvenementAvecPnj extends Evenement {
	PnjEntity pnj;

	public EvenementAvecPnj() {
		super();
	}

	public EvenementAvecPnj(String background, int butinArgent, JoueurEntity joueur, PnjEntity pnj) {
		super(background, butinArgent, joueur);
		this.pnj = pnj;
	}

	public PnjEntity getPnj() {
		return pnj;
	}

	public void setPnj(PnjEntity pnj) {
		this.pnj = pnj;
	}

}
