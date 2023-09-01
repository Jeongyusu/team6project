package shop.mtcoding.project.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    // comp_ 커뮤니티 홈 화면
    @GetMapping("comp/community")
    public String compCommunity(@RequestParam(defaultValue = "0") Integer page, String keyword, Model model) {
        Page<Community> communityPG = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            communityPG = communityService.게시물목록보기(page);
        } else {
            communityPG = communityService.검색후게시물목록보기(page, keyword);
        }
        model.addAttribute("communityPG", communityPG);
        model.addAttribute("prev", communityPG.getNumber() - 1);
        model.addAttribute("next", communityPG.getNumber() + 1);
        return "comp/comp_community_list";
    }

    // user_ 커뮤니티 홈 화면
    @GetMapping("user/community")
    public String userCommunity(@RequestParam(defaultValue = "0") Integer page, String keyword, Model model) {
        Page<Community> communityPG = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            communityPG = communityService.게시물목록보기(page);
        } else {
            communityPG = communityService.검색후게시물목록보기(page, keyword);
        }
        model.addAttribute("communityPG", communityPG);
        model.addAttribute("prev", communityPG.getNumber() - 1);
        model.addAttribute("next", communityPG.getNumber() + 1);
        return "user/user_community_list";
    }
}
