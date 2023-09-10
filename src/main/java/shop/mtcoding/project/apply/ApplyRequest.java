package shop.mtcoding.project.apply;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;

public class ApplyRequest {

    @Getter
    @Setter
    public static class ApplySaveDTO {

        private Integer selectedResumeId;
        private Integer selectedjobOpeningId;
    }

    @Getter
    @Setter
    public static class ApplyStateDTO {
        private String applyState;
        private Integer resumeId;
        private Integer JobOpeningId;
    }

}
