package com.projet.rpg.game;

import java.util.ArrayList;
import java.util.List;

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
import com.projet.rpg.vue.Option;
import com.projet.rpg.vue.Vue;
import com.projet.rpg.vue.VueService;

@Service
public class GameService {

	private Game game;

	private JoueurService joueurService;
	private PnjService pnjService;
	private EvenementService evenementService;
	private VueService vueService;

	public GameService(Game game, JoueurService joueurService, PnjService pnjService,
			EvenementService evenementService, VueService vueService) {
		this.game = game;
		this.joueurService = joueurService;
		this.pnjService = pnjService;
		this.evenementService = evenementService;
		this.vueService = vueService;
	}

	/**
	 * Sert à initialiser le jeu
	 */
	public Vue initialize() {
		game.setEtape(0);
		
		joueurService.deleteAll();
		pnjService.deleteAll();

		// on crée les personnages à la main pour tester
		Personnage perJ = new Personnage(1, "Martin", Sexe.M, Role.Ep, 1, 100, 100, 12, 5, 5, 10, "img/epeisteM.png");
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

		// génération de la rencontre avec le PNJ
		// si le pnj est hostile, la rencontre sera Dialogue + Combat
		// s'il ne l'est pas, la rencontre sera seulement Dialogue
		game.setEvenements(generateMeeting(pnj));

		// on le donne au service
		evenementService.update(this.currentEvenement());

		// on renvoie la vue de bienvenue dans le jeu
		return welcomeVue();
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
		case "flecheE":
		case "flecheO":
		case "flecheN":
		case "flecheS":
			game.setCurrentVue(welcomeVue());
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
			evenementService.update(this.currentEvenement());
			return true;
		} else {
			return false;
		}
	}

	// returns current event from list of events
	public Evenement currentEvenement() {
		return game.getEvenements().get(game.getEtape());
	}

	// generates a meeting with a pnj
	public List<Evenement> generateMeeting(Pnj pnj) {
		List<Evenement> meeting = new ArrayList<Evenement>();

		// Instanciation d'un événement de type dialogue
		EvenementDialogue evenementDialogue = new EvenementDialogue("img/bg_foret.png", 0, game.getCurrentJoueur(),
				pnj);
		// Ajout de l'événement de type dialogue dans le jeu
		meeting.add(evenementDialogue);

		if (pnj.isHostile()) {
			// création de l'événement combat qui suivra le dialogue
			EvenementCombat evenementCombat = new EvenementCombat("img/bg_volcan.png", pnj.getPersonnage().getArgent(),
					game.getCurrentJoueur(), pnj);
			meeting.add(evenementCombat);
		}

		return meeting;
	}

	// Vue d'accueil dans le jeu
	public Vue welcomeVue() {
		String currentTexte = "Bienvenue dans notre super RPG !";
		Vue myVue = new Vue("img/bg_foret.png", currentTexte, game.getCurrentJoueur());
		vueService.update(myVue);
		vueService.addOption(new Option("Commencer l'aventure"));
		return myVue;
	}
}
