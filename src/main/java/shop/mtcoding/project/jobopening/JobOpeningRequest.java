package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.project.user.User;

public class JobOpeningRequest {

    @Getter
    @Setter
    public static class JobOpeningSaveDTO {
        private String title;
        private String career;
        private String careerYear;
        private String edu;
        private String compAddress;
        private String process; // 채용절차
        private List<String> positionList = new ArrayList<>();
        private List<String> skillList = new ArrayList<>();
        private List<String> qualList = new ArrayList<>(); // 자격 요건
        private List<String> taskList = new ArrayList<>(); // 주요 업무
        private User user;
        private String deadLine;
    }

    @Getter
    @Setter
    public static class JobOpeningUpdateDTO {
        private Integer id;
        private String title;
        private String career;
        private String careerYear;
        private String edu;
        private String compAddress;
        private String process; // 채용절차
        private List<String> positionList = new ArrayList<>();
        private List<String> skillList = new ArrayList<>();
        private List<String> qualList = new ArrayList<>(); // 자격 요건
        private List<String> taskList = new ArrayList<>(); // 주요 업무
        private User user;
    }

    @Getter
    @Setter
    public static class CompRecommendDTO {

        private Integer jobOpeningId;
        private String title;
        private String userName;
        private String address;
        private String jobOpeingPic;
        private String compSkillList;
        private String career;
        private String careerYear;

        @Builder
        public CompRecommendDTO(Integer jobOpeningId, String title, String userName, String address,
                String jobOpeingPic, String compSkillList, String career, String careerYear) {
            this.jobOpeningId = jobOpeningId;
            this.title = title;
            this.userName = userName;
            this.address = address;
            this.jobOpeingPic = jobOpeingPic;
            this.compSkillList = compSkillList;
            this.career = career;
            this.careerYear = careerYear;
        }

    }
}
