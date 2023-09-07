package shop.mtcoding.project.suggest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestRepository extends JpaRepository<Suggest, Integer> {

    @Query("SELECT s FROM Suggest s JOIN s.resume r JOIN r.user u WHERE u.id = :userId")
    List<Suggest> findBySuggestUserId(@Param("userId") Integer userId);

}
