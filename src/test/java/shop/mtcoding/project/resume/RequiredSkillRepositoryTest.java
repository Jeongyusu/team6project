package shop.mtcoding.project.resume;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;

@DataJpaTest
public class RequiredSkillRepositoryTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private ResumeQueryRepository resumeQueryRepository;
    // @Test
    // public void findbySkillJobOpening_test() {
    // List<RequiredSkill> requiredSkillList =
    // requiredSkillRepository.mfindByAllJoinSkillAndJobOpening();
    // System.out.println(requiredSkillList.get(0).getJobOpening());
    // }

    @Test
    public void mfindByAllJoinSkillAndJobOpening_test() {
        List<RequiredSkill> compUserResumeList = requiredSkillRepository.findAll();
        for (RequiredSkill requiredSkill : compUserResumeList) {
            System.out.println("테스트 : " + requiredSkill.getJobOpening());
            System.out.println("테스트 : " + requiredSkill.getId());
            System.out.println("테스트 : " + requiredSkill.getSkill().getSkill());
        }
    }
}
