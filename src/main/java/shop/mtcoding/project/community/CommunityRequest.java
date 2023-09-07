package shop.mtcoding.project.community;

import lombok.Getter;
import lombok.Setter;

public class CommunityRequest {
    // 글 등록 DTO
    @Getter
    @Setter
    public static class BoardSaveDTO {

        private String title;
        private String content;

    }

    // 글 수정 DTO
    @Getter
    @Setter
    public static class BoardUpdateDTO {

        private String title;
        private String content;

    }
}
