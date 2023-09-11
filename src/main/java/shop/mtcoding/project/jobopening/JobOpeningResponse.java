package shop.mtcoding.project.jobopening;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.position.PositionResponse.PositionNameDTO;
import shop.mtcoding.project.qualified.QualifiedResponse.QualifiedContentDTO;
import shop.mtcoding.project.skill.SkillResponse.SkillNameDTO;
import shop.mtcoding.project.task.TaskResponse.TaskContentDTO;

public class JobOpeningResponse {

    // 메인화면 DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class JobOpeningMainDTO {

        private Integer jobOpeningId;
        private String title;
        private String compName;
        private String compAddress;
        private String career;
        private String careerYear;
        private String skill;
        private String compPicUrl;

        @Builder
        public JobOpeningMainDTO(Integer jobOpeningId, String title, String compName, String compAddress, String career,
                String careerYear, String skill, String compPicUrl) {
            this.jobOpeningId = jobOpeningId;
            this.title = title;
            this.compName = compName;
            this.compAddress = compAddress;
            this.career = career;
            this.careerYear = careerYear;
            this.skill = skill;
            this.compPicUrl = compPicUrl;
        }
    }

    // 공고 상세보기 DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class JobOpeningDetailDTO {

        private Integer jobOpeningId;
        private String title;
        private String process;
        private String career;
        private String careerYear;
        private String edu;
        private String compName;
        private String compAddress;
        private String compIntro;
        private String compPicUrl;
        private String compFormatCreatedAt;

        private LocalDate deadLine;
        private Timestamp createdAt;

        private List<TaskContentDTO> taskList = new ArrayList<>();
        private List<QualifiedContentDTO> qulifiedList = new ArrayList<>();
        private List<SkillNameDTO> requiredSkillList = new ArrayList<>();
        private List<PositionNameDTO> requiredPositionList = new ArrayList<>();

        @Builder
        public JobOpeningDetailDTO(Integer jobOpeningId, String title, String process, String career, String careerYear,
                String edu, String compName, String compAddress, String compIntro, String compPicUrl,
                String compFormatCreatedAt, LocalDate deadLine, Timestamp createdAt, List<TaskContentDTO> taskList,
                List<SkillNameDTO> requiredSkillList, List<PositionNameDTO> requiredPositionList,
                List<QualifiedContentDTO> qulifiedList) {
            this.jobOpeningId = jobOpeningId;
            this.title = title;
            this.process = process;
            this.career = career;
            this.careerYear = careerYear;
            this.edu = edu;
            this.compName = compName;
            this.compAddress = compAddress;
            this.compIntro = compIntro;
            this.compPicUrl = compPicUrl;
            this.compFormatCreatedAt = compFormatCreatedAt;
            this.deadLine = deadLine;
            this.createdAt = createdAt;
            this.taskList = taskList;
            this.requiredSkillList = requiredSkillList;
            this.requiredPositionList = requiredPositionList;
            this.qulifiedList = qulifiedList;
        }
    }
}
