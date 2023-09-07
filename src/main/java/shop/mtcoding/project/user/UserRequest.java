package shop.mtcoding.project.user;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;
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

        @Getter
        @Setter
        public static class UserUpdateDTO {
            private String nowPassword;
            private String newPassword;
            private String newPasswordConfirm;
        }

        @Getter
        @Setter
        public static class UserPicUpdateDTO {
            private MultipartFile userPic;

        }

        @Getter
        @Setter
        public static class UserLoginDTO {
            private String userEmailId;
            private String userPassword;
            private String compEmailId;
            private Integer gubun;

        }

        @Getter
        @Setter
        public static class CompInfoUpdateDTO {
            private MultipartFile compPic;
            private Date compDate;
            private String compExplan;
        }

    }

}