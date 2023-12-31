package shop.mtcoding.project.community;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.reply.ReplyResponse.ReplyDetailDTO;

public class CommunityResponse {

    // board 목록 DTO - 날짜포맷
    @NoArgsConstructor
    @Getter
    @Setter
    public static class BoardListDTO {

        private Integer boardId;
        private String title;
        private String content;
        private String boardUserName;
        private String boardFormatDate;

        @Builder
        public BoardListDTO(Integer boardId, String title, String content, String boardUserName,
                String boardFormatDate) {
            this.boardId = boardId;
            this.title = title;
            this.content = content;
            this.boardUserName = boardUserName;
            this.boardFormatDate = boardFormatDate;
        }
    }

    // board 상세 DTO - 날짜포맷
    @NoArgsConstructor
    @Setter
    @Getter
    public static class BoardDetailDTO {

        private Integer boardId;
        private String title;
        private String content;
        private String boardUserName;
        private String boardFormatDate;

        private List<ReplyDetailDTO> replyList;
        private boolean boardOwner;

        @Builder
        public BoardDetailDTO(Integer boardId, String title, String content, String boardUserName,
                String boardFormatDate, List<ReplyDetailDTO> replyList, boolean boardOwner) {
            this.boardId = boardId;
            this.title = title;
            this.content = content;
            this.boardUserName = boardUserName;
            this.boardFormatDate = boardFormatDate;
            this.replyList = replyList;
            this.boardOwner = boardOwner;
        }
    }

}
