package com.projet.rpg.ennemi;

import org.springframework.data.relational.core.mapping.Table;

import com.projet.rpg.personnage.Role;
import com.projet.rpg.personnage.Sexe;
import com.projet.rpg.pnj.Pnj;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;

@Table("ennemi")
@AllArgsConstructor
public class Ennemi extends Pnj {
	
	@OneToOne
	private Pnj pnj;

	// A voir si ce constructeur est bien nécessaire
	public Ennemi(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv,
		int pvMax, int forcePersonnage, int agilite, int defense, int argent,
		String apparence) {
		// TODO Auto-generated constructor stub
	}

}
