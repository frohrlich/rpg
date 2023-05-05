package com.projet.rpg.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.projet.rpg.vue.Vue;

@RestController
@CrossOrigin
public class GameController {
	
	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@MessageMapping("/initialize")
	@SendTo("/client/vue")
	public Vue initialize() {
		return gameService.initialize();
	}
	
	@MessageMapping("/update")
	@SendTo("/client/vue")
	public Vue update(String message) {
		return gameService.update(message);
	}

}
