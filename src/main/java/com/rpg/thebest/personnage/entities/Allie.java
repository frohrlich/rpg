package com.rpg.thebest.personnage.entities;

import org.springframework.data.relational.core.mapping.Table;

@Table("allie")
public class Allie extends Pnj {
	
	public Allie(int id, String name, Sexe sexe, Role role, int level, int strength, int dexterity, int intelligence, int wisdom, int charisma, int hitPoints, String imageUrl) {
	    super(id, name, sexe, role, level, strength, dexterity, intelligence, wisdom, charisma, hitPoints, imageUrl);
	}


}
