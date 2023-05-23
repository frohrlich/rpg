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
	
	
	/**
	 * Récupération depuis la BDD, de la liste des tous les personnages.
	 * @return
	 */
	public List<Personnage> findAll() {
		return personnageRepository.findAll();
	}
	
	/**
	 * Récupération depuis la BDD, d'un personnage à partir de son identifiant unique.
	 * @param id
	 * @return
	 */
	public Personnage findById(int id) {
		return personnageRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personnage non trouvé"));
	}
	
	/**
	 * Création dans la BDD, d'un personnage.
	 * @param personnage
	 * @return
	 */
	public Personnage save(Personnage personnage) {
		return personnageRepository.save(personnage);
	}

	/**
	 * Suppression dans la BDD, d'un personnage à partir de son identifiant unique.
	 * @param id
	 */
	public void deleteById(int id) {
		personnageRepository.deleteById(id);
	}

	/**
	 * Suppression dans la BDD, de la totalité des personnages.
	 */
	public void deleteAll() {
		personnageRepository.deleteAll();
	}

	/**
	 * Mise à jour dans la BDD, d'un personnage.
	 * Peut-être redondante avec la méthode save(), à discuter.
	 * @param personnage
	 */
	public void update(Personnage personnage) {
		findById(personnage.getId());
		save(personnage);
	}
	

	/**
	 * Etablissement du succès ou de l'échec de l'attaque réalisée par un personnage contre sa cible.
	 * Pour cela, comparaison d'un nombre aléatoire compris entre 0 et 1 à un coefficient de succès.
	 * Le coefficient de succès est calculé en fonction de l'agilité du personnage et de celle de sa cible.
	 * Si le nombre aléatoire est plus petit que que le coefficient de succès, alors l'attaque réussit, sinon elle échoue.
	 * @param attaquant
	 * @param cible
	 * @return
	 */
	public boolean reussitAttaque(Personnage attaquant, Personnage cible) {

		boolean succes = false;

		Random random = new Random();
		
		int chanceBase = 80;
		
		double coefficientDeSucces = Math.max(chanceBase + attaquant.getAgilite() - cible.getAgilite(), 10);
		coefficientDeSucces /= 100.;

		if (random.nextDouble() < coefficientDeSucces) {
			succes = true;
		}
		return succes;
	}

	/**
	 * Calcul des dégâts infligeables par un personnage en position d'attaque contre sa cible.
	 * @param attaquant
	 * @param cible
	 * @return
	 */
	public int calculDegats(Personnage attaquant, Personnage cible) {

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
	
	/**
	 * Inflige des dégâts à un personnage cible.
	 * @param cible
	 * @param degats
	 */
	public void blesse(Personnage cible, int degats) {
		cible.setPv(cible.getPv() - degats);
		this.save(cible);
	}
	
	/**
	 * Vérifie si un personnage est mort (c'est-à-dire que ses points de vie sont tombés à 0)
	 * @param cible
	 * @return
	 */
		public boolean isDead(Personnage cible) {
			return cible.getPv() <= 0;
	}
}
