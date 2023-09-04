package shop.mtcoding.project.resume;

import lombok.Builder;
import lombok.Getter;
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

}
