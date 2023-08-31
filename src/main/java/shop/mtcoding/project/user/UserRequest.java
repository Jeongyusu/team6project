package shop.mtcoding.project.user;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class UserUpdateDTO {
        // 9/2까지 해야할것
        // 현재비번값과 newPassword & newPasswordConfirm이 같아야 바뀌게기능구현하기
        private String newPassword;
    }

}
