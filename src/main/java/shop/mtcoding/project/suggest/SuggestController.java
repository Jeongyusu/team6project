package shop.mtcoding.project.suggest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.suggest.SuggestRequest.SuggestSaveDTO;
import shop.mtcoding.project.user.User;

@Controller
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/openResumeList")
    public String OpenResumeList(Model model) {
        List<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("resumeList", resumeList);

        return "comp/comp_user_open_resume";
    }

    @PostMapping("/userSuggest")
    public String UserSuggest(SuggestSaveDTO suggestSaveDTO, Model model) {
        suggestService.제안(suggestSaveDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);
        Integer id = suggestSaveDTO.getSelectedResumeId();
        return "redirect:/user/" + id + "/resume/detail";
    }

    ///////// 유저 제안 수락
    @PostMapping("/api/{resumeId}/suggestAccept")
    public @ResponseBody ApiUtil<String> userSuggestAccept(@PathVariable Integer resumeId,
            @RequestBody SuggestRequest.SuggestStateDTO suggestStateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        suggestService.제안수락(suggestStateDTO, resumeId);
        return new ApiUtil<String>(true, "수락하기완료");
    }

    // @PostMapping("/user/suggest/accept")
    // public @ResponseBody String userSuggestAccept(SuggestRequest.SuggestStateDTO
    // suggestStateDTO, Model model) {
    // User sessionUser = (User) session.getAttribute("sessionUser");
    // suggestService.제안수락(suggestStateDTO, sessionUser.getId());
    // return "/user/myPageForm";
    // }

}
