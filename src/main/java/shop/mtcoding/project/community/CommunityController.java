package shop.mtcoding.project.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import shop.mtcoding.project.user.User;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    // comp_ 커뮤니티 글 등록 화면
    @GetMapping("comp/community/board/saveForm")
    public String compBoardSaveForm() {
        return "comp/comp_community_write";
    }

    // user_ 커뮤니티 글 등록 화면
    @GetMapping("user/community/board/saveForm")
    public String userBoardSaveForm() {
        return "user/user_community_write";
    }

    // comp_ 커뮤니티 글 작성
    @PostMapping("comp/community/board/save")
    public String compBoardSave(@SessionAttribute User sessionUser, CommunityRequest.BoardSaveDTO boardSaveDTO) {
        communityService.게시물작성(sessionUser.getId(), boardSaveDTO);
        return "redirect:/comp/community";
    }

    // user_ 커뮤니티 글 작성
    @PostMapping("user/community/board/save")
    public String userBoardSave(@SessionAttribute User sessionUser, CommunityRequest.BoardSaveDTO boardSaveDTO) {
        communityService.게시물작성(sessionUser.getId(), boardSaveDTO);
        return "redirect:/user/community";
    }
}
