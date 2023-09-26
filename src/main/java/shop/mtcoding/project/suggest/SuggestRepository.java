package shop.mtcoding.project.suggest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestRepository extends JpaRepository<Suggest, Integer> {

    @Query(value = "select * from suggest_tb st left outer join resume_tb rt on st.resume_id = rt.id where st.user_id= :userId", nativeQuery = true)
    List<Suggest> findBySuggestCompId(@Param("userId") Integer userId);

    @Query("select s From Suggest s where s.resume.user.id = :userId")
    List<Suggest> findBySuggestResumeUserId(@Param("userId") Integer userId);

    @Query("select s From Suggest s where s.resume.id = :resumeId and s.jobOpening.id = :jobOpeningId")
    Suggest findByResumeIdAndJobOpeningId(@Param("resumeId") Integer userId,
            @Param("jobOpeningId") Integer jobOpeningId);

    @Query("select s From Suggest s where s.jobOpening.user.id = :userId")
    List<Suggest> findByUserIdofJobOpeningInSuggest(@Param("userId") Integer userId);

}
