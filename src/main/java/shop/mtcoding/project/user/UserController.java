package shop.mtcoding.project.user;


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
import shop.mtcoding.project.user.UserRequest.UserJoinDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.CompInfoUpdateDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserLoginDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserPicUpdateDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserSaveResumeDTO;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserUpdateDTO;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/user")
    public String home(Model model) {
        return "user_index";
    }

    @GetMapping("/comp")
    public String compHome() {
        return "comp_index";
    }

    @GetMapping("/user/joinForm")
    public String userJoinForm() {
        return "user/user_join";
    }

    @GetMapping("/comp/joinForm")
    public String compJoinForm() {
        return "comp/comp_join";
    }

    @PostMapping("/user/join")
    public @ResponseBody String userJoin(UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/user", "회원가입 완료");

    }

    @PostMapping("/comp/join")
    public @ResponseBody String compJoin(UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/comp", "회원가입 완료");
    }
    //////// 구직자///////
  
    @GetMapping("/userMyPageForm")
    public String userMyPage() {
        return "user/user_mypage";
    }

    @GetMapping("/userResumeWriteForm")
    public String UserResumeWrite() {
        return "user/user_resume_write";
    }

    @PostMapping("/user/update")
    public String userUpdate(UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser.getUserPassword().equals(userUpdateDTO.getNowPassword())) {
        } else {
            return Script.back("현재 비밀번호가 일치하지않습니다.");
        }
        if (userUpdateDTO.getNewPassword().equals(userUpdateDTO.getNewPasswordConfirm())) {
        } else {
            return Script.back("새로운 비밀번호가 일치하지않습니다.");

        }
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }

    @PostMapping("/user/picUpdate")
    public String userPicUpdate(UserPicUpdateDTO userPicUpdateDTO) {
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

    @PostMapping("/user/login")
    public @ResponseBody String userLogin(UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/user", "로그인 완료");
    }


    @GetMapping("/compMyPageForm")
    public String compMyPage() {
        return "comp/comp_info";
    }

    @PostMapping("/comp/login")
    public @ResponseBody String compLogin(UserLoginDTO userLoginDTO) {
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

    
    @GetMapping("/user/resume/saveForm")
    public String resumeSaveForm(){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/login";
        }
        return "user/user_resume_write";
    }

    @PostMapping("/user/resume/save")
    public String saveResume(UserSaveResumeDTO UserSaveResumeDTO) {
        userService.이력서작성(UserSaveResumeDTO, 1);
        return "redirect:/";


    }


    @PostMapping("/compinfo/update")
    public String compInfoUpdate(CompInfoUpdateDTO compInfoUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회사정보수정(compInfoUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "comp/comp_info";
    }

    @PostMapping("/comppw/update")
    public String compPWUpdate(UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser.getUserPassword().equals(userUpdateDTO.getNowPassword())) {
        } else {
            return Script.back("현재 비밀번호가 일치하지않습니다.");
        }
        if (userUpdateDTO.getNewPassword().equals(userUpdateDTO.getNewPasswordConfirm())) {
        } else {
            return Script.back("새로운 비밀번호가 일치하지않습니다.");

        }
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "redirect:/";

    }

}
