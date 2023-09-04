package shop.mtcoding.project.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("SELECT p FROM Position p WHERE p.position = :position")
    public Position findByPositionName(@Param("position") String position);
}