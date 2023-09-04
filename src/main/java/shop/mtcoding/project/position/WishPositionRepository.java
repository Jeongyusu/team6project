package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishPositionRepository extends JpaRepository<WishPosition, Integer> {

    @Query("select w from WishPosition as w left join fetch w.position as wp left join w.resume wr where wr.id = :resumeId")
    public List<WishPosition> wishPositionofResume(@Param("resumeId") Integer resumeId);
}
