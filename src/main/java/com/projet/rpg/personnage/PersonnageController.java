package com.projet.rpg.personnage;

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
@RequestMapping("/personnages")
public class PersonnageController {
	
	private final PersonnageService personnageService;
	
	public PersonnageController(PersonnageService personnageService) {
		this.personnageService = personnageService;
	}
	
	@GetMapping
	public List<Personnage> findAll() {
		return personnageService.findAll();
	}
	
	@GetMapping("{id}")
	public Personnage findById(@PathVariable int id) {
		return personnageService.findById(id);
	}
	
	@PostMapping
	public void save(@RequestBody Personnage personnage) {
		personnageService.save(personnage);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		personnageService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		personnageService.deleteAll();
	}
	
	@PutMapping
	public void update(@RequestBody Personnage personnage) {
		personnageService.update(personnage);
	}

}
