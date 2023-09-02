package shop.mtcoding.project.jobopening;

import java.util.List;

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
        private List<String> position;
        private List<String> skill;
        private List<String> qual; // 자격 요건
        private List<String> task; // 주요 업무
        private User user;
        private String deadLine;
    }
}
