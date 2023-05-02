package com.rpg.thebest;

import com.rpg.thebest.evenement.Evenement;
import com.rpg.thebest.evenement.EvenementDialogue;
import com.rpg.thebest.personnage.entities.Joueur;
import com.rpg.thebest.personnage.entities.Personnage;
import com.rpg.thebest.personnage.entities.Pnj;
import com.rpg.thebest.personnage.entities.Role;
import com.rpg.thebest.personnage.entities.Sexe;
import com.rpg.thebest.vue.Vue;

public class Game {
	private Personnage currentJoueur;
	private Evenement currentEvenement;
	private Vue currentVue;

	public Game() {
		// Game initialization
		Joueur felix = new Joueur(2, "Felix", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png", 1000);
		this.setCurrentJoueur(felix);

		Pnj pnj = new Pnj(2, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");

		pnj.dialogueCreation(new String[] { "Coucou je serai ton rival", "prépare toi à m'affronter", "c'est parti!" },
				10, 0);

		currentEvenement = new EvenementDialogue("img/bg_foret.png", 200, felix, pnj);
		currentVue = this.currentEvenement.nextVue();
	}

	public Game(Personnage currentJoueur, Vue currentVue) {
		this.currentJoueur = currentJoueur;
		this.currentVue = currentVue;
	}

	public Personnage getCurrentJoueur() {
		return currentJoueur;
	}

	public void setCurrentJoueur(Personnage currentJoueur) {
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
		case "option1":
			Vue nextVue = currentEvenement.nextVue();
			if(nextVue != null) {
				currentVue = nextVue;
			}
			break;
		default:
		}

		// returns current view info in json format
		return currentVue.toString();
	}

}
