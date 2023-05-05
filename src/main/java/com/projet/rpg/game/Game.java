package com.projet.rpg.game;

import org.springframework.stereotype.Component;

import com.projet.rpg.evenement.Evenement;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.vue.VueAvecPnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor 
public class Game {
	private Joueur currentJoueur;
	private Evenement currentEvenement;
	private VueAvecPnj currentVue;

}
