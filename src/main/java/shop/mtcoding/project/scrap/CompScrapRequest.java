package shop.mtcoding.project.scrap;

import lombok.Getter;
import lombok.Setter;

public class CompScrapRequest {

    // 기업 -> 이력서 스크랩 등록 DTO
    @Getter
    @Setter
    public static class CompScrapSaveDTO {
        private Integer resumeId;
    }

    // 기업 -> 이력서 스크랩 삭제 DTO
    @Getter
    @Setter
    public static class CompScrapDeleteDTO {
        private Integer resumeId;

    }
}
