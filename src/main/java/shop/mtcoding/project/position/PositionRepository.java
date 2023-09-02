package shop.mtcoding.project.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("select p from Position p where p.position = :position")
    Position findByName(@Param("position") String position);

}
