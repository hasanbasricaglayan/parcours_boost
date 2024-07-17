package fr.hb.hasan.parcours_boost.repository;

import fr.hb.hasan.parcours_boost.business.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
}
