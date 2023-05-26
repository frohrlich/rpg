package com.projet.rpg.personnage.pnj;

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
@RequestMapping("/pnjs")
public class PnjController {
	
	private final PnjService pnjService;
	
	public PnjController(PnjService pnjService) {
		this.pnjService = pnjService;
	}
	
	@GetMapping
	public List<Pnj> findAll() {
		return pnjService.findAll();
	}
	
	@GetMapping("{id}")
	public Pnj findById(@PathVariable int id) {
		return pnjService.findById(id);
	}
	
	@GetMapping("{positionX}, {positionY}")
	public Pnj findByLieu(@PathVariable int positionX, @PathVariable int positionY) {
		return pnjService.findByLieu(positionX, positionY);
	}
	
	@PostMapping
	public void save(@RequestBody Pnj pnj) {
		pnjService.save(pnj);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		pnjService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		pnjService.deleteAll();
	}
	
	@PutMapping
	public void update(@RequestBody Pnj pnj) {
		pnjService.update(pnj);
	}

}
