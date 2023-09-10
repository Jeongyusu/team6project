package shop.mtcoding.project.resume;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r where r.user.id = :userId")
    public List<Resume> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT r FROM Resume r JOIN r.hasSkillList hs WHERE hs.skill.id = :skillId1 OR hs.skill.id = :skillId2")
    public List<Resume> findByHasSkillIds(@Param("skillId1") Integer skillId1, @Param("skillId2") Integer skillId2);

    @Query("SELECT DISTINCT r FROM Resume r LEFT JOIN FETCH r.jobOpeningList j WHERE j.user.id = :userId")
    public List<Resume> findByUserIdofJobOpeningList(@Param("userId") Integer userId);
}
