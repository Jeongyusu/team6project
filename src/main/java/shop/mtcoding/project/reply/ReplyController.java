package shop.mtcoding.project.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // comp_ 커뮤니티 댓글 작성
    // @SessionAttribute User sessionUser
    @PostMapping("comp/community/reply/save")
    public String compReplySave(ReplyRequest.ReplySaveDTO replySaveDTO) {
        replyService.댓글작성(1, replySaveDTO);
        return "redirect:/comp/community/board/" + replySaveDTO.getBoardId();
    }

    // user_ 커뮤니티 댓글 작성
    // @SessionAttribute User sessionUser
    @PostMapping("user/community/reply/save")
    public String userReplySave(ReplyRequest.ReplySaveDTO replySaveDTO) {
        replyService.댓글작성(1, replySaveDTO);
        return "redirect:/user/community/board/" + replySaveDTO.getBoardId();
    }

}
