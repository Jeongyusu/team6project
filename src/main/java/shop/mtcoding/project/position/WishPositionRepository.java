package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.project.resume.Resume;

public interface WishPositionRepository extends JpaRepository<WishPosition, Integer> {

    @Query("select w from WishPosition as w left join fetch w.position as wp left join w.resume wr where wr.id = :resumeId")
    public List<WishPosition> wishPositionofResume(@Param("resumeId") Integer resumeId);

    @Query("select w from WishPosition as w where w.resume.id = :resumeId")
    public List<WishPosition> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select w from WishPosition as w where w.resume.id = :resumeId")
    public List<WishPosition> positionFindByResumeId(@Param("resumeId") Integer resumeId);

    @Query("SELECT CASE WHEN COUNT(wp) > 0 THEN true ELSE false END FROM WishPosition wp WHERE wp.resume = :resume AND wp.position = :position")
    boolean existsByResumeAndPosition(@Param("resume") Resume resume, @Param("position") Position position);
}
