package com.projet.Personnage.Entities;

public enum Classes {

	Epeiste,
	Archer,
	Magicien; 
	
	Classes() {}  
	
private String classe;

	
	private Classes(String classe) {
		this.classe = classe;
	}
	

	


	public String getclasse() {
		return classe;
	}

}