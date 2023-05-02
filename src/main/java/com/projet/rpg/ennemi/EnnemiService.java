package com.projet.rpg.ennemi;

import java.util.List;

public class EnnemiService {
	
	private EnnemiRepository ennemiRepository;
	
	public EnnemiService(EnnemiRepository ennemiRepository) {
		this.ennemiRepository = ennemiRepository;
	}
	
	public List<Ennemi> findAll() {
		return ennemiRepository.findAll();
	}

	public Ennemi findById(int id) {
		return ennemiRepository.findById(id).orElse(null);
	}

	public Ennemi save(Ennemi ennemi) {
		ennemiRepository.save(ennemi);
		return ennemi;
	}

	public void delete(int id) {
		ennemiRepository.deleteById(id);
	}

	public void update(Ennemi ennemi) {
		delete(ennemi.getId());
		save(ennemi);
	}

}
