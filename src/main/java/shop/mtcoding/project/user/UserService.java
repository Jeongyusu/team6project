package shop.mtcoding.project.user;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.vo.MyPath;
import shop.mtcoding.project.user.UserRequest.CompUpdateDTO;
import shop.mtcoding.project.user.UserRequest.UserPicUpdateDTO;
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

        user.setUserPicUrl(fileName);

        userRepository.save(user);

        return user;
    }

    @Transactional
    public User 회사정보수정(CompUpdateDTO compUpdateDTO, Integer id) {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + compUpdateDTO.getCompPic().getOriginalFilename();
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            Files.write(filePath, compUpdateDTO.getCompPic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        User user = userRepository.findById(id).get();

        user.setCompPicUrl(fileName);

        Date compHistoryDate = new Date(compUpdateDTO.getCompDate().getTime());
        user.setCompHistory(compHistoryDate);
        user.setCompIntro(compUpdateDTO.getCompExplan());

        userRepository.save(user);

        return user;

    }

    //////////////////////////////////////////////////////
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
