package shop.mtcoding.project.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("select s from Skill s where s.skill = :skill")
    Skill findBySkillName(@Param("skill") String skill);

}