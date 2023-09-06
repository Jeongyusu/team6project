package shop.mtcoding.project.jobopening;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

    // @Query("SELECT j FROM JobOpening j JOIN FETCH j.positionList pl JOIN FETCH
    // j.skillList sl JOIN FETCH j.qualList ql JOIN FETCH j.taskList tl WHERE j.id
    // =:id")
    // Optional<JobOpening> findJobOpeningWithList(@Param("id") Integer id);
}
