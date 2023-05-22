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
	
	/**
	 * Mise à jour de l'événement
	 * @param evenement
	 */
	public void update(Evenement evenement) {
		this.evenementCombat = (EvenementCombat) evenement;
	}
	
	/**
	 * Génération de la prochaine vue de l'événénement combat
	 * @return
	 */
	public Vue nextVue() {
		Pnj pnj = evenementCombat.getPnj();
		Joueur joueur = evenementCombat.getJoueur();
		
		/* Avanat que le combat ne commence, vérification qu'aucun des personnages ne soit déjà mort,
		 * sinon, le combat est immédiatement terminé
		*/
		if (personnageService.isDead(pnj.getPersonnage()) || personnageService.isDead(joueur.getPersonnage())) {
			return null;
		}
		
		VueAvecPnj myVue = null;

		// Si le combat vient de commencer, affichage d'un texte "d'introduction"
		if (evenementCombat.etape == 0) {
			String welcomeMessage = "Le combat contre " + pnj.getPersonnage().getNom() + " commence !";
			VueAvecPnj welcomeVue = new VueAvecPnj(evenementCombat.getBackground(), welcomeMessage, joueur, pnj);
			vueAvecPnjService.update(welcomeVue);
			vueAvecPnjService.addOption(new Option("Attaquer"));
			evenementCombat.etape++;
			return welcomeVue;
		}

		// Création des variables locales pour le combat
		int infligeDegatJoueur;
		String texteDegatJoueur;
		int infligeDegatPnj;
		String texteDegatPnj;

		// Si le personnage atteint le Pnj cible
		if (personnageService.reussitAttaque(joueur.getPersonnage(), pnj.getPersonnage())) {
			// Calcul des dégâts infligeables à la cible
			infligeDegatJoueur = personnageService.calculDegats(joueur.getPersonnage(), pnj.getPersonnage());
			// Application des dégâts à la cible
			personnageService.blesse(pnj.getPersonnage(), infligeDegatJoueur);
			// Préparation du texte correspondant à l'action
			texteDegatJoueur = joueur.getPersonnage().getNom() + " a infligé " + infligeDegatJoueur
					+ " points de dégâts.";
		} else { // Sinon préparation du texte d'échec de l'attaque
			texteDegatJoueur = joueur.getPersonnage().getNom() + " a manqué sa cible !";
		}

		// Idem, mais dans le cas où le Pnj attaque le Personnage
		if (personnageService.reussitAttaque(pnj.getPersonnage(), joueur.getPersonnage())) {
			infligeDegatPnj = personnageService.calculDegats(pnj.getPersonnage(), joueur.getPersonnage());
			personnageService.blesse(joueur.getPersonnage(), infligeDegatPnj);
			texteDegatPnj = pnj.getPersonnage().getNom() + " a infligé " + infligeDegatPnj + " points de dégâts.";
		} else {
			texteDegatPnj = pnj.getPersonnage().getNom() + " a manqué sa cible !";
		}

		String currentTexte;

		// Vérification que le Pnj n'est pas mort
		if (personnageService.isDead(pnj.getPersonnage())) {
			currentTexte = "Vous avez gagné le combat !";
			// Puis, vérification que le Joueur n'est pas mort. Si le Joueur et le Pnj meurent au même tour, alors le Joueur gagne.
		} else if (personnageService.isDead(joueur.getPersonnage())) {
			currentTexte = "Game Over";
			// Sinon, affichage des dégâts infligés
		} else {
			currentTexte = texteDegatJoueur + " " + texteDegatPnj;
		}

		myVue = new VueAvecPnj(evenementCombat.getBackground(), currentTexte, joueur, pnj);

		vueAvecPnjService.update(myVue);
		vueAvecPnjService.addOption(new Option("Attaquer"));

		return myVue;
	}

}
