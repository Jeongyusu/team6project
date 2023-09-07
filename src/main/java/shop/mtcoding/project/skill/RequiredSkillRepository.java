package shop.mtcoding.project.skill;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {

    // 상세보기 쿼리
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join r.jobOpening as rj where rj.id = :id")
    List<RequiredSkill> mfindByIdJoinSkill(@Param("id") Integer id);

    // 메인화면 쿼리 - 쿼리실행 1
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join fetch r.jobOpening as rj")
    List<RequiredSkill> mfindByAllJoinSkillAndJobOpening();

    // 메인화면 쿼리 - 쿼리실행 1
    @Query("select r from RequiredSkill as r left join fetch r.skill as rs left join fetch r.jobOpening as rj where rs.id = :id")
    List<RequiredSkill> mfindByIdJoinSkillAndJobOpening(@Param("id") Integer id);

    @Query("select rs from RequiredSkill rs where rs.jobOpening.id = :jobOpeningId")
    List<RequiredSkill> findByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId);
}