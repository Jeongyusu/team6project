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

    // 그 스킬 안에 있는 공개체크된 이력서만 가지고 온다
    @Query("SELECT r FROM Resume r WHERE r.openCheck = '1'")
    public List<Resume> findByCheckUserId();

    @Query("SELECT DISTINCT r FROM Resume r WHERE r.openCheck = '1' AND ((:career = '신입' AND r.career = '신입') OR (:career = '경력' AND r.career = '경력' AND r.careerYear = :careerYear)) AND r.address LIKE %:address%")
    List<Resume> findByOpenAndCareerAndOpenAddress(@Param("career") String career,
            @Param("careerYear") String careerYear, @Param("address") String address);

}
