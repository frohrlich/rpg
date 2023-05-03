package com.projet.rpg.evenement;

import org.springframework.stereotype.Service;

@Service
public class EvenementService {
	
	private Evenement evenement;
	
	public EvenementService(Evenement evenement) {
		this.evenement=evenement;
	}
	
	

}
