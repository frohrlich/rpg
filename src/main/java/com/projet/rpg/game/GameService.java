package com.projet.rpg.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.rpg.evenement.EvenementDialogue;
import com.projet.rpg.evenement.EvenementDialogueService;
import com.projet.rpg.personnage.Personnage;
import com.projet.rpg.personnage.Role;
import com.projet.rpg.personnage.Sexe;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.joueur.JoueurService;
import com.projet.rpg.personnage.pnj.Pnj;
import com.projet.rpg.personnage.pnj.PnjService;
import com.projet.rpg.vue.Vue;

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
	
	
	/**
	 * Sert à initialiser le jeu
	 */
	public Vue initialize() {
		
		joueurService.deleteAll();
		pnjService.deleteAll();
		
		
		Personnage perJ = new Personnage(1,"Alex", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/epeisteM.png");
		Joueur Martin = new Joueur(1,1000,perJ);
		Personnage perP = new Personnage(2, "Alfred", Sexe.M, Role.Ep, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
	    Pnj pnj = new Pnj(1,"",perP);
	    
		joueurService.save(Martin);
		
		// Initialisation du joueur dans le jeu
		game.setCurrentJoueur(Martin);
		
		// Création d'un dialogue pour le PNJ
		String dialoguePnj = PnjService.dialogueCreation(new String[] { "Coucou je serai ton rival", "prépare toi à m'affronter", "c'est parti!" },
				10, 0);
		// Assignation du dialogue au PNJ
		pnj.setDialogue(dialoguePnj);
		// Update du PNJ dans la BDD, maintenant avec son dialogue
		pnjService.save(pnj);
		// Instanciation d'un événement de type dialogue
		EvenementDialogue evenementDialogue = new EvenementDialogue("img/bg_foret.png", 200, Martin, pnj);
		// On place l'événement dans un service
		evenementDialogueService.update(evenementDialogue);
		// Initialisation de l'événement de type dialogue dans le jeu
		game.setCurrentEvenement(evenementDialogue);
		// Initialisation de la vue dans le jeu
		game.setCurrentVue(evenementDialogueService.nextVue());
		return game.getCurrentVue();
	}
	
	/**
	 * C'est le coeur même du jeu
	 * @param message
	 * @return
	 */
	public Vue update(String message) {
		Vue nouvelleVue = null;
		switch (message) {
		case "option1":
			nouvelleVue = evenementDialogueService.nextVue();
			if(nouvelleVue != null) {
				game.setCurrentVue(nouvelleVue);
			}
			break;
		case "option2": // On transforme le personnage en ours
			nouvelleVue = evenementDialogueService.nextVue();
			if(nouvelleVue != null) {
				nouvelleVue.getJoueur().getPersonnage().setApparence("img/le_ours.png");
				game.setCurrentVue(nouvelleVue);
			}
			break;
		case "option3": // On transforme le background en volcan
			nouvelleVue = evenementDialogueService.nextVue();
			if(nouvelleVue != null) {
				nouvelleVue.setBackground("img/bg_volcan.png");
				game.setCurrentVue(nouvelleVue);
			}
			break;
		default:
		}

		// returns current view info in json format
		return game.getCurrentVue();
	}

}
