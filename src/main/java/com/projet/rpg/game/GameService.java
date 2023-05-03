package com.projet.rpg.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.rpg.evenement.EvenementDialogueService;
import com.projet.rpg.personnage.joueur.JoueurService;
import com.projet.rpg.personnage.pnj.PnjService;

@Service
public class GameService {
	
	private Game game;
	
	@Autowired
	private JoueurService joueurService;
	@Autowired
	private PnjService pnjService;
	@Autowired
	private EvenementDialogueService evenementDialogueService;
	
	
	
	public GameService(Game game, JoueurService joueurService, PnjService pnjService,
			EvenementDialogueService evenementDialogueService) {
		super();
		this.game = game;
		this.joueurService = joueurService;
		this.pnjService = pnjService;
		this.evenementDialogueService = evenementDialogueService;
	}

	public Game() {
		// Game initialization
//		Joueur felix = new Joueur(2, "Felix", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png", 1000);
//		this.setCurrentJoueur(felix);
//
//		Pnj pnj = new Pnj(2, "Lalaina", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
//
//		pnj.dialogueCreation(new String[] { "Coucou je serai ton rival", "prépare toi à m'affronter", "c'est parti!" },
//				10, 0);

		
//		currentEvenement = new EvenementDialogue("img/bg_foret.png", 200, felix, pnj);
//		currentVue = this.currentEvenement.nextVue();
		
		currentJoueur.findById(1);
		
	}
	
	public String update(String message) {
		switch (message) {
		case "option1":
			currentVue = currentEvenement.nextVue();
			break;
		default:
		}

		// returns current view info in json format
		return currentVue.toString();
	}

}
