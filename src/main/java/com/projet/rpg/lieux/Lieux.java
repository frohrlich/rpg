package com.projet.rpg.lieux;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Lieux {

	protected String texteAccueil;
	protected String background;

	public Lieux(String texteAccueil, String background) {
		super();
		this.texteAccueil = texteAccueil;
		this.background = background;

	}

}