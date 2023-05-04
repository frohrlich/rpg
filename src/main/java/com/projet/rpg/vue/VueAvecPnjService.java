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

	// toString sends infos in json format
	@Override
	public String toString() {
		String returnString = "{\"background\":\"" + vueAvecPnj.getBackground() + "\"," + "\"texte\":\"" + vueAvecPnj.getPnj().getPersonnage().getNom() + " : " + vueAvecPnj.getTexte()
				+ "\",";
		for (int i = 0; i < vueAvecPnj.options.size(); i++) {
			returnString += "\"option" + (i + 1) + "\":" + "\"" + vueAvecPnj.options.get(i).getTexte() + "\",";
		}
		returnString += "\"joueur\":\"" + vueAvecPnj.getJoueur().getPersonnage().getApparence() + "\"," + "\"pnj\":\"" + vueAvecPnj.getPnj().getPersonnage().getApparence() + "\""
				+ "}";
		return returnString;
	}
	
	public void addOption(Option option) {
		vueAvecPnj.getOptions().add(option);
	}
	
	public void update(VueAvecPnj vueAvecPnj) {
		this.vueAvecPnj = vueAvecPnj;
	}
}
