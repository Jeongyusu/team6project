package shop.mtcoding.project.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@DataJpaTest
public class ApplyRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById_test() {
        User user = userRepository.findById(1).get();
        System.out.println("테스트" + user.getUserName());
    }
}
