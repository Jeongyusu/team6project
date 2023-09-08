package shop.mtcoding.project.resume;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ResumeRequest {

    @Getter
    @Setter
    @ToString
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

    @Getter
    @Setter
    public static class UserUpdateResumeDTO {

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

    @Getter
    @Setter
    public static class CompUserOpenResumeDTO {

        private Integer resumeId;
        private String title;
        private String userName;
        private String address;
        private String resumePic;
        private String userSkillList;
        private String career;
        private String careerYear;
        private String openCheck;

        @Builder
        public CompUserOpenResumeDTO(Integer resumeId, String title, String userName, String address,
                String resumePic, String userSkillList, String career, String careerYear,
                String openCheck) {
            this.resumeId = resumeId;
            this.title = title;
            this.userName = userName;
            this.address = address;
            this.resumePic = resumePic;
            this.userSkillList = userSkillList;
            this.career = career;
            this.careerYear = careerYear;
            this.openCheck = openCheck;
        }

    }

}