package com.projet.rpg.game;

import com.projet.rpg.evenement.Evenement;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.vue.Vue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor 
public class Game {
	private Joueur currentJoueur;
	private Evenement currentEvenement;
	private Vue currentVue;

}
