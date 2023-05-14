package com.projet.rpg.game;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.projet.rpg.evenement.Evenement;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.vue.Vue;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Game {
	private Joueur currentJoueur;
	private Evenement currentEvenement;
	private List<Evenement> evenements;
	private int etape;
	private Vue currentVue;
	
	public Game() {
		this.etape = 0;
		this.evenements = new ArrayList<Evenement>();
	}
}
