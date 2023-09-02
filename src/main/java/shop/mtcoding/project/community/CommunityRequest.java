package shop.mtcoding.project.community;

import lombok.Getter;
import lombok.Setter;

public class CommunityRequest {
    @Getter
    @Setter
    public static class BoardSaveDTO {

        private String title;
        private String content;

    }

    @Getter
    @Setter
    public static class BoardUpdateDTO {

        private String title;
        private String content;

    }
}
