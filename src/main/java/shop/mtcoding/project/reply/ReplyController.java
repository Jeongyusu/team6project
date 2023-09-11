package shop.mtcoding.project.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.user.User;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpSession session;

    //// comp_ 자바스크립트로 댓글등록
    @PostMapping("/api/comp/community/reply/save")
    public @ResponseBody ApiUtil<String> compReplySave(@RequestBody ReplyRequest.ReplySaveDTO replySaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyApiException("로그인 후 작성이 가능합니다.");
        }
        replyService.댓글작성(sessionUser.getId(), replySaveDTO);
        return new ApiUtil<String>(true, "댓글쓰기 성공");
    }

    //// user_ 자바스크립트로 댓글등록
    @PostMapping("/api/user/community/reply/save")
    public @ResponseBody ApiUtil<String> userReplySave(@RequestBody ReplyRequest.ReplySaveDTO replySaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyApiException("로그인 후 작성이 가능합니다.");
        }
        replyService.댓글작성(sessionUser.getId(), replySaveDTO);
        return new ApiUtil<String>(true, "댓글쓰기 성공");
    }

    ////////////////////////////////////////////////////////////////////////

    //// comp_ 커뮤니티 댓글 삭제
    @PostMapping("/comp/community/{id}/reply/delete")

    public String compReplyDelete(@PathVariable Integer id, @RequestParam Integer boardId) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 삭제가 가능합니다.");
        }
        replyService.댓글삭제(sessionUser.getId(), id);
        return "redirect:/comp/community/board/" + boardId;
    }

    //// user_ 커뮤니티 댓글 삭제
    @PostMapping("/user/community/{id}/reply/delete")
    public String userReplyDelete(@PathVariable Integer id, @RequestParam Integer boardId) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 삭제가 가능합니다.");
        }
        replyService.댓글삭제(sessionUser.getId(), id);
        return "redirect:/user/community/board/" + boardId;
    }
}