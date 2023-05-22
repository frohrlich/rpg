package com.projet.rpg.lieu;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Carte {

	protected String[][] maCarte = new String[][] {};

	public Carte(String[][] maCarte) {
		super();
		this.maCarte = maCarte;
	}

	public void affiche() {
		for (int i = 0; i < maCarte.length; ++i) {
			System.out.println();
			for (int j = 0; j < maCarte[i].length; ++j) {
				System.out.print(maCarte[i][j] + " ");
			}
		}
	}

}