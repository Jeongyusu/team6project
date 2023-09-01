package shop.mtcoding.project.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.Script;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "user_index";
    }

    @GetMapping("/mypageForm")
    public String mypage() {
        return "user/user_mypage";
    }

    @GetMapping("/userResumeWriteForm")
    public String UserResumeWrite() {
        return "user/user_resume_write";
    }

    @PostMapping("/user/update")
    public String userUpdate(UserRequest.UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        System.out.println("테스트1" + sessionUser.getUserPassword());
        System.out.println("테스트2" + userUpdateDTO.getNowPassword());

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
    public String userPicUpdate(UserRequest.UserPicUpdateDTO userPicUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.유저사진수정(userPicUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "user/user_mypage";
    }

    @PostMapping("/comp/update")
    public String compUpdate(Integer id, UserRequest.CompUpdateDTO compUpdateDTO, Model model) {
        User user = userService.회사정보수정(compUpdateDTO, 4);
        model.addAttribute("user", user);
        return "comp/comp_info";
    }

    /////////////////////////////////////////////////////////////////////////////

    @GetMapping("/user/loginForm")
    public String userLoginForm() {
        return "user/user_login";

    }

    @PostMapping("/user/login")
    public @ResponseBody String userLogin(UserRequest.UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/user", "로그인 완료");
    }

    @GetMapping("/user")
    public String home1() {
        return "user_index";
    }

}
