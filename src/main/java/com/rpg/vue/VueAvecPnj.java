package com.rpg.vue;

import java.util.List;

import com.rpg.entities.PersonnageEntity;

public class VueAvecPnj extends Vue {
	protected PersonnageEntity pnj;

	public VueAvecPnj() {
		super();
	}

	public VueAvecPnj(String background, String texte, List<Option> options, PersonnageEntity joueur,
			PersonnageEntity pnj) {
		super(background, texte, options, joueur);
		this.pnj = pnj;
	}

	public VueAvecPnj(String background, String texte, PersonnageEntity joueur, PersonnageEntity pnj) {
		super(background, texte, joueur);
		this.pnj = pnj;
	}

	public PersonnageEntity getPnj() {
		return pnj;
	}

	public void setPnj(PersonnageEntity pnj) {
		this.pnj = pnj;
	}

	@Override
	public void affiche() {
		System.out.println(
				"|" + this.getJoueur().getNom() + "|" + "----------------" + "|" + this.getPnj().getNom() + "|");
		System.out.println();
		System.out.println(this.getPnj().getNom() + " : " + this.getTexte());
		System.out.println();
		for (int i = 0; i < this.getOptions().size(); i++) {
			System.out.println(this.getOptions().get(i).getTexte() + " : Tapez " + (i + 1));
		}
	}

}
