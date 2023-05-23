package com.projet.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.projet.rpg.evenement.Evenement;
import com.projet.rpg.evenement.EvenementCombat;
import com.projet.rpg.evenement.EvenementDialogue;
import com.projet.rpg.evenement.EvenementService;
import com.projet.rpg.lieu.Carte;
import com.projet.rpg.lieu.Lieu;
import com.projet.rpg.personnage.Personnage;
import com.projet.rpg.personnage.Role;
import com.projet.rpg.personnage.Sexe;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.joueur.JoueurService;
import com.projet.rpg.personnage.pnj.Pnj;
import com.projet.rpg.personnage.pnj.PnjService;
import com.projet.rpg.vue.Option;
import com.projet.rpg.vue.Vue;
import com.projet.rpg.vue.VueDeplacement;
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
	 * Vue d'accueil dans le jeu.
	 * @return
	 */
	public Vue welcomeVue() {
		String currentTexte = "Bienvenue dans notre super RPG !";
		Vue myVue = new Vue("img/bg_foret.png", currentTexte, game.getCurrentJoueur());
		vueService.update(myVue);
		vueService.addOption(new Option("Commencer l'aventure"));
		return myVue;
	}

	/**
	 * Initialisation du jeu.
	 */
	public Vue initialize() {
		
		String [][] maCarte = new String[][] {{"Foret", "Foret", "Montagne"},
											  {"Volcan", "Village", "Foret"},
											  {"Plage", "Plage", "Plage"}};
		
		game.setCarte(new Carte(maCarte));
		
		
		
		game.setEtape(0);
		
		// On supprime tous les joueurs et tous les pnjs, il ne faudra plus faire ça à terme .
		joueurService.deleteAll();
		pnjService.deleteAll();

		// Création des personnages à la main pour tester.
		Personnage perJ = new Personnage(1, "Martin", Sexe.M, Role.Ep, 1, 100, 100, 12, 5, 5, 10, "img/epeisteM.png", 1, 1);
		Joueur martin = new Joueur(1, 1000, perJ);
		Personnage perP = new Personnage(2, "Paysanne", Sexe.F, Role.Ep, 1, 100, 100, 10, 5, 5, 10, "img/paysanne.png", 1, 2);
		Pnj pnj = new Pnj(1, "", true, perP);

		joueurService.save(martin);

		// Initialisation du joueur dans le jeu.
		game.setCurrentJoueur(martin);
		
		updateCurrentLieu();

		// Création d'un dialogue pour le PNJ.
		String dialoguePnj = PnjService.dialogueCreation(
				new String[] { "Coucou je serai ta rivale !", "Prépare toi à m'affronter.", "C'est parti !" }, 10, 0
				);
		// Assignation du dialogue au PNJ.
		pnj.setDialogue(dialoguePnj);

		// Update du PNJ dans la BDD, maintenant avec son dialogue.
		pnjService.save(pnj);

		// Génération de l'événement de rencontre avec le PNJ :
		// Si le pnj est hostile, la rencontre sera un événement Dialogue suivi d'un Combat ;
		// S'il ne l'est pas, la rencontre sera seulement un événement de type Dialogue.
		game.setEvenements(generateMeeting(pnj));

		// L'événement est ensuite donné au service
		evenementService.update(this.currentEvenement());

		// La méthode d'initialisation renvoie la vue de bienvenue dans le jeu.
		return getVueDeplacementAvecOuSansPnj();
	}

	/**
	 * Fonction appelée par le client à chaque action pour mettre à jour le jeu
	 * @param message
	 * @return
	 */
	public Vue update(String message) {

		Vue nouvelleVue = null;

		// parse JSON received from client
		JSONObject obj = new JSONObject(message);
		String strClick = obj.getString("click");
		
		Joueur joueur = game.getCurrentJoueur();
		
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

		case "flecheN": // si on n'est ni dans un combat, ni dans un dialogue etc... On est en déplacement
			deplacementJoueur(joueur, 0, -1);
			nouvelleVue = getVueDeplacementAvecOuSansPnj();
			game.setCurrentVue(nouvelleVue);
			
			break;
		case "flecheS":
			deplacementJoueur(joueur, 0, 1);
			nouvelleVue = getVueDeplacementAvecOuSansPnj();
			game.setCurrentVue(nouvelleVue);
			
			break;
		case "flecheO":
			deplacementJoueur(joueur, -1, 0);
			nouvelleVue = getVueDeplacementAvecOuSansPnj();
			game.setCurrentVue(nouvelleVue);
			
			break;
		case "flecheE":
			deplacementJoueur(joueur, 1, 0);
			nouvelleVue = getVueDeplacementAvecOuSansPnj();
			game.setCurrentVue(nouvelleVue);

			break;
		default:
		}
	

		// returns current vue info
		return game.getCurrentVue();
	}

	/**
	 * Ajout d'un événement à la liste des événements.
	 * @param evenement
	 */
	public void addEvenement(Evenement evenement) {
		game.getEvenements().add(evenement);
	}

	/**
	 * Méthode qui dit au jeu de passer à l'événement suivant.
	 * Retourne faux s'il n'y a pas d'événement à venir.
	 * @return
	 */
	public boolean nextEvent() {
		game.setEtape(game.getEtape() + 1);
		// Vérification que la fin de la liste d'événements n'a pas été atteinte avant de passer à l'événement suivant.
		if (game.getEtape() < game.getEvenements().size()) {
			evenementService.update(this.currentEvenement());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Méthode retournant l'événement courrant de la liste d'événements.
	 * @return
	 */
	public Evenement currentEvenement() {
		return game.getEvenements().get(game.getEtape());
	}

	/**
	 * Méthode générant une rencontre avec un Pnj
	 * @param pnj
	 * @return
	 */
	public List<Evenement> generateMeeting(Pnj pnj) {
		updateCurrentLieu();

		List<Evenement> meeting = new ArrayList<Evenement>();
		
		String currentBackground = game.getCurrentLieu().getBackground();

		// Instanciation d'un événement de type dialogue
		EvenementDialogue evenementDialogue = new EvenementDialogue(currentBackground, 0, game.getCurrentJoueur(),
				pnj);
		// Ajout de l'événement de type dialogue dans le jeu
		meeting.add(evenementDialogue);
		
		// Dans le cas où le Pnj est hostile
		if (pnj.isHostile()) {
			// Création de l'événement combat qui suivra le dialogue
			EvenementCombat evenementCombat = new EvenementCombat(currentBackground, pnj.getPersonnage().getArgent(),
					game.getCurrentJoueur(), pnj);
			System.out.println(currentBackground);
			meeting.add(evenementCombat);
		}

		return meeting;
	}
	
	/**
	 * Méthode pour déplacer le joueur sur la carte
	 * @param joueur
	 * @param deltaX
	 * @param deltaY
	 */
	public void deplacementJoueur(Joueur joueur, int deltaX, int deltaY) {
		int currentX = joueur.getPersonnage().getPositionX();
		int currentY = joueur.getPersonnage().getPositionY();
		
		joueur.getPersonnage().setPositionX(currentX + deltaX);
		joueur.getPersonnage().setPositionY(currentY + deltaY);
		
		game.setCurrentJoueur(joueur);
	}
	
	/**
	 * Méthode pour récupérer le Pnj présent (ou non) à un lieu donné
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public Vue getVueDeplacementAvecOuSansPnj(){
		updateCurrentLieu();

		int positionX = game.getCurrentJoueur().getPersonnage().getPositionX();
		int positionY = game.getCurrentJoueur().getPersonnage().getPositionY();
		
		Pnj pnjPresent = pnjService.findByLieu(positionX, positionY);
		
		String newBackground = game.getCurrentLieu().getBackground();
		
		String welcomeNewLieu = game.getCurrentLieu().getTexteAccueil();
		
		if (pnjPresent != null) {
			Vue nouvelleVue = new VueDeplacement(newBackground, welcomeNewLieu, game.getCurrentJoueur(), pnjPresent, game.getCarte());
			Option option = new Option("Parler avec " + pnjPresent.getPersonnage().getNom());
			vueService.update(nouvelleVue);
			vueService.addOption(option);
			return nouvelleVue;
		} else {
			Vue nouvelleVue = new VueDeplacement(newBackground, welcomeNewLieu, game.getCurrentJoueur(), game.getCarte());
			return nouvelleVue;
		}
	}
	
	public void updateCurrentLieu() {
		int positionX = game.getCurrentJoueur().getPersonnage().getPositionX();
		int positionY = game.getCurrentJoueur().getPersonnage().getPositionY();
				
		String newStringLieu = game.getCarte().getMaCarte()[positionY][positionX];
		
		Lieu newLieu = new Lieu(newStringLieu);
		
		game.setCurrentLieu(newLieu);
	}
	
}
