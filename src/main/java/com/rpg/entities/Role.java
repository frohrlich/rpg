package com.rpg.entities;

public enum Role {

	Epeiste,
	Archer,
	Magicien; 
	
	Role() {}  
	
	private String role;
	private Role(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

}
