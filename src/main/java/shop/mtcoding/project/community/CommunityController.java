package shop.mtcoding.project.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // comp_ 커뮤니티 글 상세보기 화면
    @GetMapping("comp/community/board/{id}")
    public String compBoardDetailForm(@PathVariable Integer id, Model model) {
        Community community = communityService.상세게시물(id);
        model.addAttribute("community", community);
        return "comp/comp_community_detail";
    }

    // user_ 커뮤니티 글 상세보기 화면
    @GetMapping("user/community/board/{id}")
    public String userBoardDetailForm(@PathVariable Integer id, Model model) {
        Community community = communityService.상세게시물(id);
        model.addAttribute("community", community);
        return "user/user_community_detail";
    }

    // comp_ 커뮤니티 글 수정 화면
    @GetMapping("comp/community/board/{id}/updateForm")
    public String compBoardUpdateForm(@PathVariable Integer id, Model model) {
        Community community = communityService.게시물내용(id);
        model.addAttribute("community", community);
        return "comp/comp_community_update";
    }

    // user_ 커뮤니티 글 수정 화면
    @GetMapping("user/community/board/{id}/updateForm")
    public String userBoardUpdateForm(@PathVariable Integer id, Model model) {
        Community community = communityService.게시물내용(id);
        model.addAttribute("community", community);
        return "user/user_community_update";
    }

    // comp_ 커뮤니티 글 수정
    @PostMapping("comp/community/board/{id}/update")
    public String compBoardUpdate(@PathVariable Integer id, @SessionAttribute User sessionUser,
            CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        communityService.게시물수정(sessionUser.getId(), id, boardUpdateDTO);
        return "redirect:/comp/community/board/" + id;
    }

    // user_ 커뮤니티 글 수정
    @PostMapping("user/community/board/{id}/update")
    public String userBoardUpdate(@PathVariable Integer id, @SessionAttribute User sessionUser,
            CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        communityService.게시물수정(sessionUser.getId(), id, boardUpdateDTO);
        return "redirect:/user/community/board/" + id;
    }

}
