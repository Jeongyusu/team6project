package shop.mtcoding.project.suggest;

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
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.suggest.SuggestRequest.SuggestSaveDTO;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}/resume/detail")
    public String userOpenResumeDetail(@PathVariable Integer id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        model.addAttribute("resume", resume);

        return "user/user_resume_detail";
    }

    @PostMapping("/comp/suggest")
    public @ResponseBody String UserSuggest(SuggestSaveDTO suggestSaveDTO, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        suggestService.제안(suggestSaveDTO, user.getId());
        model.addAttribute("sessionUser", sessionUser);
        Integer id = suggestSaveDTO.getSelectedResumeId();
        return Script.href("/comp/resume/" + id, "제안 완료");
    }

    @PostMapping("/api/suggest/answer/update")
    public @ResponseBody ApiUtil<String> AnswerSuggest(@RequestBody SuggestRequest.SuggestStateDTO suggestStateDTO,
            Model model) {

        suggestService.제안응답(suggestStateDTO);
        return new ApiUtil<String>(true, suggestStateDTO.getSugState());

    }

}
