package shop.mtcoding.project.task;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("select t from Task t where t.taskContent = :taskContent")
    Optional<Task> findByTaskName(@Param("taskContent") String taskContent);

    @Query("select t from Task t where t.jobOpening.id = :jobOpeningId")
    List<Task> findByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId);

    @Query("select t from Task t where t.jobOpening.id = :id")
    List<Task> mfindByJobOpeningId(@Param("id") Integer id);
}
