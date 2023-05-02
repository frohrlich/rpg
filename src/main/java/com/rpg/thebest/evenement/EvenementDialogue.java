package com.rpg.thebest.evenement;

import org.json.JSONObject;

import com.rpg.thebest.personnage.entities.Joueur;
import com.rpg.thebest.personnage.entities.Pnj;
import com.rpg.thebest.vue.Option;
import com.rpg.thebest.vue.Vue;
import com.rpg.thebest.vue.VueDialogue;

public class EvenementDialogue extends EvenementAvecPnj {

	public EvenementDialogue() {
	}

	public EvenementDialogue(String background, int butinArgent, Joueur joueur, Pnj pnj) {
		super(background, butinArgent, joueur, pnj);
	}

	@Override
	public Vue nextVue() {
		JSONObject obj = new JSONObject(this.getPnj().getDialogue());
		String textFieldName = "texte" + this.etape;
		Vue myVue = null;

		if (!obj.isNull(textFieldName)) {
			String currentTexte = obj.getString(textFieldName);
			myVue = new VueDialogue(this.background, currentTexte, this.getJoueur(), this.getPnj());
			myVue.addOption(new Option("Continuer"));
			this.etape++;
		}

		return myVue;
	}

//	public void modifieCaracs() {
//		JSONObject obj = new JSONObject(this.getPnj().getDialogue());
//		int pv=obj.getInt("pv");
//		
//		monDialogue  = this.Pnj.dialogue;
//		monJoueur.setPv(monJoueur.getPv() + pv);
//		monJoueur.setXp(monJoueur.getXp() + monDialogue.getXp());
//	}
}
