package shop.mtcoding.project.community;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    // board 제목/내용 검색 & 목록
    @Query("select c from Community as c where title like %:keyword% or content like %:keyword%")
    Page<Community> mfindBySearchAll(@Param("id") Pageable pageable, @Param("keyword") String keyword);

    // board 상세
    @Query("select c from Community as c left join fetch c.replyList as r left join fetch r.user as ru where c.id = :id")
    Optional<Community> mfindByIdJoinReplyAndBoard(@Param("id") Integer id);
}
