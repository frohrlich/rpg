package com.projet.rpg.allie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private final AllieService allieService;
	
	public AllieController(AllieService allieService) {
		this.allieService = allieService;
	}
	
	@PostMapping("/write")
	public void write(@RequestBody Allie allie) {
		allieService.save(allie);
	}
	
	@GetMapping("/read")
	public List<Allie> read() {
		return allieService.findAll();
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Allie allie) {
		allieService.update(allie);
	}
	
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable int id) {
		allieService.delete(id);
	}

}
