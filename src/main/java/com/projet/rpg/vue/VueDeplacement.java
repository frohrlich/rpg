package com.projet.rpg.vue;

import org.springframework.stereotype.Component;

import com.projet.rpg.lieu.Carte;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class VueDeplacement extends VueAvecPnj {

	protected Carte carte;

	public VueDeplacement(String background, String texte, Joueur joueur, Pnj pnj, Carte carte) {
		super(background, texte, joueur, pnj);
		this.carte = carte;
	}
	
	//constructeur sans Pnj
	
	public VueDeplacement(String background, String texte, Joueur joueur, Carte carte) {
		super(background, texte, joueur, null);
		this.carte = carte;
	}
	
//	public Vue nextVue() {
//		Vue myVue = new VueDeplacement();
//		
//		
//		return myVue;
//	}
}