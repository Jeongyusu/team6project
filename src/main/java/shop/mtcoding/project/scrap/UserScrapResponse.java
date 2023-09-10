package shop.mtcoding.project.scrap;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserScrapResponse {

    @Getter
    @Setter
    public static class ScrapJobOpeningDTO {
        private Integer jobOpeningId;
        private String compName;
        private String title;
        private String edu;
        private String skillName;
        private String compPicUrl;
        private String dDayDeadLine;

        @Builder
        public ScrapJobOpeningDTO(Integer jobOpeningId, String compName, String title, String edu, String skillName,
                String compPicUrl, String dDayDeadLine) {
            this.jobOpeningId = jobOpeningId;
            this.compName = compName;
            this.title = title;
            this.edu = edu;
            this.skillName = skillName;
            this.compPicUrl = compPicUrl;
            this.dDayDeadLine = dDayDeadLine;
        }
    }

}
