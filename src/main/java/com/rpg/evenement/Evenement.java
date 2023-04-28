package com.rpg.evenement;

import com.rpg.entities.JoueurEntity;
import com.rpg.vue.Vue;

public abstract class Evenement {

	protected String background;
	protected int butinArgent;
	protected int etape;
	protected JoueurEntity joueur;

	public int getButinArgent() {
		return butinArgent;
	}

	public void setButinArgent(int butinArgent) {
		this.butinArgent = butinArgent;
	}

	public int getEtape() {
		return etape;
	}

	public void setEtape(int etape) {
		this.etape = etape;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public JoueurEntity getJoueur() {
		return joueur;
	}

	public void setJoueur(JoueurEntity joueur) {
		this.joueur = joueur;
	}

	public Evenement(String background, int butinArgent, JoueurEntity joueur) {
		super();
		this.background = background;
		this.butinArgent = butinArgent;
		this.etape = 1;
		this.joueur = joueur;
	}

	public Evenement() {
		super();
	}

	public Vue nextVue() {
		return null;
	}

	public void modifieCaracs() {
	}

}
