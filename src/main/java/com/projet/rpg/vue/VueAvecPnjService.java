package com.projet.rpg.vue;

public class VueAvecPnjService {

	private VueAvecPnj vue;

	public VueAvecPnjService(VueAvecPnj vue) {
		this.vue = vue;
	}

	public void affiche() {
		System.out
				.println("|" + vue.getJoueur().getNom() + "|" + "----------------" + "|" + vue.getPnj().getNom() + "|");
		System.out.println();
		System.out.println(vue.getPnj().getNom() + " : " + vue.getTexte());
		System.out.println();
		for (int i = 0; i < vue.getOptions().size(); i++) {
			System.out.println(vue.getOptions().get(i).getTexte() + " : Tapez " + (i + 1));
		}
	}

	// toString sends infos in json format
	@Override
	public String toString() {
		String returnString = "{\"background\":\"" + vue.getBackground() + "\"," + "\"texte\":\"" + vue.getPnj().getNom() + " : " + vue.getTexte()
				+ "\",";
		for (int i = 0; i < vue.options.size(); i++) {
			returnString += "\"option" + (i + 1) + "\":" + "\"" + vue.options.get(i).getTexte() + "\",";
		}
		returnString += "\"joueur\":\"" + vue.getJoueur().getApparence() + "\"," + "\"pnj\":\"" + vue.getPnj().getApparence() + "\""
				+ "}";
		return returnString;
	}
}
