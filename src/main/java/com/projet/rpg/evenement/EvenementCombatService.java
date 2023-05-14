package com.projet.rpg.evenement;

import org.springframework.stereotype.Service;

import com.projet.rpg.personnage.PersonnageService;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;
import com.projet.rpg.vue.Option;
import com.projet.rpg.vue.Vue;
import com.projet.rpg.vue.VueAvecPnj;
import com.projet.rpg.vue.VueAvecPnjService;

@Service
public class EvenementCombatService {

	private EvenementCombat evenementCombat;
	private VueAvecPnjService vueAvecPnjService;
	private PersonnageService personnageService;

	public EvenementCombatService(EvenementCombat evenementCombat, VueAvecPnjService vueAvecPnjService,
			PersonnageService personnageService) {
		this.evenementCombat = evenementCombat;
		this.vueAvecPnjService = vueAvecPnjService;
		this.personnageService = personnageService;
	}

	public void update(EvenementCombat evenementCombat) {
		this.evenementCombat = evenementCombat;
	}

	public Vue nextVue() {
		Pnj pnj = evenementCombat.getPnj();
		Joueur joueur = evenementCombat.getJoueur();

		// if fight just started : return welcome view
		if (evenementCombat.etape == 1) {
			String welcomeMessage = "Le combat contre " + pnj.getPersonnage().getNom() + " commence !";
			VueAvecPnj welcomeVue = new VueAvecPnj(evenementCombat.getBackground(), welcomeMessage, joueur, pnj);
			vueAvecPnjService.update(welcomeVue);
			vueAvecPnjService.addOption(new Option("Attaquer"));
			evenementCombat.etape++;
			return welcomeVue;
		}

		// at the beginning, check if someone is already dead.
		// if so, end the event immediately
		if (personnageService.isDead(pnj.getPersonnage()) || personnageService.isDead(joueur.getPersonnage())) {
			return null;
		}

		VueAvecPnj myVue = null;

		int infligeDegatJoueur;
		String texteDegatJoueur;
		int infligeDegatPnj;
		String texteDegatPnj;

		// check if player hit
		if (personnageService.reussitAttaque(joueur.getPersonnage(), pnj.getPersonnage())) {
			// calculates damage inflicted to pnj
			infligeDegatJoueur = personnageService.infligeDegat(joueur.getPersonnage(), pnj.getPersonnage());
			// inflicts damage to the pnj
			personnageService.blesse(pnj.getPersonnage(), infligeDegatJoueur);
			// sets text corresponding to the action
			texteDegatJoueur = joueur.getPersonnage().getNom() + " a infligé " + infligeDegatJoueur
					+ " points de dégâts.";
		} else {
			texteDegatJoueur = joueur.getPersonnage().getNom() + " a manqué sa cible !";
		}

		// same for pnj hitting player
		if (personnageService.reussitAttaque(pnj.getPersonnage(), joueur.getPersonnage())) {
			infligeDegatPnj = personnageService.infligeDegat(pnj.getPersonnage(), joueur.getPersonnage());
			personnageService.blesse(joueur.getPersonnage(), infligeDegatPnj);
			texteDegatPnj = pnj.getPersonnage().getNom() + " a infligé " + infligeDegatPnj + " points de dégâts.";
		} else {
			texteDegatPnj = pnj.getPersonnage().getNom() + " a manqué sa cible !";
		}

		String currentTexte;

		// check if pnj is dead
		if (personnageService.isDead(pnj.getPersonnage())) {
			currentTexte = "Vous avez gagné le combat !";
			// then check if player is dead. If both die at same turn, player wins :-)
		} else if (personnageService.isDead(joueur.getPersonnage())) {
			currentTexte = "Game Over";
			// else display normal message
		} else {
			currentTexte = texteDegatJoueur + " " + texteDegatPnj;
		}

		myVue = new VueAvecPnj(evenementCombat.getBackground(), currentTexte, joueur, pnj);

		vueAvecPnjService.update(myVue);
		vueAvecPnjService.addOption(new Option("Attaquer"));
		evenementCombat.etape++;

		return myVue;
	}
	
	public void update(Evenement evenement) {
		this.evenementCombat = (EvenementCombat) evenement;
	}

}
