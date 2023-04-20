package com.rpg;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.rpg.entities.AllieEntity;
import com.rpg.entities.EnnemiEntity;
import com.rpg.entities.JoueurEntity;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				// Configuration de la connexion à la BDD
				Configuration configuration = new Configuration();
				
				// Définition des propriétés, qui remplaceront le persistence.xml
				Properties dbSettings = new Properties();
				dbSettings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/rpg");
				dbSettings.put(Environment.USER, "root");
				dbSettings.put(Environment.PASS, "");
				dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				dbSettings.put(Environment.SHOW_SQL,"true");
				
				// Configuration de Hbiernate
				configuration.setProperties(dbSettings);
				configuration.setProperty("hibernate.hbm2ddl.auto", "update");
				
				// Ajout des classes à Mapper
				configuration.addAnnotatedClass(EnnemiEntity.class);
				configuration.addAnnotatedClass(JoueurEntity.class);
				configuration.addAnnotatedClass(AllieEntity.class);
				
				// Création du ServiceRegistry
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties())
						.build();
				
				// Création de la sessionFactory
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
}
