package com.rpg.vue;

import com.rpg.entities.AllieEntity;
import com.rpg.entities.JoueurEntity;
import com.rpg.entities.PersonnageEntity;
import com.rpg.entities.Role;
import com.rpg.entities.Sexe;

public class Main {

	public static void main(String[] args) {
		PersonnageEntity felix = new JoueurEntity(2, 3, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png",
				1000);
		PersonnageEntity fermiere = new AllieEntity(2, 3, "Basma", Sexe.F, Role.Ar, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		Vue myVue = new VueDialogue("img/bg_foret", texteVue, felix, fermiere);
		myVue.addOption(new Option("Saluer l'inconnue"));
		myVue.addOption(new Option("Ramasser une branche et la frapper"));
		myVue.addOption(new Option("S'enfuir en courant"));
		myVue.affiche();
	}

}
