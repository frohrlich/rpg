package com.projet.rpg.vue;

import org.springframework.stereotype.Service;

@Service
public class VueService {

	private Vue vue;

	public VueService(Vue vue) {
		this.vue = vue;
	}

	// Method to add options to a Vue
	public void addOption(Option option) {
		vue.getOptions().add(option);
	}

	// method to test in terminal
	public void affiche() {
		System.out.println("|" + vue.getJoueur().getPersonnage().getNom() + "|");
		System.out.println();
		System.out.println(vue.getTexte());
		System.out.println();
		for (int i = 0; i < vue.getOptions().size(); i++) {
			System.out.println(vue.getOptions().get(i).getTexte() + " : Tapez " + (i + 1));
		}
	}
	
	public void update(Vue vue) {
		this.vue = vue;
	}
}
