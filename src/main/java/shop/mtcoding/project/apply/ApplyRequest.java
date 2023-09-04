package shop.mtcoding.project.apply;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;

public class ApplyRequest {

    @Getter
    @Setter
    public static class ApplySaveDTO {
        private Integer applyId;
        private String compName;
        private String jobOpeningTitle;
        private String resumeTitle;
        // position도 받아오기
        private Integer selectedResumeId;
        private Integer selectedjobOpeningId;
    }
}
