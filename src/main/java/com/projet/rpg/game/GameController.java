package com.projet.rpg.game;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.rpg.vue.Vue;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {
	
	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping("/initialize")
	public Vue initialize() {
		return gameService.initialize();
	}
	
	@GetMapping("/update")
	public Vue update(@RequestParam String message) {
		return gameService.update(message);
	}

}
