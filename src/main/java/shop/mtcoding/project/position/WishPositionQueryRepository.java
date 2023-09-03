package shop.mtcoding.project.position;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.skill.SkillRequest.MySkill;

@Repository
public class WishPositionQueryRepository {

    @Autowired
    private EntityManager em;

    List<MySkill> mFindBySkillListofResume(Integer resumeId) {
        Query query = em.createNativeQuery("select s.skill from has_skill_tb h left outer join skill_tb s on h.skill_id = s.id where h.resume_id = :resumeId", MySkill.class);
        query.setParameter("resumeId", resumeId);
        return query.getResultList();
    }


    
}
