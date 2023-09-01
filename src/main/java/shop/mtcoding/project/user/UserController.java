package shop.mtcoding.project.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.Script;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/user")
    public String home() {
        return "user_index";
    }

    @GetMapping("/comp")
    public String compHome() {
        return "user_index";
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
    public @ResponseBody String userJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/user", "회원가입 완료");

    }

    @PostMapping("/comp/join")
    public @ResponseBody String compJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/comp", "회원가입 완료");
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
    public @ResponseBody String userLogin(UserRequest.UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/user", "로그인 완료");
    }

    @PostMapping("/comp/login")
    public @ResponseBody String compLogin(UserRequest.UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/comp", "로그인 완료");
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "user/user_mypage";
    }

}
