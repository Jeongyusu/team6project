package shop.mtcoding.project.suggest;

import lombok.Getter;
import lombok.Setter;

public class SuggestRequest {

    @Getter
    @Setter
    public static class SuggestSaveDTO {
        private Integer selectedResumeId;
        private Integer jobOpeningId;
        private Integer selectedUserId;
    }

    @Getter
    @Setter
    public static class SuggestStateDTO {
        private String sugState;
        private Integer resumeId;
        private Integer JobOpeningId;
    }

}
