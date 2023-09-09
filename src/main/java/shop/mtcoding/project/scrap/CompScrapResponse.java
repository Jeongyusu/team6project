package shop.mtcoding.project.scrap;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CompScrapResponse {

    @Getter
    @Setter
    public static class ScrapResumeDTO {

        private Integer resumeId;
        private String userName;
        private String title;
        private String edu;
        private String skillName;

        @Builder
        public ScrapResumeDTO(Integer resumeId, String userName, String title, String edu, String skillName) {
            this.resumeId = resumeId;
            this.userName = userName;
            this.title = title;
            this.edu = edu;
            this.skillName = skillName;
        }
    }
}
