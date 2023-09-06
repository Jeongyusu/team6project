package shop.mtcoding.project.user;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.vo.MyPath;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.CompInfoUpdateDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserLoginDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserPicUpdateDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserUpdateDTO;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Transactional
    public void 유저회원가입(UserRequest.UserJoinDTO userjoinDTO) {

        User user = null;
        if (userjoinDTO.getGubun() == 1) {
            if (userjoinDTO.getUserEmailId() == null || userjoinDTO.getUserEmailId().isEmpty()) {
                throw new MyException("아이디에 값이 없거나 공백문자가 있습니다.");
            }
            if (userjoinDTO.getUserPassword() == null || userjoinDTO.getUserPassword().isEmpty()) {
                throw new MyException("비밀번호에 값이 없거나 공백문자가 있습니다.");
            }

            if (userjoinDTO.getUserName() == null || userjoinDTO.getUserName().isEmpty()) {
                throw new MyException("이름에 값이 없거나 공백문자가 있습니다.");
            }
            user = User.builder().userEmailId(userjoinDTO.getUserEmailId()).userName(userjoinDTO.getUserName())
                    .userPassword(userjoinDTO.getUserPassword()).gubun(userjoinDTO.getGubun()).build();
        }
        if (userjoinDTO.getGubun() == 2) {
            if (userjoinDTO.getCompEmailId() == null || userjoinDTO.getCompEmailId().isEmpty()) {
                throw new MyException("아이디에 값이 없거나 공백문자가 있습니다.");
            }
            if (userjoinDTO.getUserPassword() == null || userjoinDTO.getUserPassword().isEmpty()) {
                throw new MyException("비밀번호에 값이 없거나 공백문자가 있습니다.");
            }

            if (userjoinDTO.getUserName() == null || userjoinDTO.getUserName().isEmpty()) {
                throw new MyException("회사 이름에 값이 없거나 공백문자가 있습니다.");
            }
            user = User.builder().compEmailId(userjoinDTO.getCompEmailId()).userName(userjoinDTO.getUserName())
                    .userPassword(userjoinDTO.getUserPassword()).gubun(userjoinDTO.getGubun()).build();
        }

        userRepository.save(user);
    }

    public User 유저로그인(UserLoginDTO userloginDTO) {

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

    public User 회원정보수정(UserUpdateDTO userUpdateDTO, Integer id) {
        // 1.조회
        User user = userRepository.findById(id).get();
        // 2.변경
        user.setUserPassword(userUpdateDTO.getNewPassword());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User 유저사진수정(UserPicUpdateDTO userPicUpdateDTO, Integer id) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + userPicUpdateDTO.getUserPic().getOriginalFilename();
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            Files.write(filePath, userPicUpdateDTO.getUserPic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        User user = userRepository.findById(id).get();

        user.setCompPicUrl(fileName);

        userRepository.save(user);

        return user;
    }

    @Transactional
    public User 회사정보수정(CompInfoUpdateDTO compInfoUpdateDTO, Integer id) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + compInfoUpdateDTO.getCompPic().getOriginalFilename();
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            Files.write(filePath, compInfoUpdateDTO.getCompPic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        User user = userRepository.findById(id).get();

        user.setCompPicUrl(fileName);

        Date compHistoryDate = new Date(compInfoUpdateDTO.getCompDate().getTime());
        user.setCompHistory(compHistoryDate);
        user.setCompIntro(compInfoUpdateDTO.getCompExplan());

        userRepository.save(user);

        return user;

    }

}
