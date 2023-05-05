package com.projet.rpg.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.projet.rpg.vue.VueAvecPnjService;

@RestController
@CrossOrigin
public class GameController {
	
	private final GameService gameService;
	private final VueAvecPnjService vueAvecPnjService;
	
	public GameController(GameService gameService, VueAvecPnjService vueAvecPnjService) {
		this.gameService = gameService;
		this.vueAvecPnjService = vueAvecPnjService;
	}

	@MessageMapping("/initialize")
	@SendTo("/client/vue")
	public String initialize() {
		vueAvecPnjService.update(gameService.initialize());
		return vueAvecPnjService.toJson();
	}
	
	@MessageMapping("/update")
	@SendTo("/client/vue")
	public String update(String message) {
		System.out.println(message);
		vueAvecPnjService.update(gameService.update(message));
		return vueAvecPnjService.toJson();
	}

}
