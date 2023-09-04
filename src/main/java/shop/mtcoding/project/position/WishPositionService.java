package shop.mtcoding.project.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.skill.SkillRequest.MySkill;

@Service
public class WishPositionService {

    @Autowired
    WishPositionQueryRepository wishPositionQueryRepository;

    // public List<MySkill> 이력서스킬상태복원(Integer resumeId) {
    // // return wishPositionQueryRepository.mFindBySkillListofResume(resumeId);

    // }

}
