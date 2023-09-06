package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequiredPositionRepository extends JpaRepository<RequiredPosition, Integer> {
    @Query("select rs from RequiredPosition rs where rs.jobOpening.id = :jobOpeningId")
    List<RequiredPosition> findByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId);

    @Query("select r from RequiredPosition as r left join fetch r.position as rp left join r.jobOpening as rj where rj.id = :id")
    List<RequiredPosition> mfindByIdJoinPosition(@Param("id") Integer id);

    @Query("select r from RequiredPosition as r where r.position.id = :id")
    List<RequiredPosition> mfindByPositionId(@Param("id") Integer id);

}
