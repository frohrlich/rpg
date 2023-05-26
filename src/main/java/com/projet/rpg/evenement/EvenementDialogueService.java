package com.projet.rpg.evenement;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;
import com.projet.rpg.vue.Option;
import com.projet.rpg.vue.Vue;
import com.projet.rpg.vue.VueAvecPnj;
import com.projet.rpg.vue.VueAvecPnjService;

@Service
public class EvenementDialogueService {

	private EvenementDialogue evenementDialogue;
	private VueAvecPnjService vueAvecPnjService;

	public EvenementDialogueService(EvenementDialogue evenementDialogue, VueAvecPnjService vueAvecPnjService) {
		this.evenementDialogue = evenementDialogue;
		this.vueAvecPnjService = vueAvecPnjService;
	}
	
	/**
	 * Mise à jour de l'événement dialogue.
	 * @param evenementCombat
	 */
	public void update(Evenement evenement) {
		this.evenementDialogue = (EvenementDialogue) evenement;
	}

	/**
	 * Génération de la prochaine vue de l'événénement dialogue.
	 * @return
	 */
	public Vue nextVue() {

		Pnj pnj = evenementDialogue.getPnj();
		Joueur joueur = evenementDialogue.getJoueur();
		
		// Si le dialogue vient de commencer, affichage d'un dialogue "d'introduction".
		if (evenementDialogue.etape == 0) {
			String welcomeMessage = pnj.getPersonnage().getNom() + " se trouve sur votre chemin.";
			VueAvecPnj welcomeVue = new VueAvecPnj(evenementDialogue.getBackground(), welcomeMessage, joueur, pnj);
			vueAvecPnjService.update(welcomeVue);
			vueAvecPnjService.addOption(new Option("Parler"));
			evenementDialogue.etape++;
			return welcomeVue;
		}
		
		JSONObject obj = new JSONObject(pnj.getDialogue());
		String textFieldName = "texte" + evenementDialogue.getEtape();
		VueAvecPnj myVue = null;
		
		// Autrement, poursuite du dialogue avec pour unique possibilité de continuer.
		if (!obj.isNull(textFieldName)) {
			String currentTexte = pnj.getPersonnage().getNom() 
								+ " : " + obj.getString(textFieldName);
			myVue = new VueAvecPnj(evenementDialogue.getBackground(), currentTexte, joueur,
					pnj);
			vueAvecPnjService.update(myVue);
			vueAvecPnjService.addOption(new Option("Continuer"));
			evenementDialogue.etape++;
		}

		return myVue;
	}
	
}
