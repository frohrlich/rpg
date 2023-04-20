package com.rpg.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rpg.Connexion;
import com.rpg.entities.JoueurEntity;

// On va faire le CRUD (create, read, update, delete)

public class JoueurDAO {

	public List<JoueurEntity> findAll() {
		// Connexion à la BDD
		Connexion conn = Connexion.getInstance();

		// Récupération de l'entity manager
		EntityManager em = conn.getEmf().createEntityManager();

		// Ecriture de la requête pour interroger la BDD
		String query = "From JoueurEntity";

		// Création d'un objet depuis cette requête pour qu'hibernate fasse le mapping
		Query q = em.createQuery(query);

		// Déconnexion
		conn.deconnexion();
		
		// Il faut retourner le résultat
		return q.getResultList();
	}
	
	public JoueurEntity findById(int id) {

		Connexion conn = Connexion.getInstance();

		EntityManager em = conn.getEmf().createEntityManager();

		JoueurEntity joueur = em.find(JoueurEntity.class, id);

		conn.deconnexion();

		return joueur;
	}
	
	public void create(JoueurEntity joueur) {

		Connexion conn = Connexion.getInstance();

		EntityManager em = conn.getEmf().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			// on démarre la transaction
			tx.begin();
			// faire persister un objet, enregistrer en bdd
			em.persist(joueur);
			// on va commit la transation
			tx.commit();
		} catch (Exception e) { // en cas de soucis
			if (tx != null && tx.isActive()) { // si une transaction sort une erreur
				tx.rollback(); // marche arrière sur les actions précédentes
			}
			e.printStackTrace(); // affichage de toute la stack d'erreurs
		}

	}

	public JoueurEntity update(int id, JoueurEntity joueur) {

		Connexion conn = Connexion.getInstance();

		EntityManager em = conn.getEmf().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		// Modification de l'objet

		try {
			tx.begin(); // j'annule les auto-commit

			// Récupération de l'objet à modifier
			JoueurEntity joueurToUpdate = this.findById(id);
			joueurToUpdate.setInventaireId(joueur.getInventaireId());
			joueurToUpdate.setDialogueId(joueur.getDialogueId());
			joueurToUpdate.setNom(joueur.getNom());
			joueurToUpdate.setSexe(joueur.getSexe());
			joueurToUpdate.setRole(joueur.getRole());
			joueurToUpdate.setNiveau(joueur.getNiveau());
			joueurToUpdate.setPv(joueur.getPv());
			joueurToUpdate.setPvMax(joueur.getPvMax());
			joueurToUpdate.setForcePersonnage(joueur.getForcePersonnage());
			joueurToUpdate.setAgilite(joueur.getAgilite());
			joueurToUpdate.setDefense(joueur.getDefense());
			joueurToUpdate.setArgent(joueur.getArgent());
			joueurToUpdate.setXp(joueur.getXp());

			// Persistance de l'objet
			JoueurEntity updated = em.merge(joueurToUpdate);

			// Envoi sur le serveur
			tx.commit();

			conn.deconnexion();

			return updated;

		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		conn.deconnexion();

		return null;
	}

	
	public void delete(int id) {

		// pour protéger l'api, on n'est pas censé donner d'infos sur l'exécution de la
		// suppression

		Connexion conn = Connexion.getInstance();

		EntityManager em = conn.getEmf().createEntityManager();

		EntityTransaction tx = em.getTransaction();

		// suppression de l'objet

		try {
			tx.begin();

			JoueurEntity joueur = this.findById(id); // l'entité doit être récupérée pour pouvoir procéder à la
														// suppression

			// méthode de suppression
			em.remove(em.contains(joueur) ? joueur : em.merge(joueur)); // on met l'entité en argument et pas l'id
			// je fais un if else de manière raccourcie en une seule ligne
			tx.commit();

		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
		conn.deconnexion(); // comme il n'y a pas de return (void) on peut se déconnecter ici et pas dans le
							// try ; le code s'exécute de façon linéaire
	}

}
