package com.projet.rpg.joueur;

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
	
	@PostMapping("/write")
	public void write(@RequestBody Joueur joueur) {
		joueurService.save(joueur);
	}
	
	@GetMapping("/read")
	public List<Joueur> read() {
		return joueurService.findAll();
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Joueur joueur) {
		joueurService.update(joueur);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable int id) {
		joueurService.delete(id);
	}

}
