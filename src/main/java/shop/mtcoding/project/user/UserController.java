package shop.mtcoding.project.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.position.WishPosition;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    ///////// 구직자홈
    @GetMapping("/user")
    public String home(Model model) {
        return "user_index";
    }

    ///////// 회사홈
    @GetMapping("/comp")
    public String compHome() {
        return "comp_index";
    }

    ///////// 구직자 회원가입페이지
    @GetMapping("/user/joinForm")
    public String userJoinForm() {
        return "user/user_join";
    }

    ///////// 회사 회원가입페이지
    @GetMapping("/comp/joinForm")
    public String compJoinForm() {
        return "comp/comp_join";
    }

    ///////// 유저 회원가입
    @PostMapping("/user/join")
    public @ResponseBody String userJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/user", "회원가입 완료");

    }

    ///////// 구직자 회원가입
    @PostMapping("/comp/join")
    public @ResponseBody String compJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/comp", "회원가입 완료");
    }

    ///////// 유저 비번 변경하기 완료
    @PostMapping("/user/password/update")
    public @ResponseBody String userUpdate(UserRequest.UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return Script.href("/user/myPageForm", "비밀번호 변경완료");
    }

    ///////// 회사 비번 변경하기 완료
    @PostMapping("/com/password/update")
    public @ResponseBody String compPWUpdate(UserRequest.UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return Script.href("/user/myPageForm", "비밀번호 변경완료");
    }

    ///////// 유저 제안 수락
    @PostMapping("/user/suggest/accept")
    public @ResponseBody String userSuggestAccept() {
        return null;
    }

    @GetMapping("/user/resume/wirteForm")
    public String UserResumeWrite() {
        return "user/user_resume_write";
    }

    @PostMapping("/user/picUpdate")
    public String userPicUpdate(UserRequest.UserPicUpdateDTO userPicUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.유저사진수정(userPicUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "user/user_mypage";
    }

    @GetMapping("/user/loginForm")
    public String userLoginForm() {
        return "user/user_login";

    }

    @GetMapping("/comp/loginForm")
    public String compLoginForm() {
        return "comp/comp_login";
    }

    //////// 구직자///////

    @GetMapping("/user/MyPageForm")
    public String userMyPageForm(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeRepository.findByUserId(sessionUser.getId());
        model.addAttribute("resumeList", resumeList);

        // List<WishPosition> wishPositionList =
        // wishPositionRepository.positionFindByResumeId(id);
        // List<HasSkill> hasSkillList = hasSkillRepository.hasSkillofResume(id);

        // model.addAttribute("hasSkillList", hasSkillList);
        // model.addAttribute("wishPositionList", wishPositionList);
        return "user/user_mypage";
    }

    @GetMapping("/comp/MyPageForm")
    public String compMyPageForm() {
        return "comp/comp_info";
    }

    @PostMapping("/user/login")
    public @ResponseBody String userLogin(UserRequest.UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/user", "로그인 완료");
    }

    // @GetMapping("/compMyPageForm")
    // public String compMyPage() {
    // return "comp/comp_info";
    // }

    @PostMapping("/comp/login")
    public @ResponseBody String compLogin(UserRequest.UserLoginDTO userLoginDTO) {
        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/comp", "로그인 완료");
    }

    @GetMapping("/api/check")
    public @ResponseBody ApiUtil<String> check(String userEmailId) {
        User user = userRepository.findByUserEmailId(userEmailId);
        if (user != null) {
            // return new ApiUtil<String>(false, "유저네임을 사용할 수 없습니다.");
            throw new MyApiException("EmailID를 사용할 수 없습니다");
        }

        return new ApiUtil<String>(true, "EmailID를 사용할 수 있습니다.");

    }

    @GetMapping("/user/logout")
    public String userLogout() {
        session.invalidate();
        return "redirect:/user";
    }

    @GetMapping("/comp/logout")
    public String compLogout() {
        session.invalidate();
        return "redirect:/comp";
    }

    @PostMapping("/compinfo/update")
    public String compInfoUpdate(UserRequest.CompInfoUpdateDTO compInfoUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회사정보수정(compInfoUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "comp/comp_info";
    }
}