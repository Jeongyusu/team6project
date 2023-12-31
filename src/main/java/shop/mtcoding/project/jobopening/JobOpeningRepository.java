package shop.mtcoding.project.jobopening;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.project.resume.Resume;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

        // 공고 상세보기
        @Query("select j from JobOpening as j left join fetch j.user as ju where j.id = :id")
        Optional<JobOpening> mfindByIdJoinJobOpeningAndUser(@Param("id") Integer id);

        // 공고,유저 조인
        @Query("select DISTINCT j from JobOpening as j left join fetch j.user as ju")
        List<JobOpening> mfindByAllJoinJobOpeningAndUser();

        @Query("select j from JobOpening j where j.career = :selectedCareer or j.careerYear = :selectedCareerYear")
        List<JobOpening> findBySelectedCareerOrCareerYear(@Param("selectedCareer") String selectedCareer,
                        @Param("selectedCareerYear") String selectedCareerYear);

        @Query("select jo from JobOpening jo where jo.user.id = :userId")
        public List<JobOpening> findByUserId(@Param("userId") Integer userId);

        @Query("select j from JobOpening j left join fetch j.requiredPositionList jr left join fetch jr.position")
        public List<JobOpening> findByPositionInRequiredPositionInJobOpeing();

        @Query("SELECT r FROM Resume r WHERE r.openCheck = '1' AND (r.career LIKE %:career% OR r.careerYear IS NULL OR r.careerYear = :careerYear) OR r.address LIKE %:address%")
        List<Resume> findByOpenAndCareerAndOpenAddress(@Param("career") String career,
                        @Param("careerYear") String careerYear, @Param("address") String address);

        @Query("select j from JobOpening as j where title like %:keyword% or compAddress like %:keyword%")
        List<JobOpening> mfindBySearchJobOpeningList(@Param("keyword") String keyword);

}