package com.rpg.vue;

import java.util.ArrayList;
import java.util.List;

import com.rpg.entities.PersonnageEntity;

public class Vue {
	protected String background;
	protected String texte;
	protected List<Option> options;
	protected PersonnageEntity joueur;

	public Vue() {
	}

	// Constructor with option list
	public Vue(String background, String texte, List<Option> options, PersonnageEntity joueur) {
		this.background = background;
		this.texte = texte;
		this.options = options;
		this.joueur = joueur;
	}

	// Constructor without options
	public Vue(String background, String texte, PersonnageEntity joueur) {
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

	public PersonnageEntity getJoueur() {
		return joueur;
	}

	public void setJoueur(PersonnageEntity joueur) {
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

	// toString sends infos in json format
	@Override
	public String toString() {
		return "{\"background\":\"" + background + "\","
			   + "\"texte\":\"" + texte + "\","
			   + "\"option1\":\"" + options.get(0).getTexte() + "\","
			   + "\"option2\":\"" + options.get(1).getTexte() + "\","
			   + "\"option3\":\"" + options.get(2).getTexte() + "\","
			   + "\"joueur\":\"" + joueur.getApparence() + "\""
			   + "}";
	}

}
