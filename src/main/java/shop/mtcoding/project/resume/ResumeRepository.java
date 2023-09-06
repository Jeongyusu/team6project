package shop.mtcoding.project.resume;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r where r.user.id = :userId")
    public List<Resume> findByUserId(@Param("userId") Integer userId);

}