package shop.mtcoding.project.scrap;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserScrapRepository extends JpaRepository<UserScrap, Integer> {

    @Query("select u from UserScrap u where u.jobOpening.id = :jobOpeningId and u.user.id = :userId")
    Optional<UserScrap> mfindByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId,
            @Param("userId") Integer userId);

    @Modifying
    @Query("delete from UserScrap u where u.jobOpening.id = :jobOpeningId and u.user.id = :userId")
    void mdeleteByJobOpeningId(@Param("jobOpeningId") Integer jobOpeningId, @Param("userId") Integer userId);

}