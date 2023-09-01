package shop.mtcoding.project.user;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class UserJoinDTO {

        private String userEmailId;
        private String userName;
        private String userPassword;
        private String compEmailId;
        private Integer gubun;

    }

    @Getter
    @Setter
    public static class UserLoginDTO {

        private String userEmailId;
        private String userPassword;
        private String compEmailId;
        private Integer gubun;

    }

}
