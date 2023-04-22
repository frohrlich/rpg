package com.rpg;

import com.rpg.entities.AllieEntity;
import com.rpg.entities.JoueurEntity;
import com.rpg.entities.PersonnageEntity;
import com.rpg.entities.Role;
import com.rpg.entities.Sexe;
import com.rpg.vue.Option;
import com.rpg.vue.Vue;
import com.rpg.vue.VueDialogue;

public class Game {
	private PersonnageEntity currentJoueur;
	private Vue currentVue;

	public Game() {
		// Game initialization
		PersonnageEntity felix = new JoueurEntity(2, 3, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png", 1000);
		PersonnageEntity fermiere = new AllieEntity(2, 3, "Paysanne", Sexe.F, Role.Ar, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		Vue myVue = new VueDialogue("img/bg_foret.png", texteVue, felix, fermiere);
		myVue.addOption(new Option("Saluer l'inconnue bien bas"));
		myVue.addOption(new Option("Se changer en ours"));
		myVue.addOption(new Option("S'enfuir en courant"));

		this.setCurrentJoueur(felix);
		this.setCurrentVue(myVue);
	}

	public Game(PersonnageEntity currentJoueur, Vue currentVue) {
		this.currentJoueur = currentJoueur;
		this.currentVue = currentVue;
	}

	public PersonnageEntity getCurrentJoueur() {
		return currentJoueur;
	}

	public void setCurrentJoueur(PersonnageEntity currentJoueur) {
		this.currentJoueur = currentJoueur;
	}

	public Vue getCurrentVue() {
		return currentVue;
	}

	public void setCurrentVue(Vue currentVue) {
		this.currentVue = currentVue;
	}

	public String update(String message) {
		switch (message) {
		case "option1Clicked":
			this.getCurrentVue().setTexte("Comment allez-vous ?");
			this.getCurrentVue().getJoueur().setApparence("img/epeisteM.png");
			break;
		case "option2Clicked":
			this.getCurrentVue().setTexte("Un ours ! Damnation");
			this.getCurrentVue().getJoueur().setApparence("img/le_ours.png");
			break;
		case "option3Clicked":
			this.getCurrentVue().setTexte("Adios");
			this.getCurrentVue().getJoueur().setApparence("img/epeisteM.png");
			break;
		default:
		}

		// returns current view info in json format
		return this.getCurrentVue().toString();
	}

}
