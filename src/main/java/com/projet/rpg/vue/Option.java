package com.projet.rpg.vue;

/**
 * Une option contient une chaîne de caractère composée de différentes possibilités.
 */

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Option {
	
	private String texte;

}
