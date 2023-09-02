package shop.mtcoding.project.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("select t from Task t where t.taskContent = :taskContent")
    Task findByName(@Param("taskContent") String taskContent);
}
