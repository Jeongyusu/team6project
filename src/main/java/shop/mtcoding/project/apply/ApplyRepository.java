package shop.mtcoding.project.apply;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.project.suggest.Suggest;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    @Query("select a from Apply a where a.resume.id = :resumeId")
    public List<Apply> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.user.id = :userId")
    public List<Apply> findByUserId(@Param("userId") Integer userId);

    @Query("select a from Apply a where a.jobOpening.user.id = :userId")
    public List<Apply> findByUserIdofJobOpening(@Param("userId") Integer userId);

    @Query("select a From Apply a where a.resume.id = :resumeId and a.jobOpening.id = :jobOpeningId")
    Apply findByResumeIdAndJobOpeningId(@Param("resumeId") Integer resumeId, @Param("jobOpeningId") Integer jobOpeningId);

}
