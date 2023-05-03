package com.projet.rpg.personnage.pnj.ennemi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnnemiService {
	
	private EnnemiRepository ennemiRepository;
	
	public EnnemiService(EnnemiRepository ennemiRepository) {
		this.ennemiRepository = ennemiRepository;
	}
	
	public List<Ennemi> findAll() {
		return ennemiRepository.findAll();
	}

	public Ennemi findById(int id) {
		return ennemiRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allié non trouvé"));
	}

	public Ennemi save(Ennemi ennemi) {
		return ennemiRepository.save(ennemi);
	}

	public void deleteById(int id) {
		ennemiRepository.deleteById(id);
	}
	
	public void deleteAll() {
		ennemiRepository.deleteAll();
	}

	public void update(Ennemi ennemi) {
		deleteById(ennemi.getId());
		save(ennemi);
	}

}
