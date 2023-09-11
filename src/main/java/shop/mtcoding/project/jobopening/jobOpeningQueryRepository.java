package shop.mtcoding.project.jobopening;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JobOpeningQueryRepository {

    @Autowired
    private EntityManager em;

    //// 채용정보에서 포지션과 스킬을 조인해서 선택한 조건에 맞는 데이터 출력
    public List<JobOpening> findByIdJoinPositionAndSkill(List<Integer> positionIdList,
            List<Integer> skillIdList) {

        String sql = "SELECT j.* FROM job_opening_tb j LEFT JOIN required_position_tb rp ON j.id = rp.job_opening_id LEFT JOIN required_skill_tb rs ON j.id = rs.job_opening_id ";

        // 반복문을 통해 체크박스를 체크한 만큼 검색하기
        // 포지션 조건이 있다면
        if (!positionIdList.isEmpty()) {
            sql += "WHERE ";
            for (int i = 0; i < positionIdList.size(); i++) {
                if (i == 0) {
                    sql += "rp.position_id = :positionId" + i + " ";
                } else {
                    sql += "or rp.position_id = :positionId" + i + " ";
                }
            }
        }

        // 스킬 조건도 있다면
        if (!skillIdList.isEmpty()) {
            if (positionIdList.isEmpty()) {
                // 포지션 조건이 없으면 WHERE 키워드를 추가하고 시작
                sql += "WHERE ";
            } else {
                // 이미 포지션 조건이 추가되었으면 AND 연산자로 스킬 조건 추가
                sql += "AND ";
            }
            for (int i = 0; i < skillIdList.size(); i++) {
                if (i == 0) {
                    sql += "rs.skill_id = :skillId" + i + " ";
                } else {
                    sql += "or rs.skill_id = :skillId" + i + " ";
                }
            }
        }

        // 매핑
        Query query = em.createNativeQuery(sql, JobOpening.class);

        for (int i = 0; i < positionIdList.size(); i++) {
            query.setParameter("positionId" + i, positionIdList.get(i));
        }
        for (int i = 0; i < skillIdList.size(); i++) {

            query.setParameter("skillId" + i, skillIdList.get(i));
        }

        System.out.println("쿼리" + sql);

        return (List<JobOpening>) query.getResultList();
    }

    //// 채용정보에서 경력, 기간, 지역을 조인해서, 셋 중에 1의 값만 있어도 조건에 맞는 데이터 출력 = OR/like 사용
    public List<JobOpening> mFindBySelectedCareerOrCareerYearOrLocation(String career, String careerYear,
            String location) {
        String sql = "SELECT * FROM Job_opening_tb j WHERE (j.career LIKE :career) OR (j.career_year LIKE :careerYear) OR (j.comp_address LIKE :location)";
        Query query = em.createNativeQuery(sql, JobOpening.class);

        query.setParameter("career", "%" + career + "%");
        query.setParameter("careerYear", "%" + careerYear + "%");
        query.setParameter("location", "%" + location + "%");

        return (List<JobOpening>) query.getResultList();
    }

    //// 채용정보에서 경력, 기간, 지역을 조인해서, 셋 중에 지역 + 신입 또는 지역 +경력에 조건에 맞는 데이터 출력 = AND/like
    //// 사용
    public List<JobOpening> mFindBySelectedCareerOrCareerYearAndLocation(String career, String careerYear,
            String location) {
        String sql = "SELECT * FROM Job_opening_tb j WHERE ((j.career LIKE :career) OR (j.career_year LIKE :careerYear)) And (j.comp_address LIKE :location)";
        Query query = em.createNativeQuery(sql, JobOpening.class);

        query.setParameter("career", "%" + career + "%");
        query.setParameter("careerYear", "%" + careerYear + "%");
        query.setParameter("location", "%" + location + "%");

        return (List<JobOpening>) query.getResultList();
    }

}
