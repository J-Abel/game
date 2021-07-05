package hu.rsoft.homework.repository;

import hu.rsoft.homework.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
