package com.projet.rpg.personnage.pnj.ennemi;

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
@RequestMapping("/ennemis")
public class EnnemiController {
	
	private final EnnemiService ennemiService;
	
	public EnnemiController(EnnemiService ennemiService) {
		this.ennemiService = ennemiService;
	}
	
	@GetMapping
	public List<Ennemi> findAll() {
		return ennemiService.findAll();
	}
	
	@GetMapping("{id}")
	public Ennemi findById(@PathVariable int id) {
		return ennemiService.findById(id);
	}
	
	@PostMapping
	public void save(@RequestBody Ennemi ennemi) {
		ennemiService.save(ennemi);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		ennemiService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		ennemiService.deleteAll();
	}
	
	@PutMapping
	public void update(@RequestBody Ennemi ennemi) {
		ennemiService.update(ennemi);
	}

}
