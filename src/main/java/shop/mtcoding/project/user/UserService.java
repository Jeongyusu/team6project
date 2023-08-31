package shop.mtcoding.project.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.user.UserRequest.CompJoinDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void 유저회원가입(UserJoinDTO joinDTO) {
        User user = User.builder().userEmailId(joinDTO.getUserEmailId()).userName(joinDTO.getUserName()).userPassword(joinDTO.getPassword()).build();
        userRepository.save(user);
    }

    @Transactional
    public void 기업회원가입(CompJoinDTO compJoinDTO) {
        User user = User.builder().compEmailId(compJoinDTO.getCompEmailId()).compName(compJoinDTO.getCompName()).userPassword(compJoinDTO.getPassword()).build();
        userRepository.save(user);
    }
    


}
