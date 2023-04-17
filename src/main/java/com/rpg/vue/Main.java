package com.rpg.vue;

public class Main {

	public static void main(String[] args) {
		Personnage felix = new Personnage("Félix");
		Personnage inconnu = new Personnage("Inconnu");
		String texteVue = "Bienvenue dans la forêt des sangliers, voyageur.";
		Vue myVue = new VueDialogue("img/bg_foret", texteVue, felix, inconnu);
		myVue.addOption(new Option("Saluer l'inconnu"));
		myVue.addOption(new Option("Ramasser une branche et le frapper"));
		myVue.addOption(new Option("S'enfuir en courant"));
		myVue.affiche();
	}

}
