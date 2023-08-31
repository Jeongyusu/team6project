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

    @GetMapping("/")
    public String home() {
        return "user_index";
    }

    @GetMapping("/comp")
    public String compHome() {
        return "user_index";
    }

    @GetMapping("/joinForm")
    public String userJoinForm() {
        return "user/user_join";
    }

    @PostMapping("/join")
    public @ResponseBody String userJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/", "회원가입 완료"); // Persistence Context초기화

    }

    @GetMapping("/comp/joinForm")
    public String compJoinForm() {
        return "comp/comp_join";
    }

    @PostMapping("/comp/join")
    public @ResponseBody String compJoin(UserRequest.CompJoinDTO compJoinDTO) {
        userService.기업회원가입(compJoinDTO);
        return Script.href("/", "회원가입 완료");// Persistence Context초기화

    }

    // @PostMapping("/login")
    // public @ResponseBody String login(UserRequest.LoginDTO loginDTO) {

    // User sessionUser = userService.로그인(loginDTO);
    // session.setAttribute("sessionUser", sessionUser);
    // return Script.href("/");
    // }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";

    }

}
