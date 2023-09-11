package shop.mtcoding.project.skill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.project.resume.Resume;

public interface HasSkillRepository extends JpaRepository<HasSkill, Integer> {

    @Query("select h from HasSkill as h left join fetch h.skill as hs left join h.resume hr where hr.id = :resumeId")
    public List<HasSkill> hasSkillofResume(@Param("resumeId") Integer resumeId);

    @Query("select h from HasSkill h where h.resume.id = :resumeId")
    public List<HasSkill> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select h from HasSkill as h left join fetch h.skill as hs left join fetch h.resume hr where hs.id = :skillId1")
    public List<HasSkill> hasSkillofResumeofSkill1(@Param("skillId1") Integer skillId);

    @Query("select h from HasSkill as h left join fetch h.skill as hs left join fetch h.resume hr where hs.id = :skillId1 or hs.id = :skillId2 ")
    public List<HasSkill> hasSkillofResumeofSkill(@Param("skillId1") Integer skillId1,
            @Param("skillId2") Integer skillId2);

    @Query("SELECT CASE WHEN COUNT(hs) > 0 THEN true ELSE false END FROM HasSkill hs WHERE hs.resume = :resume AND hs.skill = :skill")
    boolean existsByResumeAndSkill(@Param("resume") Resume resume, @Param("skill") Skill skill);

}
