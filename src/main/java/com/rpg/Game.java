package com.rpg;

import com.rpg.entities.JoueurEntity;
import com.rpg.entities.PersonnageEntity;
import com.rpg.entities.PnjEntity;
import com.rpg.entities.Role;
import com.rpg.entities.Sexe;
import com.rpg.evenement.Evenement;
import com.rpg.evenement.EvenementDialogue;
import com.rpg.vue.Vue;

public class Game {
	private PersonnageEntity currentJoueur;
	private Evenement currentEvenement;
	private Vue currentVue;

	public Game() {
		// Game initialization
		JoueurEntity felix = new JoueurEntity(2, "Felix", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png", 1000);
		this.setCurrentJoueur(felix);

		PnjEntity pnj = new PnjEntity(2, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");

		pnj.dialogueCreation(new String[] { "Coucou je serai ton rival", "prépare toi à m'affronter", "c'est parti!" },
				10, 0);

		currentEvenement = new EvenementDialogue("img/bg_foret.png", 200, felix, pnj);
		currentVue = this.currentEvenement.nextVue();
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
		case "option1":
			currentVue = currentEvenement.nextVue();
			break;
		default:
		}

		// returns current view info in json format
		return currentVue.toString();
	}

}
