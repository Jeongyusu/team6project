package shop.mtcoding.project.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    @Query("select b from Community as b where title like %:keyword%")
    Page<Community> findBySearchAll(@Param("id") Pageable pageable, @Param("keyword") String keyword);

}
