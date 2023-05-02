package com.projet.rpg.allie;

import java.util.List;

public class AllieService {
	
	private AllieRepository allieRepository;
	
	public AllieService(AllieRepository allieRepository) {
		this.allieRepository = allieRepository;
	}
	
	public List<Allie> findAll() {
		return allieRepository.findAll();
	}

	public Allie findById(int id) {
		return allieRepository.findById(id).orElse(null);
	}

	public Allie save(Allie allie) {
		allieRepository.save(allie);
		return allie;
	}

	public void delete(int id) {
		allieRepository.deleteById(id);
	}

	public void update(Allie allie) {
		delete(allie.getId());
		save(allie);
	}

}
