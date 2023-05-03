package com.projet.rpg.personnage.pnj.allie;

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
@RequestMapping("/allies")
public class AllieController {
	
	private final AllieService allieService;
	
	public AllieController(AllieService allieService) {
		this.allieService = allieService;
	}
	
	@GetMapping
	public List<Allie> findAll() {
		return allieService.findAll();
	}
	
	@GetMapping("{id}")
	public Allie findById(@PathVariable int id) {
		return allieService.findById(id);
	}
	
	@PostMapping
	public void save(@RequestBody Allie allie) {
		allieService.save(allie);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		allieService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		allieService.deleteAll();
	}
	
	@PutMapping
	public void update(@RequestBody Allie allie) {
		allieService.update(allie);
	}

}
