package shop.mtcoding.project.resume;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.apply.Apply;
import shop.mtcoding.project.apply.ApplyRepository;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.position.WishPositionService;
import shop.mtcoding.project.resume.ResumeRequest.UserSaveResumeDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserUpdateResumeDTO;
import shop.mtcoding.project.skill.SkillRequest.MySkill;
import shop.mtcoding.project.suggest.Suggest;
import shop.mtcoding.project.suggest.SuggestRepository;
import shop.mtcoding.project.user.User;

@Controller
public class ResumeController {

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    ResumeService resumeService;

    @Autowired
    WishPositionService wishPositionService;

    @Autowired
    HttpSession session;

    @GetMapping("/user/resume/saveForm")
    public String resumeSaveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/login";
        }
        return "user/user_resume_write";
    }

    @PostMapping("/user/resume/save")
    public String saveResume(UserSaveResumeDTO userSaveResumeDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서작성(userSaveResumeDTO, sessionUser.getId());
        return "redirect:/userMyPageForm";

    }

    @GetMapping("/user/resume/{id}/updateForm")
    public String resumeUpdateForm(@PathVariable Integer id, Model model) {
        Resume resume = resumeService.업데이트폼(id);
        model.addAttribute("resume", resume);
        return "user/user_resume_update";
    }

    @PostMapping("/user/resume/{id}/update")
    public String updateResume(@PathVariable Integer id, UserUpdateResumeDTO userUpdateResumeDTO) {
        resumeService.이력서수정(userUpdateResumeDTO, id);
        return "redirect:/";
    }

    @GetMapping("/api/getSkillList")
    public List<MySkill> getSkillList(@RequestParam("resumeId") Integer resumeId) {
        List<MySkill> mySkillList = wishPositionService.이력서스킬상태복원(resumeId);
        return mySkillList;

    }

    @GetMapping("/{id}/userMyPageForm")
    public String resumeList(@PathVariable Integer id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<Apply> applyList = applyRepository.findAll();
        model.addAttribute("applyList", applyList);
        List<Apply> applyList2 = applyRepository.findByResumeUserId(sessionUser.getId());
        int totalApply = applyList2.size();
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList2", applyList2);

        List<Suggest> suggestList = suggestRepository.findAll();
        int totalSuggestList = suggestList.size();
        model.addAttribute("suggestList", suggestList);
        model.addAttribute("totalSuggestList", totalSuggestList);

        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        model.addAttribute("jobOpeningList", jobOpeningList);

        List<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("resumeList", resumeList);
        List<Resume> resumeList2 = resumeRepository.findByUserId(sessionUser.getId());
        int totalResume = resumeList2.size();
        model.addAttribute("totalResume", totalResume);
        model.addAttribute("resumeList", resumeList2);

        return "user/user_mypage";

    }
    // @GetMapping("/{id}/userMyPageForm")
    // public @ResponseBody List<Apply> resumeList(@PathVariable Integer id, Model
    // model) {
    // User sessionUser = (User) session.getAttribute("sessionUser");

    // List<Apply> applyList = applyRepository.findAll();
    // model.addAttribute("applyList", applyList);
    // List<Apply> applyList2 =
    // applyRepository.findByResumeUserId(sessionUser.getId());
    // int totalApply = applyList2.size();
    // model.addAttribute("totalApply", totalApply);
    // model.addAttribute("applyList2", applyList2);

    // List<Suggest> suggestList = suggestRepository.findAll();
    // int totalSuggestList = suggestList.size();
    // model.addAttribute("suggestList", suggestList);
    // model.addAttribute("totalSuggestList", totalSuggestList);

    // List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
    // model.addAttribute("jobOpeningList", jobOpeningList);

    // List<Resume> resumeList = resumeRepository.findAll();
    // model.addAttribute("resumeList", resumeList);
    // List<Resume> resumeList2 =
    // resumeRepository.findByUserId(sessionUser.getId());
    // int totalResume = resumeList2.size();
    // model.addAttribute("totalResume", totalResume);
    // model.addAttribute("resumeList", resumeList2);

    // return applyList2;

    // }

}
