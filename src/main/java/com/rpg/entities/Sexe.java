package com.rpg.entities;

public enum Sexe  {
	M("Masculin"),
	F("Feminin");
	
	private String sexe;
	
	private Sexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getSexe() {
		return sexe;
	}

}
