package shop.mtcoding.project.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ReplyResponse {

    // board 상세 속 댓글 DTO - 날짜포맷
    @Getter
    @Setter
    public static class ReplyDetailDTO {

        private Integer replyId;
        private String comment;
        private String replyUserName;
        private String replyFormatDate;
        private boolean replyOwner;

        @Builder
        public ReplyDetailDTO(Integer replyId, String comment, String replyUserName, String replyFormatDate,
                boolean replyOwner) {
            this.replyId = replyId;
            this.comment = comment;
            this.replyUserName = replyUserName;
            this.replyFormatDate = replyFormatDate;
            this.replyOwner = replyOwner;
        }

    }

}