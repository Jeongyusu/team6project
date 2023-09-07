package shop.mtcoding.project.skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> 스킬이름() {
        List<Skill> skillList = skillRepository.findAll();
        return skillList;
    }

}