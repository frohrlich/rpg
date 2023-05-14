package com.projet.rpg.evenement;

import org.springframework.stereotype.Service;

import com.projet.rpg.vue.Vue;

@Service
public class EvenementService {
	private EvenementCombatService evenementCombatService;
	private EvenementDialogueService evenementDialogueService;
	private String currentState;

	public EvenementService(EvenementCombatService evenementCombatService,
			EvenementDialogueService evenementDialogueService) {
		this.evenementCombatService = evenementCombatService;
		this.evenementDialogueService = evenementDialogueService;
		this.currentState = "";
	}

	public Vue nextVue() {
		Vue returnedVue = null;
		if (currentState.equals("combat")) {
			returnedVue = evenementCombatService.nextVue();
		} else if (currentState.equals("dialogue")) {
			returnedVue = evenementDialogueService.nextVue();
		}
		return returnedVue;
	}

	public void update(Evenement evenement) {
		if (evenement instanceof EvenementCombat) {
			evenementCombatService.update(evenement);
			currentState = "combat";
		} else if (evenement instanceof EvenementDialogue) {
			evenementDialogueService.update(evenement);
			currentState = "dialogue";
		}
	}
}
