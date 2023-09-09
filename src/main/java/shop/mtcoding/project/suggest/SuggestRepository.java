package shop.mtcoding.project.suggest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestRepository extends JpaRepository<Suggest, Integer> {

    @Query("SELECT s FROM Suggest s JOIN s.resume r JOIN r.user u WHERE u.id = :userId")
    List<Suggest> findBySuggestUserId(@Param("userId") Integer userId);

    @Query(value = "select * from suggest_tb st left outer join resume_tb rt on st.resume_id = rt.id where st.user_id= :userId", nativeQuery = true)
    List<Suggest> findBySuggestCompId(@Param("userId") Integer userId);

    @Query("select s From Suggest s where s.resume.user.id = :userId")
    List<Suggest> findBySuggestResumeUserId(@Param("userId") Integer userId);

    // @Query(value = "SELECT job_opening.* FROM suggest_tb s " +
    // "LEFT OUTER JOIN resume_tb r ON s.resume_id = r.id " +
    // "LEFT OUTER JOIN user_tb u ON r.user_id = u.id " +
    // "LEFT OUTER JOIN job_opening_tb job_opening ON s.job_opening_id =
    // job_opening.id " +
    // "WHERE u.id = :userId", nativeQuery = true)
    // List<Suggest> findJobOpeningsByUserId(@Param("userId") Integer userId);

    // @Query("select sj from Suggest s left join fetch s.resume sr left join fetch
    // s.user su left join fetch s.jobOpening sj where su.id = :userId")
    // List<Suggest> findJobOpeningsByUserId(@Param("userId") Integer userId);

}
