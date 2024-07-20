package fr.hb.hasan.parcours_boost.repository;

import fr.hb.hasan.parcours_boost.business.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
	List<Parcours> findAllByOrderByNomAsc();

	@Query("SELECT p FROM Parcours AS p WHERE p.nom LIKE '%place%'")
	List<Parcours> findByNomContaining();

	@Query("SELECT DISTINCT p FROM Parcours AS p INNER JOIN p.positions AS pos WHERE pos.longitude > 45.75")
	List<Parcours> findByPositionGreaterThan();
}
