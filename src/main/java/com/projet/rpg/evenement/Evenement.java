package com.projet.rpg.evenement;

import org.springframework.stereotype.Component;

import com.projet.rpg.personnage.joueur.Joueur;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public abstract class Evenement {

	protected String background;
	protected int butinArgent;
	protected int etape;
	protected Joueur joueur;

	public Evenement(String background, int butinArgent, Joueur joueur) {
		this.background = background;
		this.butinArgent = butinArgent;
		this.etape = 1;
		this.joueur = joueur;
	}

}
