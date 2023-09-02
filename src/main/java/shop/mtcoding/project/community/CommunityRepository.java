package shop.mtcoding.project.community;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    // 글 상세보기
    @Query("select c from Community as c left join fetch c.replyList as r left join fetch r.user as ru where c.id = :id")
    Optional<Community> mfindByIdJoinReplyAndBoard(@Param("id") Integer id);
}
