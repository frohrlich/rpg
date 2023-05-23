package com.projet.rpg.lieu;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Lieu {

	protected String texteAccueil;
	protected String background;

	public Lieu(String typeLieu) {
		switch (typeLieu) {
		case "Foret":
			this.texteAccueil = "Vous pénétrez dans la forêt interdite.";
			this.background = "img/bg_foret.png";
			break;
		case "Montagne":
			this.texteAccueil = "Vous décidez d'escalader la montagne.";
			this.background = "img/bg_montagne.png";
			break;
		case "Volcan":
			this.texteAccueil = "Vous allez voir un volcan en éruption, parce que vous n'avez rien de mieux à faire.";
			this.background = "img/bg_volcan.png";
			break;
		case "Village":
			this.texteAccueil = "Vous rentrez au village, c'est l'heure de l'apéro.";
			this.background = "img/bg_village.png";
			break;
		case "Plage":
			this.texteAccueil = "Vous arrivez sur une plage de sable fin.";
			this.background = "img/bg_plage.png";
			break;
		default:
			this.texteAccueil = "";
			this.background = "";
			break;
		}

	}

}