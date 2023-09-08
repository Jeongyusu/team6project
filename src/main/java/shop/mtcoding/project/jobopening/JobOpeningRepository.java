package shop.mtcoding.project.jobopening;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Integer> {

    @Query("select jo from JobOpening jo where jo.user.id = :userId")
    public List<JobOpening> findByUserId(@Param("userId") Integer userId);

}
