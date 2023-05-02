package com.projet.rpg.personnage.joueur;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/joueurs")
public class JoueurController {
	
	private final JoueurService joueurService;
	
	public JoueurController(JoueurService joueurService) {
		this.joueurService = joueurService;
	}
	
	@GetMapping
	public List<Joueur> findAll() {
		return joueurService.findAll();
	}
	
	@GetMapping("{id}")
	public Joueur findById(@PathVariable int id) {
		return joueurService.findById(id);
	}
	
	@PostMapping
	public void save(@RequestBody Joueur joueur) {
		joueurService.save(joueur);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		joueurService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		joueurService.deleteAll();
	}
	
	@PutMapping
	public void update(@RequestBody Joueur joueur) {
		joueurService.update(joueur);
	}

}
