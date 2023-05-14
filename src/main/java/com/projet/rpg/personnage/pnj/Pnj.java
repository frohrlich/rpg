package com.projet.rpg.personnage.pnj;

import com.projet.rpg.personnage.Personnage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Pnj {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 8192, nullable=true)
	private String dialogue;
	
	private boolean isHostile;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personnage_id", referencedColumnName = "id")
	private Personnage personnage;

}
