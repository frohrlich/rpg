package com.projet.rpg.personnage.pnj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PnjRepository extends JpaRepository<Pnj, Integer> {
	
	@Query("SELECT * FROM pnj pn INNER JOIN personnage pe ON pn.id = pe.id WHERE (pe.positionx = ?1 AND pe.positiony = ?2)")
	Pnj findByLieu(int positionX, int positionY);
	
}
