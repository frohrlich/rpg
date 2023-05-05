package com.projet.rpg.personnage;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonnageService {

	private PersonnageRepository personnageRepository;

	public PersonnageService(PersonnageRepository personnageRepository) {
		this.personnageRepository = personnageRepository;
	}

	public List<Personnage> findAll() {
		return personnageRepository.findAll();
	}

	public Personnage findById(int id) {
		return personnageRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personnage non trouvé"));
	}

	public Personnage save(Personnage personnage) {
		return personnageRepository.save(personnage);
	}

	public void deleteById(int id) {
		personnageRepository.deleteById(id);
	}

	public void deleteAll() {
		personnageRepository.deleteAll();
	}

	public void update(Personnage personnage) {
		deleteById(personnage.getId());
		save(personnage);
	}

	// check if attack succeeded
	public boolean reussitAttaque(Personnage attaquant, Personnage cible) {

		boolean caTape = false;

		Random rand = new Random();
		double probaHit = Math.max(80 + attaquant.getAgilite() - cible.getAgilite(), 10);
		probaHit /= 100.;

		if (rand.nextDouble() < probaHit) {
			caTape = true;
		}
		return caTape;
	}

	// calculates damage inflicted
	public int infligeDegat(Personnage attaquant, Personnage cible) {

		int degats = 0;

		int attaque = attaquant.getForcePersonnage();
		int defense = cible.getDefense();
		
		if (attaque > defense) {
			degats = attaque * 2 - defense;
		} else {
			degats = attaque * attaque / defense;
		}

		return degats;
	}

	// check if a character is dead
	public boolean isDead(Personnage cible) {
		return cible.getPv() <= 0;
	}
	
	// injures character by degats pv
	public void blesse(Personnage cible, int degats) {
		cible.setPv(cible.getPv() - degats);
	}
}
