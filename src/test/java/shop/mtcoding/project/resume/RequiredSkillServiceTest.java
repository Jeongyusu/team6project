package shop.mtcoding.project.resume;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;

@DataJpaTest
public class RequiredSkillServiceTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Test
    public void mfindByAllJoinSkillAndJobOpening_test() {
        List<RequiredSkill> compUserResumeList = requiredSkillRepository.findAll();
        System.out.println("Test: " + compUserResumeList.get(0).getJobOpening());
        System.out.println("Test: " + compUserResumeList.get(0).getId());
        System.out.println("Test: " + compUserResumeList.get(0).getSkill().getSkill());
        for (RequiredSkill requiredSkill : compUserResumeList) {
            System.out.println("테스트 : " + requiredSkill.getJobOpening());
            System.out.println("테스트 : " + requiredSkill.getId());
            System.out.println("테스트 : " + requiredSkill.getSkill().getSkill());
        }
    }
}
