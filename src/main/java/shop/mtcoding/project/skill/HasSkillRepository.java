package shop.mtcoding.project.skill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HasSkillRepository extends JpaRepository<HasSkill, Integer> {

    @Query("select h from HasSkill as h left join fetch h.skill as hs left join h.resume hr where hr.id = :resumeId")
    public List<HasSkill> hasSkillofResume(@Param("resumeId") Integer resumeId);

    @Query("select h from HasSkill h where h.resume.id = :resumeId")
    public List<HasSkill> findByResumeId(@Param("resumeId") Integer resumeId);

}