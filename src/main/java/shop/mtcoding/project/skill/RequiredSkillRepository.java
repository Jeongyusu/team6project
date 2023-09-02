package shop.mtcoding.project.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {

}
