package com.projet.rpg.evenement;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

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

	public Vue nextVue() {
		JSONObject obj = new JSONObject(evenementDialogue.getPnj().getDialogue());
		String textFieldName = "texte" + evenementDialogue.getEtape();
		VueAvecPnj myVue = null;

		if (!obj.isNull(textFieldName)) {
			String currentTexte = evenementDialogue.getPnj().getPersonnage().getNom() 
								+ " : " + obj.getString(textFieldName);
			myVue = new VueAvecPnj(evenementDialogue.getBackground(), currentTexte, evenementDialogue.getJoueur(),
					evenementDialogue.getPnj());
			vueAvecPnjService.update(myVue);
			vueAvecPnjService.addOption(new Option("Continuer"));
			evenementDialogue.etape++;
		}

		return myVue;
	}

	public void update(Evenement evenement) {
		this.evenementDialogue = (EvenementDialogue) evenement;
	}

}
