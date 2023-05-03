package com.projet.rpg.evenement;

import org.springframework.stereotype.Service;

@Service
public class EvenementAvecPnjService {
	
	private EvenementAvecPnj evenementAvecPnj;
	
	public EvenementAvecPnjService (EvenementAvecPnj evenementAvecPnj) {
		this.evenementAvecPnj=evenementAvecPnj;
	}

}
