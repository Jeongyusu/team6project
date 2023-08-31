package shop.mtcoding.project.user;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class UserJoinDTO {
        
        private String userEmailId;
        private String userName;
        private String password;
        
    }

    @Getter
    @Setter
    public static class LoginDTO {

        private Integer userEmailId;

    }

    @Getter
    @Setter
    public static class CompJoinDTO {

        private String compEmailId;
        private String compName;
        private String password;
    }

}
