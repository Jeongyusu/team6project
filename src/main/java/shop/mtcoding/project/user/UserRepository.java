package shop.mtcoding.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userEmailId = :userEmailId")
    User findByUserEmailId(@Param("userEmailId") String userEmailId);

    @Query("select u from User u where u.compEmailId = :compEmailId")
    User findByCompEmailId(@Param("compEmailId") String compEmailId);

}