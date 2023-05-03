package com.projet.rpg.vue;

import com.projet.rpg.personnage.Personnage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VueAvecPnj extends Vue {
	
	protected Personnage pnj;

	public VueAvecPnj(String background, String texte, Personnage joueur, Personnage pnj) {
		super(background, texte, joueur);
		this.pnj = pnj;
	}

}
