package com.projet.rpg.personnage.pnj;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PnjService {
	
	private PnjRepository pnjRepository;
	
	public PnjService(PnjRepository pnjRepository) {
		this.pnjRepository = pnjRepository;
	}
	
	public List<Pnj> findAll() {
		return pnjRepository.findAll();
	}

	public Pnj findById(int id) {
		return pnjRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pnj non trouvé"));
	}

	public Pnj save(Pnj pnj) {
		return pnjRepository.save(pnj);
	}

	public void deleteById(int id) {
		pnjRepository.deleteById(id);
	}
	
	public void deleteAll() {
		pnjRepository.deleteAll();
	}

	public void update(Pnj pnj) {
		deleteById(pnj.getId());
		save(pnj);
	}
	
	// Méthode qui prendrait la suite des textes, des options et des récompenses et
	// qui permettrait de créer le contenu json
	public static String dialogueCreation(String[] textes, int pv, int xp) {
		
		String contenu = "{";
		for (int i = 0; i < textes.length; i++) {
			contenu += "\"texte" + (i + 1) + "\":" + "\"" + textes[i] + "\",";
		}
		
		contenu += "\"pv" + "\":" + "\"" + pv + "\", " + "\"xp" + "\":" + "\"" + xp + "\"}";
		
		return contenu;
	}

}
