package shop.mtcoding.project.resume;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.RequiredSkill;

@Repository
public class ResumeQueryRepository {

    @Autowired
    private EntityManager em;

    public List<RequiredSkill> findJobRequiredSkillId(Integer id) {
        Query query = em.createNativeQuery(
                "SELECT rs.* FROM required_skill_tb rs INNER JOIN job_opening_tb jo ON rs.job_opening_id = jo.id WHERE jo.id = jobSkillId;",
                RequiredSkill.class);
        query.setParameter("jobSkillId", id);
        return query.getResultList();
    }

    public List<HasSkill> findResumeHasSkillId(Integer id) {
        Query query = em.createNativeQuery(
                "SELECT hs.* FROM has_skill_tb hs INNER JOIN resume_tb re ON hs.resume_id = re.id WHERE re.id = jobSkillId;",
                HasSkill.class);
        query.setParameter("resumeSkillId", id);
        return query.getResultList();
    }
}
