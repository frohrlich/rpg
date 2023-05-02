package com.rpg.thebest.evenement;

import com.rpg.thebest.personnage.entities.Joueur;
import com.rpg.thebest.vue.Vue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class Evenement {

	protected String background;
	protected int butinArgent;
	protected int etape;
	protected Joueur joueur;

	public Evenement(String background, int butinArgent, Joueur joueur) {
		super();
		this.background = background;
		this.butinArgent = butinArgent;
		this.etape = 1;
		this.joueur = joueur;
	}

	public Vue nextVue() {
		return null;
	}

	public void modifieCaracs() {
	}

}
