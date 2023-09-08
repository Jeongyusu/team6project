package shop.mtcoding.project.jobopening;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JobOpeningResponse {

    // 메인화면
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

        @Builder
        public JobOpeningMainDTO(Integer jobOpeningId, String title, String compName, String compAddress, String career,
                String careerYear, String skill) {
            this.jobOpeningId = jobOpeningId;
            this.title = title;
            this.compName = compName;
            this.compAddress = compAddress;
            this.career = career;
            this.careerYear = careerYear;
            this.skill = skill;
        }
    }
}
