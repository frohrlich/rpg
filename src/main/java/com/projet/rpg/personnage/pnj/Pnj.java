package com.projet.rpg.personnage.pnj;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.rpg.personnage.Personnage;
import com.projet.rpg.personnage.pnj.allie.Allie;
import com.projet.rpg.personnage.pnj.ennemi.Ennemi;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pnj {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 8192, nullable=true)
	private String dialogue;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personnage_id", referencedColumnName = "id")
	private Personnage personnage;
	
	@JsonIgnore
	@OneToOne(mappedBy = "pnj")
    private Allie allie;
	
	@JsonIgnore
	@OneToOne(mappedBy = "pnj")
    private Ennemi ennemi;
	
}
