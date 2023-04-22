package com.rpg;

import com.rpg.DAO.AllieDAO;
import com.rpg.entities.AllieEntity;
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
		
		AllieEntity allie = new AllieEntity(2, 3, "Basma", Sexe.F, Role.Ar, 150, 1280, 2280, 300, 300, 2000, 3, "img/paysanne.png");
		
		AllieDAO dao = new AllieDAO();
		
		System.out.println(dao.create(allie));
	}

}
