package shop.mtcoding.project.scrap;

import lombok.Getter;
import lombok.Setter;

public class UserScrapRequest {

    // 유저 -> 채용공고 스크랩 등록 DTO
    @Getter
    @Setter
    public static class UserScrapDTO {
        private Integer jobOpeningId;
    }

    // 유저 -> 채용공고 스크랩 삭제 DTO
    @Getter
    @Setter
    public static class UserScrapDeleteDTO {
        private Integer jobOpeningId;

    }
}