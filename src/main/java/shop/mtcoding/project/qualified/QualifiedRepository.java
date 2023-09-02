package shop.mtcoding.project.qualified;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QualifiedRepository extends JpaRepository<Qualified, Integer> {
    @Query("select q from Qualified q where q.qualifiedContent = :qualifiedContent")
    Qualified findByName(@Param("qualifiedContent") String qualifiedContent);
}
