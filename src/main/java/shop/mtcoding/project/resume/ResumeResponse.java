package shop.mtcoding.project.resume;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ResumeResponse {

    @Getter
    @Setter
    @Builder
    public static class ResumeCareerAndEduResponseDTO {
        private String career;
        private String careerYear;
        private String edu;
        private String openCheck;
    }

    // 이력서 등록날짜 포맷을 위한 DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ApplyResumeInJobOpeningDTO {
        private Integer resumeId;
        private String userTel;
        private String resumeTitle;
        private String createdAtFormat;

        @Builder
        public ApplyResumeInJobOpeningDTO(Integer resumeId, String userTel, String resumeTitle,
                String createdAtFormat) {
            this.resumeId = resumeId;
            this.userTel = userTel;
            this.resumeTitle = resumeTitle;
            this.createdAtFormat = createdAtFormat;
        }
    }

    // 공고 상세보기 팝업창 속 이력서 for문을 위한 DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ResumeInJobOpeningDTO {

        private String userEmail;
        private List<ApplyResumeInJobOpeningDTO> applyResumeInJobOpeningDTO;
        private Integer totalResume;

        @Builder
        public ResumeInJobOpeningDTO(String userEmail, List<ApplyResumeInJobOpeningDTO> applyResumeInJobOpeningDTO,
                Integer totalResume) {
            this.userEmail = userEmail;
            this.applyResumeInJobOpeningDTO = applyResumeInJobOpeningDTO;
            this.totalResume = totalResume;
        }
    }

}