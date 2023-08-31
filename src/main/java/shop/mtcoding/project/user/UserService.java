package shop.mtcoding.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.project.user.UserRequest.UserUpdateDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User 회원정보수정(UserUpdateDTO userUpdateDTO, Integer id) {
        // 1.조회
        User user = userRepository.findById(id).get();
        // 2.변경
        user.setUserPassword(userUpdateDTO.getNewPassword());
        userRepository.save(user);
        return user;
    }

}
