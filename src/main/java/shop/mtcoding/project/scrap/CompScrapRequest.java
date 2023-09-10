package shop.mtcoding.project.scrap;

import lombok.Getter;
import lombok.Setter;

public class CompScrapRequest {
    @Getter
    @Setter
    public static class CompScrapDTO {
        private Integer resumeId;
    }

    @Getter
    @Setter
    public static class CompScrapDeleteDTO {
        private Integer resumeId;

    }
}
