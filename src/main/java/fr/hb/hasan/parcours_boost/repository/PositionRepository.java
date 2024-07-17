package fr.hb.hasan.parcours_boost.repository;

import fr.hb.hasan.parcours_boost.business.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
