package com.rpg.thebest.personnage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rpg.thebest.personnage.entities.Personnage;
import com.rpg.thebest.personnage.repositories.PersonnageRepository;

public class PersonnageService {
	
	private PersonnageRepository personnageRepository;
	
	@Autowired
	public PersonnageService(PersonnageRepository personnageRepository) {
		this.personnageRepository = personnageRepository;
	}
	
	public List<Personnage> findAll() {
		return personnageRepository.findAll();
	}

	public Personnage findById(int id) {
		return personnageRepository.findById(id).orElse(null);
	}

	public Personnage save(Personnage personnage) {
		personnageRepository.save(personnage);
		return personnage;
	}

	public void delete(int id) {
		personnageRepository.deleteById(id);
	}

	public void update(Personnage personnage) {
		delete(personnage.getId());
		save(personnage);
	}
	

}
