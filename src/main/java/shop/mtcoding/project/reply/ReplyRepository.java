package shop.mtcoding.project.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    // userId로 reply 조회
    @Query("select r from Reply r where r.user.id = :userId")
    List<Reply> findByUserId(@Param("userId") Integer userId);
}
