package com.projet.rpg.vue;

import org.springframework.stereotype.Service;

@Service
public class VueAvecPnjService {

	private VueAvecPnj vueAvecPnj;

	public VueAvecPnjService(VueAvecPnj vueAvecPnj) {
		this.vueAvecPnj = vueAvecPnj;
	}

	public void affiche() {
		System.out
				.println("|" + vueAvecPnj.getJoueur().getPersonnage().getNom() + "|" + "----------------" + "|" + vueAvecPnj.getPnj().getPersonnage().getNom() + "|");
		System.out.println();
		System.out.println(vueAvecPnj.getPnj().getPersonnage().getNom() + " : " + vueAvecPnj.getTexte());
		System.out.println();
		for (int i = 0; i < vueAvecPnj.getOptions().size(); i++) {
			System.out.println(vueAvecPnj.getOptions().get(i).getTexte() + " : Tapez " + (i + 1));
		}
	}
	
	public void addOption(Option option) {
		vueAvecPnj.getOptions().add(option);
	}
	
	public void update(VueAvecPnj vueAvecPnj) {
		this.vueAvecPnj = vueAvecPnj;
	}

}