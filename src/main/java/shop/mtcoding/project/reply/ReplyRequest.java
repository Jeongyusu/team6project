package shop.mtcoding.project.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyRequest {

    // reply 등록 DTO
    @Getter
    @Setter
    public static class ReplySaveDTO {

        private Integer boardId;
        private String comment;

    }

}