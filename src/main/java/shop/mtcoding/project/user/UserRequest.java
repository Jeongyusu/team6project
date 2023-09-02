package shop.mtcoding.project.user;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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
    public static class UserSaveResumeDTO {
        private String title;
        private String userName;
        private String userEmailId;
        
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birth;
    
        private String tel;
        private String address;
        private String subIntro;
        private MultipartFile resumePic;
        private List<String> positionList = new ArrayList<>();
        private List<String> skillList = new ArrayList<>();
        private String career;
        private String careerYear;
        private String edu;
        private String mainIntro;
        private String openCheck;
        private Timestamp createdAt;


    }

}
