package com.projet.rpg.evenement;
import com.projet.rpg.personnage.joueur.Joueur;
import com.projet.rpg.personnage.pnj.Pnj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EvenementDialogue extends EvenementAvecPnj {


	public EvenementDialogue(String background, int butinArgent, Joueur joueur, Pnj pnj) {
		super(background, butinArgent, joueur, pnj);
	}

	

//	public void modifieCaracs() {
//		JSONObject obj = new JSONObject(this.getPnj().getDialogue());
//		int pv=obj.getInt("pv");
//		
//		monDialogue  = this.Pnj.dialogue;
//		monJoueur.setPv(monJoueur.getPv() + pv);
//		monJoueur.setXp(monJoueur.getXp() + monDialogue.getXp());
//	}
}
