package com.projet.rpg.personnage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personnage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@OneToOne // Ce sera une one to many
//	private Inventaire inventaire;
	
	@Column(length=255, nullable=false)
	private String nom;
	
	@Column(length=255, nullable=false)
	private Sexe sexe;
	
	@Column(length=255, nullable=false)
	private Role role;
	
	@Column(nullable=false)
	private int niveau;
	
	@Column(nullable=false)
	private int pv;
	
	@Column(nullable=false)
	private int pvMax;
	
	@Column(nullable=false)
	private int forcePersonnage;
	
	@Column(nullable=false)
	private int agilite;
	
	@Column(nullable=false)
	private int defense;
	
	@Column(nullable=false)
	private int argent;
	
	@Column(nullable = false)
	private String apparence;
	
	@Column(nullable = false)
	private String positionX;
	
	@Column(nullable = false)
	private String poxitionY;
	
//	@JsonIgnore
//	@OneToOne(mappedBy = "personnage")
//    private Pnj pnj;

}
