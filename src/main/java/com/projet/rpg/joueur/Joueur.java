package com.projet.rpg.joueur;

import org.springframework.data.relational.core.mapping.Table;

import com.projet.rpg.personnage.Personnage;
import com.projet.rpg.personnage.Role;
import com.projet.rpg.personnage.Sexe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("joueur")
public class Joueur extends Personnage {

	@Column
	int xp;
	
	@OneToOne
	private Personnage personnage;
	
	// A voir si ce constructeur est bien nécessaire	
	public Joueur(int inventaireId, String nom, Sexe sexe, Role role, int niveau, int pv,
			int pvMax, int forcePersonnage, int agilite, int defense, int argent,
			String apparence, int xp) {
		// TODO Auto-generated constructor stub
	}
}
