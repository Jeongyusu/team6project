package shop.mtcoding.project.jobopening;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningRequiredPositionDTO;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

    // 공고 상세보기
    @Query("select j from JobOpening as j left join fetch j.user as ju where j.id = :id")
    Optional<JobOpening> mfindByIdJoinJobOpeningAndUser(@Param("id") Integer id);

    // 공고,유저 조인
    @Query("select j from JobOpening as j left join fetch j.user as ju")
    List<JobOpening> mfindByAllJoinJobOpeningAndUser();

    @Query("select j from JobOpening j where j.career = :selectedCareer or j.careerYear = :selectedCareerYear")
    List<JobOpening> findBySelectedCareerOrCareerYear(@Param("selectedCareer") String selectedCareer,
            @Param("selectedCareerYear") String selectedCareerYear);

    @Query("select jo from JobOpening jo where jo.user.id = :userId")
    public List<JobOpening> findByUserId(@Param("userId") Integer userId);

    @Query("select j from JobOpening j left join fetch j.requiredPositionList jr left join fetch jr.position")
    public List<JobOpening> findByPositionInRequiredPositionInJobOpeing();

}