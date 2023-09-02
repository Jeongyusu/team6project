package shop.mtcoding.project;

import org.junit.jupiter.api.Test;

import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserService;
import shop.mtcoding.project.user.UserRequest.UserJoinDTO.UserUpdateDTO;


public class UserUpdateTest {
    private UserService userService;

    @Test
    public void test정보수정() {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User updateUser = userService.회원정보수정(userUpdateDTO, 1);

    }
}
