package shop.mtcoding.project.resume;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.mtcoding.project.jobopening.JobOpening;

@Repository
public class ResumeQueryRepository {

    @Autowired
    private EntityManager em;

    public List<Resume> mFindBySelectedCareerOrCareerYearOrAddress(String career, String careerYear,
            String address) {
        String sql = "SELECT * FROM resume_tb r WHERE (r.career LIKE :career) OR (r.career_year LIKE :career_year) OR (r.address LIKE :address)";
        Query query = em.createNativeQuery(sql, Resume.class);

        query.setParameter("career", "%" + career + "%");
        query.setParameter("careerYear", "%" + careerYear + "%");
        query.setParameter("address", "%" + address + "%");

        return (List<Resume>) query.getResultList();
    }

    public List<Resume> mFindBySelectedCareerOrCareerYearAndAddress(String career, String careerYear,
            String address) {
        String sql = "SELECT * FROM resume_tb r WHERE ((r.career LIKE :career) OR (r.career_year LIKE :career_year)) And (r.address LIKE :address)";
        Query query = em.createNativeQuery(sql, Resume.class);

        query.setParameter("career", "%" + career + "%");
        query.setParameter("careerYear", "%" + careerYear + "%");
        query.setParameter("address", "%" + address + "%");

        return (List<Resume>) query.getResultList();
    }
}
