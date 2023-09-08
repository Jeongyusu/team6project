package shop.mtcoding.project.skill;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class skillServiceTest {

    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void findAll_test() {
        List<Skill> skillList = skillRepository.findAll();
        System.out.println("테스트 : " + skillList.get(0).getSkill());

        // for (Skill skill : skillList) {
        // System.out.println("테스트" + skill);
        // }
    }
}
