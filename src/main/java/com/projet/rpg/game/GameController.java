package com.projet.rpg.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.projet.rpg.vue.VueService;

@RestController
@CrossOrigin
public class GameController {
	
	private final GameService gameService;
	private final VueService vueService;
	
	public GameController(GameService gameService, VueService vueService) {
		this.gameService = gameService;
		this.vueService = vueService;
	}

	@MessageMapping("/initialize")
	@SendTo("/topic/greetings")
	public String initialize() {
		vueService.update(gameService.initialize());
		return vueService.toJson();
	}
	
	@MessageMapping("/update")
	@SendTo("/topic/greetings")
	public String update(String message) {
		System.out.println(message);
		vueService.update(gameService.update(message));
		return vueService.toJson();
	}

}
