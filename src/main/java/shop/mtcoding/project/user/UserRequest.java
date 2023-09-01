package shop.mtcoding.project.user;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class UserUpdateDTO {
        // 9/2까지 해야할것
        // 현재비번값과 newPassword & newPasswordConfirm이 같아야 바뀌게기능구현하기
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
    public static class CompUpdateDTO {
        private MultipartFile compPic;
        private Date compDate;
        private String compExplan;
    }

    ///////////////////////////////////////////////////////////

    @Getter
    @Setter
    public static class UserLoginDTO {
        private String userEmailId;
        private String userPassword;
        private String compEmailId;
    }
}
