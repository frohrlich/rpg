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
	
	/**
	 * Récupération depuis la BDD, de la liste des tous les joueurs.
	 * @return
	 */
	public List<Joueur> findAll() {
		return joueurRepository.findAll();
	}
	
	/**
	 * Récupération depuis la BDD, d'un joueur à partir de son identifiant unique.
	 * @param id
	 * @return
	 */
	public Joueur findById(int id) {
		return joueurRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pnj non trouvé"));
	}
	
	/**
	 * Création dans la BDD, d'un joueur.
	 * @param joueur
	 * @return
	 */
	public Joueur save(Joueur joueur) {
		return joueurRepository.save(joueur);
	}
	
	/**
	 * Suppression dans la BDD, d'un joueur à partir de son identifiant unique.
	 * @param id
	 */
	public void deleteById(int id) {
		joueurRepository.deleteById(id);
	}
	
	/**
	 * Suppression dans la BDD, de la totalité des joueurs.
	 */
	public void deleteAll() {
		joueurRepository.deleteAll();
	}
	
	/**
	 * Mise à jour dans la BDD, d'un joueur.
	 * Peut-être redondante avec la méthode save(), à discuter.
	 * @param joueur
	 */
	public void update(Joueur joueur) {
		deleteById(joueur.getId());
		save(joueur);
	}
	
}
