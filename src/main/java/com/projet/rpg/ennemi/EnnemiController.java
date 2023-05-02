package com.projet.rpg.ennemi;

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
	
	@PostMapping("/write")
	public void write(@RequestBody Ennemi ennemi) {
		ennemiService.save(ennemi);
	}
	
	@GetMapping("/read")
	public List<Ennemi> read() {
		return ennemiService.findAll();
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Ennemi ennemi) {
		ennemiService.update(ennemi);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable int id) {
		ennemiService.delete(id);
	}

}
