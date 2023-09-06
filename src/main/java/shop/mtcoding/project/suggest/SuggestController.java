package shop.mtcoding.project.suggest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/user/{id}/resume/detail")
    public String userOpenResumeDetail(@PathVariable Integer id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        model.addAttribute("resume", resume);

        return "user/user_resume_detail";
    }

    @GetMapping("/openResumeList")
    public String OpenResumeList(Model model) {
        List<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("resumeList", resumeList);

        return "comp/comp_user_open_resume";
    }
    // @GetMapping("/openResumeList")
    // public @ResponseBody List<Resume> OpenResumeList(Model model) {
    // List<Resume> resumeList = resumeRepository.findAll();
    // model.addAttribute("resumeList", resumeList);

    // return resumeList;
    // }

    @PostMapping("/userSuggest")
    public String UserSuggest(SuggestSaveDTO suggestSaveDTO, Model model) {
        suggestService.제안(suggestSaveDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);
        Integer id = suggestSaveDTO.getSelectedResumeId();
        return "redirect:/user/" + id + "/resume/detail";
    }

    // @GetMapping("/userSuggest")
    // public @ResponseBody List<Resume> userSuggest(Model model) {
    // List<Resume> resumeList = resumeRepository.findAll();
    // model.addAttribute("resumeList", resumeList);
    // User sessionUser = (User) session.getAttribute("sessionUser");
    // model.addAttribute("sessionUser", sessionUser);
    // return resumeList;
    // }

}
