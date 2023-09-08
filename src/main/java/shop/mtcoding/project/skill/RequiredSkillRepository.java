package shop.mtcoding.project.skill;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {

    // 상세보기 쿼리
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join r.jobOpening as rj where rj.id = :id")
    public List<RequiredSkill> mfindByIdJoinSkill(@Param("id") Integer id);

    // 메인화면 쿼리 - 쿼리실행 1
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join fetch r.jobOpening as rj")
    public List<RequiredSkill> mfindByAllJoinSkillAndJobOpening();

    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join fetch r.jobOpening as rj where rj.id = :id")
    public List<RequiredSkill> mfindByIdJoinSkillAndJobOpening(@Param("id") Integer id);

    @Query("select rs from RequiredSkill rs where rs.jobOpening.id = :jobOpeningId")
    public List<RequiredSkill> findByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId);

}