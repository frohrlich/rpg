package com.rpg;

import com.rpg.DAO.JoueurDAO;
import com.rpg.entities.JoueurEntity;
import com.rpg.entities.Role;
import com.rpg.entities.Sexe;

public class Main {

	public static void main(String[] args) {
		
//		Connexion conn = Connexion.getInstance();
//		
//		EnnemiEntity ennemi = new EnnemiEntity(2, 3, "Lalaina", "F", "fée", 150,
//				1280, 2280, 300, 300, 2000, 3000000);
//		
//		EnnemiDAO dao = new EnnemiDAO();
//		
//		dao.create(ennemi);
//		
//		System.out.println(dao.update(1, ennemi));

		Connexion conn = Connexion.getInstance();
		
		JoueurEntity joueur = new JoueurEntity(2, 3, "Lalaina", Sexe.F, Role.Ep , 150, 1280, 2280, 300, 300, 2000, 3000000,
				1000);
		
		JoueurDAO dao = new JoueurDAO();
		
		dao.create(joueur);
		
		//System.out.println(dao.update(2, joueur));
	}

}
