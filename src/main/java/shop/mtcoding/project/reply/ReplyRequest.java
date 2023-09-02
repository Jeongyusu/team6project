package shop.mtcoding.project.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyRequest {

    @Getter
    @Setter
    public static class ReplySaveDTO {

        private Integer boardId;
        private String comment;

    }

}
