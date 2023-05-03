package com.projet.rpg.personnage.joueur;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class JoueurService {
	
	private JoueurRepository joueurRepository;
	
	public JoueurService(JoueurRepository joueurRepository) {
		this.joueurRepository = joueurRepository;
	}
	
	public List<Joueur> findAll() {
		return joueurRepository.findAll();
	}

	public Joueur findById(int id) {
		return joueurRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pnj non trouvé"));
	}

	public Joueur save(Joueur joueur) {
		return joueurRepository.save(joueur);
	}

	public void deleteById(int id) {
		joueurRepository.deleteById(id);
	}
	
	public void deleteAll() {
		joueurRepository.deleteAll();
	}

	public void update(Joueur joueur) {
		deleteById(joueur.getId());
		save(joueur);
	}
	
}
