package shop.mtcoding.project.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void 유저회원가입(UserRequest.UserJoinDTO userjoinDTO) {
        System.out.println("테스트 " + userjoinDTO.getUserEmailId());
        User user;
        if (userjoinDTO.getCompEmailId() == null) {
            user = User.builder().userEmailId(userjoinDTO.getUserEmailId()).userName(userjoinDTO.getUserName())
                    .userPassword(userjoinDTO.getUserPassword()).build();
        } else {
            user = User.builder().compEmailId(userjoinDTO.getCompEmailId()).compName(userjoinDTO.getCompName())
                    .userPassword(userjoinDTO.getUserPassword()).build();
        }

        userRepository.save(user);
    }

    public User 유저로그인(UserRequest.UserLoginDTO userloginDTO) {

        User user;
        if (userloginDTO.getCompEmailId() == null) {
            user = userRepository.findByUserEmailId(userloginDTO.getUserEmailId());
        } else {
            user = userRepository.findByCompEmailId(userloginDTO.getCompEmailId());
        }

        if (user == null) {
            throw new MyException("해당 이메일 ID가 존재하지 않습니다.");
        }

        if (!user.getUserPassword().equals(userloginDTO.getUserPassword())) {
            throw new MyException("패스워드가 잘못되었습니다.");
        }

        return user;

    }

}
