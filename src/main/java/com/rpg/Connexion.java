package com.rpg;

import javax.persistence.EntityManagerFactory;

public class Connexion {

	private static Connexion instance = null;

    private final EntityManagerFactory emf = HibernateUtil.getSessionFactory();

    // constructeur
    private Connexion() {}

    public static Connexion getInstance() {
        // elaboration du singleton
        if(instance == null) {
            instance = new Connexion();
        }

        return instance;
    }

    // Méthode de déconnexion à la BDD
    
    public void deconnexion() {
        instance = null;
    }

    // Méthode retournant le driver
    
    public  EntityManagerFactory getEmf() {
        return emf;
    }
}