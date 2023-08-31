package shop.mtcoding.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/")
    // public String home() {
    // return "comp/comp_join";

    // }

    @PostMapping("/user/update")
    public String userUpdate(@PathVariable Integer id, UserRequest.UserUpdateDTO userUpdateDTO) {
        User user = userService.회원정보수정(userUpdateDTO, id);
        return "user/user_mypage";
    }
}
