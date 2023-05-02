package com.rpg.thebest.vue;

import com.rpg.thebest.personnage.entities.Allie;
import com.rpg.thebest.personnage.entities.Joueur;
import com.rpg.thebest.personnage.entities.Personnage;
import com.rpg.thebest.personnage.entities.Role;
import com.rpg.thebest.personnage.entities.Sexe;

public class Main {

	public static void main(String[] args) {
		Personnage felix = new Joueur(2, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png",
				1000);
		Personnage fermiere = new Allie(2, "Basma", Sexe.F, Role.Ar, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		Vue myVue = new VueDialogue("img/bg_foret", texteVue, felix, fermiere);
		myVue.addOption(new Option("Saluer l'inconnue"));
		myVue.addOption(new Option("Ramasser une branche et la frapper"));
		myVue.addOption(new Option("S'enfuir en courant"));
		myVue.affiche();
	}

}
