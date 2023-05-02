package com.rpg.thebest.evenement;

import com.rpg.thebest.personnage.*;
import com.rpg.thebest.personnage.entities.Joueur;
import com.rpg.thebest.personnage.entities.Pnj;
import com.rpg.thebest.personnage.entities.Role;
import com.rpg.thebest.personnage.entities.Sexe;
import com.rpg.thebest.vue.Vue;

public class Main {

	public static void main(String[] args) {

		Joueur felix = new Joueur(2, "Felix", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png", 1000);
		Pnj pnj = new Pnj(2, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");

		pnj.dialogueCreation(new String[] { "Coucou je serai ton rival", "prépare toi à m'affronter", "c'est parti!" },
				10, 0);

		Evenement myEvn = new EvenementDialogue("img/bg_foret.png", 200, felix, pnj);

		boolean end = false;
		while (!end) {
			Vue currentVue = myEvn.nextVue();
			if (currentVue == null) {
				end = true;
			}
			else {
				System.out.println(currentVue);
			}
		}

	}

}
