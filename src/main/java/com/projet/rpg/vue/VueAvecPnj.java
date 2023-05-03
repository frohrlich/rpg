package com.projet.rpg.vue;

import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VueAvecPnj extends Vue {
	
	protected Pnj pnj;

	public VueAvecPnj(String background, String texte, Joueur joueur, Pnj pnj) {
		super(background, texte, joueur);
		this.pnj = pnj;
	}

}
