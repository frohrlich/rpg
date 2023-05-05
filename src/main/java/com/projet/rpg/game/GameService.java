package com.projet.rpg.game;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.rpg.evenement.EvenementCombat;
import com.projet.rpg.evenement.EvenementCombatService;
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
	@Autowired
	private EvenementCombatService evenementCombatService;

	public GameService(Game game, JoueurService joueurService, PnjService pnjService,
			EvenementDialogueService evenementDialogueService, EvenementCombatService evenementCombatService) {
		super();
		this.game = game;
		this.joueurService = joueurService;
		this.pnjService = pnjService;
		this.evenementDialogueService = evenementDialogueService;
		this.evenementCombatService = evenementCombatService;
	}

	/**
	 * Sert à initialiser le jeu
	 */
	public Vue initialize() {

		joueurService.deleteAll();
		pnjService.deleteAll();

		// on crée les personnages à la main pour tester
		Personnage perJ = new Personnage(1, "Martin", Sexe.M, Role.Ep, 1, 100, 100, 15, 5, 5, 10, "img/epeisteM.png");
		Joueur martin = new Joueur(1, 1000, perJ);
		Personnage perP = new Personnage(2, "Paysanne", Sexe.F, Role.Ep, 1, 100, 100, 10, 5, 5, 10, "img/paysanne.png");
		Pnj pnj = new Pnj(1, "", perP);

		joueurService.save(martin);

		// Initialisation du joueur dans le jeu
		game.setCurrentJoueur(martin);

		// Création d'un dialogue pour le PNJ
		String dialoguePnj = PnjService.dialogueCreation(
				new String[] { "Coucou je serai ta rivale !", "Prépare toi à m'affronter.", "C'est parti !" }, 10, 0);
		// Assignation du dialogue au PNJ
		pnj.setDialogue(dialoguePnj);
		// Update du PNJ dans la BDD, maintenant avec son dialogue
		pnjService.save(pnj);
		// Instanciation d'un événement de type dialogue
		EvenementDialogue evenementDialogue = new EvenementDialogue("img/bg_foret.png", 200, martin, pnj);
		// On place l'événement dans un service
		evenementDialogueService.update(evenementDialogue);
		// Initialisation de l'événement de type dialogue dans le jeu
		game.setCurrentEvenement(evenementDialogue);
		// Initialisation de la vue dans le jeu
		game.setCurrentVue(evenementDialogueService.nextVue());

		// création de l'événement combat qui suivra le dialogue
		EvenementCombat evenementCombat = new EvenementCombat("img/bg_volcan.png", 200, martin, pnj);
		evenementCombatService.update(evenementCombat);
		game.setNextEvenement(evenementCombat);

		return game.getCurrentVue();
	}

	/**
	 * C'est le coeur même du jeu
	 * 
	 * @param message
	 * @return
	 */
	public Vue update(String message) {
		Vue nouvelleVue = null;

		JSONObject obj = new JSONObject(message);
		String strClick = obj.getString("click");

//		switch (strClick) {
//		case "option1":
//			nouvelleVue = evenementDialogueService.nextVue();
//			if (nouvelleVue != null) {
//				game.setCurrentVue(nouvelleVue);
//			}
//			break;
//		case "option2": // On transforme le personnage en ours
//			nouvelleVue = evenementDialogueService.nextVue();
//			if (nouvelleVue != null) {
//				nouvelleVue.getJoueur().getPersonnage().setApparence("img/le_ours.png");
//				game.setCurrentVue(nouvelleVue);
//			}
//			break;
//		case "option3": // On transforme le background en volcan
//			nouvelleVue = evenementDialogueService.nextVue();
//			if (nouvelleVue != null) {
//				nouvelleVue.setBackground("img/bg_volcan.png");
//				game.setCurrentVue(nouvelleVue);
//			}
//			break;
//		default:
//		}

		switch (strClick) {
		case "option1":
			// check which type of event is the current one
			if (game.getCurrentEvenement() instanceof EvenementCombat) {
				nouvelleVue = evenementCombatService.nextVue();
			} else if (game.getCurrentEvenement() instanceof EvenementDialogue) {
				nouvelleVue = evenementDialogueService.nextVue();
			}
			if (nouvelleVue != null) {
				game.setCurrentVue(nouvelleVue);
				// if current event ended : go to next event
			} else {
				game.setCurrentEvenement(game.getNextEvenement());
				game.setNextEvenement(null);
				if (game.getCurrentEvenement() != null) {
					// and immediately start it
					if (game.getCurrentEvenement() instanceof EvenementCombat) {
						nouvelleVue = evenementCombatService.nextVue();
					} else if (game.getCurrentEvenement() instanceof EvenementDialogue) {
						nouvelleVue = evenementDialogueService.nextVue();
					}

					game.setCurrentVue(nouvelleVue);
				}
			}
			break;
		default:
		}

		// returns current view info in json format
		return game.getCurrentVue();
	}

}
