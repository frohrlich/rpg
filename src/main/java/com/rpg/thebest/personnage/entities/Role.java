package com.rpg.thebest.personnage.entities;

public enum Role {

	Ep("Epeiste"),
	Ar("Archer"),
	Ma("Magicien"); 
	
	Role() {}  
	
	private String role;
	private Role(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

}
