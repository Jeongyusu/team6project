package shop.mtcoding.project.scrap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompScrapRepository extends JpaRepository<CompScrap, Integer> {

    @Query("select c from CompScrap c where c.resume.id = :resumeId and c.user.id = :userId")
    Optional<CompScrap> mfindByResumeId(@Param("resumeId") Integer resumeId,
            @Param("userId") Integer userId);

    @Modifying
    @Query("delete from CompScrap c where c.resume.id = :resumeId and c.user.id = :userId")
    void mdeleteByResumeId(@Param("resumeId") Integer resumeId, @Param("userId") Integer userId);

}
