package shop.mtcoding.project.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ReplyResponse {

    // 게시글 상세보기 > 댓글 - 날짜포맷
    @Getter
    @Setter
    public static class ReplyDetailDTO {

        private Integer replyId;
        private String comment;
        private String replyUserName;
        private String replyFormatDate;

        @Builder
        public ReplyDetailDTO(Integer replyId, String comment, String replyUserName, String replyFormatDate) {
            this.replyId = replyId;
            this.comment = comment;
            this.replyUserName = replyUserName;
            this.replyFormatDate = replyFormatDate;
        }
    }

}