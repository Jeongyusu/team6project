package shop.mtcoding.project.suggest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.apply.Apply;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.user.User;

@Repository
public class SuggestQueryRepository {

    @Autowired
    private EntityManager em;

    public List<JobOpening> findJobOpeningsByUserId(Integer userId) {
        Query query = em.createNativeQuery(
                "SELECT jo.* FROM suggest_tb s LEFT OUTER JOIN resume_tb r ON s.resume_id = r.id LEFT OUTER JOIN user_tb u ON r.user_id = u.id LEFT OUTER JOIN job_opening_tb jo ON s.job_opening_id = jo.id WHERE u.id = :userId",
                JobOpening.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    // public List<Suggest> findJobOpeningsByCompId(Integer userId) {
    // Query query = em.createNativeQuery(
    // "SELECT u.*, r.*, jo.* FROM job_opening_tb jo LEFT OUTER JOIN suggest_tb s ON
    // jo.id = s.job_opening_id LEFT OUTER JOIN resume_tb r ON s.resume_id = r.id
    // LEFT OUTER JOIN user_tb u ON jo.user_id = u.id WHERE u.id = :userId",
    // JobOpening.class);
    // query.setParameter("userId", userId);
    // return query.getResultList();
    // }

}
