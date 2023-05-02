package com.projet.rpg.personnage.pnj.allie;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AllieService {
	
	private AllieRepository allieRepository;
	
	public AllieService(AllieRepository allieRepository) {
		this.allieRepository = allieRepository;
	}
	
	public List<Allie> findAll() {
		return allieRepository.findAll();
	}

	public Allie findById(int id) {
		return allieRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allié non trouvé"));
	}

	public Allie save(Allie allie) {
		return allieRepository.save(allie);
	}

	public void deleteById(int id) {
		allieRepository.deleteById(id);
	}
	
	public void deleteAll() {
		allieRepository.deleteAll();
	}

	public void update(Allie allie) {
		deleteById(allie.getId());
		save(allie);
	}

}
