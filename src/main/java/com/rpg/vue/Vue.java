package com.rpg.vue;

import java.util.ArrayList;
import java.util.List;

public class Vue {
	protected String background;
	protected String texte;
	protected List<Option> options;
	protected Personnage joueur;

	public Vue() {
	}

	// Constructor with option list
	public Vue(String background, String texte, List<Option> options, Personnage joueur) {
		this.background = background;
		this.texte = texte;
		this.options = options;
		this.joueur = joueur;
	}

	// Constructor without options
	public Vue(String background, String texte, Personnage joueur) {
		this.background = background;
		this.texte = texte;
		this.options = new ArrayList<Option>();
		this.joueur = joueur;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Personnage getJoueur() {
		return joueur;
	}

	public void setJoueur(Personnage joueur) {
		this.joueur = joueur;
	}

	public void addOption(Option option) {
		this.options.add(option);
	}

	public void affiche() {
		System.out.println("|" + this.getJoueur().getNom() + "|");
		System.out.println();
		System.out.println(this.getTexte());
		System.out.println();
		for (int i = 0; i < this.getOptions().size(); i++) {
			System.out.println(this.getOptions().get(i).getTexte() + " : Tapez " + (i + 1));
		}
	}

}
