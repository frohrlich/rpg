package com.projet.rpg.game;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.projet.rpg.evenement.Evenement;
import com.projet.rpg.evenement.EvenementCombat;
import com.projet.rpg.evenement.EvenementDialogue;
import com.projet.rpg.evenement.EvenementService;
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

	private JoueurService joueurService;
	private PnjService pnjService;
	private EvenementService evenementService;

	public GameService(Game game, JoueurService joueurService, PnjService pnjService,
			EvenementService evenementService) {
		this.game = game;
		this.joueurService = joueurService;
		this.pnjService = pnjService;
		this.evenementService = evenementService;
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
		Pnj pnj = new Pnj(1, "", true, perP);

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
		// Ajout de l'événement de type dialogue dans le jeu
		this.addEvenement(evenementDialogue);

		// création de l'événement combat qui suivra le dialogue
		EvenementCombat evenementCombat = new EvenementCombat("img/bg_volcan.png", 200, martin, pnj);
		this.addEvenement(evenementCombat);

		// on récupère ensuite le premier événement de la liste
		game.setCurrentEvenement(game.getEvenements().get(game.getEtape()));

		// on le donne au service
		evenementService.update(game.getCurrentEvenement());

		// Initialisation de la vue dans le jeu
		game.setCurrentVue(evenementService.nextVue());

		return game.getCurrentVue();
	}

	/**
	 * Fonction appelée par le client à chaque action pour mettre à jour le jeu
	 * 
	 * @param message
	 * @return
	 */
	public Vue update(String message) {

		Vue nouvelleVue = null;

		// parse JSON received from client
		JSONObject obj = new JSONObject(message);
		String strClick = obj.getString("click");

		// then act depending on option clicked
		switch (strClick) {
		case "option1":
			nouvelleVue = evenementService.nextVue();
			// if next vue not null : proceed
			if (nouvelleVue != null) {
				game.setCurrentVue(nouvelleVue);
			} else if (this.nextEvent()) {
				// if next vue is null : it means current event ended
				// we can go to the next event in the list
				nouvelleVue = evenementService.nextVue();
				game.setCurrentVue(nouvelleVue);
			}
			break;
		default:
		}

		// returns current vue info
		return game.getCurrentVue();
	}

	
	// add an evenement to the list
	public void addEvenement(Evenement evenement) {
		game.getEvenements().add(evenement);
	}

	// tell the game to go to the next event
	// return false if there is no next event
	public boolean nextEvent() {
		game.setEtape(game.getEtape() + 1);
		// verify that we have not reached the end of the event list
		if (game.getEtape() < game.getEvenements().size()) {
			evenementService.update(game.getEvenements().get(game.getEtape()));
			return true;
		} else {
			return false;
		}
	}

}
