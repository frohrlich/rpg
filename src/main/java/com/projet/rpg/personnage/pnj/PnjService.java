package com.projet.rpg.personnage.pnj;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projet.rpg.personnage.Personnage;


@Service
public class PnjService {
	
	private PnjRepository pnjRepository;
	
	public PnjService(PnjRepository pnjRepository) {
		this.pnjRepository = pnjRepository;
	}
	
	/**
	 * Récupération depuis la BDD, de la liste des tous les pnjs.
	 * @return
	 */
	public List<Pnj> findAll() {
		return pnjRepository.findAll();
	}
	
	/**
	 * Récupération depuis la BDD, d'un pnj à partir de son identifiant unique.
	 * @param id
	 * @return
	 */
	public Pnj findById(int id) {
		return pnjRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pnj non trouvé"));
	}
	
	/**
	 * Récupération depuis la BDD, du Pnj présent (ou non) à un certain lieu.
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public Pnj findByLieu(int positionX, int positionY) {
		return pnjRepository.findByLieu(positionX, positionY);
	}
	
	/**
	 * Création dans la BDD, d'un pnj.
	 * @param personnage
	 * @return
	 */
	public Pnj save(Pnj pnj) {
		return pnjRepository.save(pnj);
	}
	
	/**
	 * Suppression dans la BDD, d'un pnj à partir de son identifiant unique.
	 * @param id
	 */
	public void deleteById(int id) {
		pnjRepository.deleteById(id);
	}
	
	/**
	 * Suppression dans la BDD, de la totalité des pnjs.
	 */
	public void deleteAll() {
		pnjRepository.deleteAll();
	}
	
	/**
	 * Mise à jour dans la BDD, d'un pnj.
	 * Peut-être redondante avec la méthode save(), à discuter.
	 * @param pnj
	 */
	public void update(Pnj pnj) {
		deleteById(pnj.getId());
		save(pnj);
	}
	
	// Méthode qui prend la suite des textes, des options et des récompenses et
	// qui permet de créer le json du dialogue
	public static String dialogueCreation(String[] textes, int pv, int xp) {
		
		String contenu = "{";
		for (int i = 0; i < textes.length; i++) {
			contenu += "\"texte" + (i + 1) + "\":" + "\"" + textes[i] + "\",";
		}
		
		contenu += "\"pv" + "\":" + "\"" + pv + "\", " + "\"xp" + "\":" + "\"" + xp + "\"}";
		
		return contenu;
	}

}
