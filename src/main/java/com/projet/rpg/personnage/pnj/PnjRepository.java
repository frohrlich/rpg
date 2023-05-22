package com.projet.rpg.personnage.pnj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PnjRepository extends JpaRepository<Pnj, Integer> {
	
	@Query(value="SELECT pe.id, "
			+ "dialogue, "
			+ "is_hostile, "
			+ "personnage_id, "
			+ "agilite, "
			+ "apparence, "
			+ "argent, "
			+ "defense, "
			+ "force_personnage, "
			+ "niveau, "
			+ "nom, "
			+ "positionx, "
			+ "positiony, "
			+ "pv, "
			+ "pv_max, "
			+ "role, "
			+ "sexe, "
			+ "pn.id as pnj_id "
			+ "FROM pnj pn INNER JOIN personnage pe ON pn.personnage_id = pe.id WHERE pe.positionx = ?1 AND pe.positiony = ?2", nativeQuery = true)
	Pnj findByLieu(int positionX, int positionY);
	
}
