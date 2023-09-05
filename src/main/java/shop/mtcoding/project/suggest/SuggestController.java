package shop.mtcoding.project.suggest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;

@Controller
public class SuggestController {

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

    // @PostMapping("/userSuggest")
    // public String userSuggest(Model model) {
    // List<Resume> resumeList = resumeRepository.findAll();
    // model.addAttribute("resumeList", resumeList);
    // return null;
    // }
    // @GetMapping("/userSuggest")
    // public @ResponseBody Resume userSuggest(@PathVariable Integer id, Model
    // model) {
    // Resume resume = resumeRepository.findById(id).get();
    // model.addAttribute("resume", resume);
    // return resume;
    // }

}
