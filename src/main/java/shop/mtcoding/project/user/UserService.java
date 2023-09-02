package shop.mtcoding.project.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.user.UserRequest.UserSaveResumeDTO;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    @Transactional
    public void 이력서작성(UserRequest.UserSaveResumeDTO userSaveResumeDTO, int sessionUserId) {
        Resume resume = Resume.builder()
                .title(userSaveResumeDTO.getTitle())
                .userName(userSaveResumeDTO.getUserName())
                .userEmailId(userSaveResumeDTO.getUserEmailId())
                .birth(userSaveResumeDTO.getBirth())
                .tel(userSaveResumeDTO.getTel())
                .address(userSaveResumeDTO.getAddress())
                .subIntro(userSaveResumeDTO.getSubIntro())
                .career(userSaveResumeDTO.getCareer())
                .careerYear(userSaveResumeDTO.getCareerYear())
                .edu(userSaveResumeDTO.getEdu())
                .resumePic(userSaveResumeDTO.getResumePic())
                .openCheck(userSaveResumeDTO.getOpenCheck())
                .mainIntro(userSaveResumeDTO.getMainIntro())
                .createdAt(userSaveResumeDTO.getCreatedAt())
                .user(User.builder().id(sessionUserId).build())
                .positionList(userSaveResumeDTO.getPositionList())
                .skillist(userSaveResumeDTO.getSkillList())
                .build();

        




                
    }

}
