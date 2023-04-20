package com.projet.Personnage.Entities;

public enum Civilité {
	M("Monsieur"),
	F("Madame");
	
	private String civilité;
	
	private Civilité(String civilité) {
		this.civilité = civilité;
	}
	
	public String getCivilité() {
		return civilité;
	}

}
