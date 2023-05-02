package com.projet.rpg.personnage;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersonnageService {
	
	private PersonnageRepository personnageRepository;
	
	public PersonnageService(PersonnageRepository personnageRepository) {
		this.personnageRepository = personnageRepository;
	}
	
	public List<Personnage> findAll() {
		return personnageRepository.findAll();
	}

	public Personnage findById(int id) {
		return personnageRepository
				.findById(id)
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
	
}
