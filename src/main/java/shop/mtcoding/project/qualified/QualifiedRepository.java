package shop.mtcoding.project.qualified;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QualifiedRepository extends JpaRepository<Qualified, Integer> {
    @Query("select q from Qualified q where q.qualifiedContent = :qualifiedContent")
    Qualified findByQualName(@Param("qualifiedContent") String qualifiedContent);

    @Query("select t from Qualified t where t.jobOpening.id = :jobOpeningId")
    List<Qualified> findByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId);

    @Query("select q from Qualified q where q.jobOpening.id = :id")
    List<Qualified> mfindByJobOpeningId(@Param("id") Integer id);

    @Modifying
    @Query("delete from Qualified q where q.jobOpening.id = :id")
    void deleteByJobOpeningId(@Param("id") Integer id);
}