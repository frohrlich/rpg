package com.projet.rpg.vue;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/vues")
public class VueController {
	private final VueService vueService;

	public VueController(VueService vueService) {
		this.vueService = vueService;
		
		
		public void addOption(Option option) {
			
		}
	}
}
