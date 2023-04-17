package com.rpg.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.rpg.Connexion;
import com.rpg.entities.EnnemiEntity;

import javax.persistence.Query;

// On va faire le CRUD (create, read, update, delete)

public class EnnemiDAO {
	
	public List<EnnemiEntity> findAll() {
		// Connexion à la BDD
		Connexion conn = Connexion.getInstance();
		
		// Récupération de l'entity manager
		EntityManager em = conn.getEmf().createEntityManager();
		
		// Ecriture de la requête pour interroger la BDD
		String query = "From EnnemiEntity";
		
		// Création d'un objet depuis cette requête pour qu'hibernate fasse le mapping
		Query q = em.createQuery(query);
		
		// Déconnexion
		conn.deconnexion();
		
		// Il faut retourner le résultat
		return q.getResultList();
	}
	
	public EnnemiEntity findById(int id) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EnnemiEntity ennemi = em.find(EnnemiEntity.class, id);
		
		conn.deconnexion();
		
		return ennemi;
	}
	
	public void create(EnnemiEntity ennemi) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			// on démarre la transaction
			tx.begin();
			// faire persister un objet, enregistrer en bdd
			em.persist(ennemi);
			// on va commit la transation
			tx.commit();
		} catch (Exception e) { // en cas de soucis
			if (tx != null && tx.isActive()) { // si une transaction sort une erreur
				tx.rollback(); // marche arrière sur les actions précédentes
			}
			e.printStackTrace(); // affichage de toute la stack d'erreurs
		}
		
	}

	public EnnemiEntity update(int id, EnnemiEntity ennemi) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		// Modification de l'objet
		
		try {
			tx.begin(); // j'annule les auto-commit
			
			// Récupération de l'objet à modifier
			EnnemiEntity ennemiToUpdate = this.findById(id);
			ennemiToUpdate.setInventaireId(ennemi.getInventaireId());
			ennemiToUpdate.setDialogueId(ennemi.getDialogueId());
			ennemiToUpdate.setNom(ennemi.getNom());
			ennemiToUpdate.setSexe(ennemi.getSexe());
			ennemiToUpdate.setClasse(ennemi.getClasse());
			ennemiToUpdate.setNiveau(ennemi.getNiveau());
			ennemiToUpdate.setPv(ennemi.getPv());
			ennemiToUpdate.setPvMax(ennemi.getPvMax());
			ennemiToUpdate.setForcePersonnage(ennemi.getForcePersonnage());
			ennemiToUpdate.setAgilite(ennemi.getAgilite());
			ennemiToUpdate.setDefense(ennemi.getDefense());
			ennemiToUpdate.setArgent(ennemi.getArgent());
			
			// Persistance de l'objet
			EnnemiEntity updated = em.merge(ennemiToUpdate);
			
			// Envoi sur le serveur
			tx.commit();
			
			conn.deconnexion();
			
			return updated;
			
		} catch(Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		conn.deconnexion();
		
		return null;
	}

	
	public void delete(int id) {
		
		// pour protéger l'api, on n'est pas censé donner d'infos sur l'exécution de la suppression
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		// suppression de l'objet
		
		try {
			tx.begin();
			
			EnnemiEntity ennemi = this.findById(id); // l'entité doit être récupérée pour pouvoir procéder à la suppression
			
			// méthode de suppression
			em.remove(em.contains(ennemi) ?ennemi : em.merge(ennemi)); // on met l'entité en argument et pas l'id
			// je fais un if else de manière raccourcie en une seule ligne
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
		conn.deconnexion(); // comme il n'y a pas de return (void) on peut se déconnecter ici et pas dans le try ; le code s'exécute de façon linéaire
	}
	
}
