package shop.mtcoding.project.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {

    @Query("select a from Apply a where a.resume.id = :resumeId")
    public List<Apply> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.resume.user.id = :userId")
    public List<Apply> findByResumeUserId(@Param("userId") Integer userId);

    @Query("select a from Apply a where a.jobOpening.user.id = :userId")
    public List<Apply> findByResumeUserInfo(@Param("userId") Integer userId);

}
