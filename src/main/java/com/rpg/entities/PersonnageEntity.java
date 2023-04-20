package com.rpg.entities;

import javax.persistence.*;

@Table(name="personnage")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  abstract class PersonnageEntity {
	
	// Attributs
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="inventaire_id", nullable=false)
	private int inventaireId;
	
	@Column(name="dialogue_id", nullable=false)
	private int dialogueId;
	
	@Column(name="nom", length=255, nullable=false)
	private String nom;
	
	@Column(name="sexe", length=255, nullable=false)
	private Sexe sexe;
	
	@Column(name="role", length=255, nullable=false)
	private Role role;
	
	@Column(name="niveau", nullable=false)
	private int niveau;
	
	@Column(name="pv", nullable=false)
	private int pv;
	
	@Column(name="pvMax", nullable=false)
	private int pvMax;
	
	@Column(name="force_personnage", nullable=false)
	private int forcePersonnage;
	
	@Column(name="agilite", nullable=false)
	private int agilite;
	
	@Column(name="defense", nullable=false)
	private int defense;
	
	@Column(name="argent", nullable=false)
	private int argent;
	
	// Getters et Setters

	public int getId() {
		return id;
	}

	public int getInventaireId() {
		return inventaireId;
	}

	public void setInventaireId(int inventaireId) {
		this.inventaireId = inventaireId;
	}

	public int getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(int dialogueId) {
		this.dialogueId = dialogueId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}

	public int getForcePersonnage() {
		return forcePersonnage;
	}

	public void setForcePersonnage(int forcePersonnage) {
		this.forcePersonnage = forcePersonnage;
	}

	public int getAgilite() {
		return agilite;
	}

	public void setAgilite(int agilite) {
		this.agilite = agilite;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}
	
	// Constructeurs

	public PersonnageEntity() {}

	public PersonnageEntity(int inventaireId, int dialogueId, String nom, Sexe sexe, Role role, int niveau,
			int pv, int pvMax, int forcePersonnage, int agilite, int defense, int argent) {
		this.setInventaireId(inventaireId);
		this.setDialogueId(dialogueId);
		this.setNom(nom);
		this.setSexe(sexe);
		this.setRole(role);
		this.setNiveau(niveau);
		this.setPv(pv);
		this.setPvMax(pvMax);
		this.setForcePersonnage(forcePersonnage);
		this.setAgilite(agilite);
		this.setDefense(defense);
		this.setArgent(argent);
	}

	@Override
	public String toString() {
		return "PersonnageEntity [id=" + id 
				+ ", inventaireId=" + inventaireId 
				+ ", dialogueId=" + dialogueId 
				+ ", nom=" + nom 
				+ ", sexe=" + sexe 
				+ ", role=" + role 
				+ ", niveau=" + niveau 
				+ ", pv=" + pv 
				+ ", pvMax=" + pvMax
				+ ", forcePersonnage=" + forcePersonnage 
				+ ", agilite=" + agilite 
				+ ", defense=" + defense 
				+ ", argent=" + argent 
				+ "]";
	}
	
}
