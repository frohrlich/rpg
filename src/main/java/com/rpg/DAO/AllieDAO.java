package com.rpg.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rpg.Connexion;
import com.rpg.entities.AllieEntity;



public class AllieDAO {
	
	public List<AllieEntity> findAll() {
		// Connexion à la BDD
		Connexion conn = Connexion.getInstance();
		
		// Récupération de l'entity manager
		EntityManager em = conn.getEmf().createEntityManager();
		
		// Ecriture de la requête pour interroger la BDD
		String query = "From AllieEntity";
		
		// Création d'un objet depuis cette requête pour qu'hibernate fasse le mapping
		Query q = em.createQuery(query);
		
		// Déconnexion
		conn.deconnexion();
		
		// Il faut retourner le résultat
		return q.getResultList();
	}
	
	public AllieEntity findById(int id) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		AllieEntity allie = em.find(AllieEntity.class, id);
		
		conn.deconnexion();
		
		return allie;
	}
	
	public AllieEntity create(AllieEntity allie) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		AllieEntity allieToReturn = null;
		
		try {
			// on démarre la transaction
			tx.begin();
			// faire persister un objet, enregistrer en bdd
			em.persist(allie);
			// on va commit la transation
			tx.commit();
			
			allieToReturn = allie;
		} catch (Exception e) { // en cas de soucis
			if (tx != null && tx.isActive()) { // si une transaction sort une erreur
				tx.rollback(); // marche arrière sur les actions précédentes
			}
			e.printStackTrace(); // affichage de toute la stack d'erreurs
		}
		return allieToReturn;
		
	}

	public AllieEntity update(int id, AllieEntity allie) {
		
		Connexion conn = Connexion.getInstance();
		
		EntityManager em = conn.getEmf().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		// Modification de l'objet
		
		try {
			tx.begin(); // j'annule les auto-commit
			
			// Récupération de l'objet à modifier
			AllieEntity allieToUpdate = this.findById(id);
			allieToUpdate.setInventaireId(allie.getInventaireId());
			allieToUpdate.setDialogueId(allie.getDialogueId());
			allieToUpdate.setNom(allie.getNom());
			allieToUpdate.setSexe(allie.getSexe());
			allieToUpdate.setRole(allie.getRole());
			allieToUpdate.setNiveau(allie.getNiveau());
			allieToUpdate.setPv(allie.getPv());
			allieToUpdate.setPvMax(allie.getPvMax());
			allieToUpdate.setForcePersonnage(allie.getForcePersonnage());
			allieToUpdate.setAgilite(allie.getAgilite());
			allieToUpdate.setDefense(allie.getDefense());
			allieToUpdate.setArgent(allie.getArgent());
			allieToUpdate.setApparence(allie.getApparence());
			
			// Persistance de l'objet
			AllieEntity updated = em.merge(allieToUpdate);
			
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
			
			AllieEntity allie = this.findById(id); // l'entité doit être récupérée pour pouvoir procéder à la suppression
			
			// méthode de suppression
			em.remove(em.contains(allie) ?allie : em.merge(allie)); // on met l'entité en argument et pas l'id
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
