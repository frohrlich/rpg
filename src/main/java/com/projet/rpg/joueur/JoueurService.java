package com.projet.rpg.joueur;

import java.util.List;

public class JoueurService {
	
	private JoueurRepository joueurRepository;
	
	public JoueurService(JoueurRepository joueurRepository) {
		this.joueurRepository = joueurRepository;
	}
	
	public List<Joueur> findAll() {
		return joueurRepository.findAll();
	}

	public Joueur findById(int id) {
		return joueurRepository.findById(id).orElse(null);
	}

	public Joueur save(Joueur joueur) {
		joueurRepository.save(joueur);
		return joueur;
	}

	public void delete(int id) {
		joueurRepository.deleteById(id);
	}

	public void update(Joueur joueur) {
		delete(joueur.getId());
		save(joueur);
	}
	
}
