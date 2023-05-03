package com.projet.rpg.personnage;

public enum Sexe  {
	M("Masculin"),
	F("Féminin");
	
	private String sexe;
	
	private Sexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getSexe() {
		return sexe;
	}

}
