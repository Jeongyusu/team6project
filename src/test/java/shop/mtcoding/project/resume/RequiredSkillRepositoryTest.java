package shop.mtcoding.project.resume;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;

@DataJpaTest
public class RequiredSkillRepositoryTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Test
    public void mfindByAllJoinSkillAndJobOpening_test() {
        List<RequiredSkill> compUserResumeList = requiredSkillRepository.findAll();
        for (RequiredSkill requiredSkill : compUserResumeList) {
        }
    }
}
