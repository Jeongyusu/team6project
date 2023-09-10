package shop.mtcoding.project.apply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.apply.ApplyRequest.ApplySaveDTO;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.suggest.Suggest;
import shop.mtcoding.project.suggest.SuggestQueryRepository;
import shop.mtcoding.project.suggest.SuggestRepository;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class ApplyController {
    @Autowired
    private HttpSession session;
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private SuggestQueryRepository suggestQueryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SuggestRepository suggestRepository;

    @GetMapping("/user/jobOpening/{id}/applyForm")
    public String ApplyList(@PathVariable Integer id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        JobOpening jobOpening = jobOpeningRepository.findById(id).get();
        model.addAttribute("jobOpening", jobOpening);

        List<Apply> applyList = applyRepository.findAll();
        model.addAttribute("applyList", applyList);
        List<Apply> applyList2 = applyRepository.findByUserId(sessionUser.getId());
        int totalApply = applyList2.size();
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList2", applyList2);

        List<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("resumeList", resumeList);
        List<Resume> resumeList2 = resumeRepository.findByUserId(sessionUser.getId());
        int totalResume = resumeList2.size();
        model.addAttribute("totalResume", totalResume);
        model.addAttribute("resumeList", resumeList2);

        return "user/user_job_opening_apply";
    }
    // @GetMapping("/user/jobOpening/{id}/applyForm")
    // public @ResponseBody List<Resume> ApplyList(@PathVariable Integer id, Model
    // model) {
    // User sessionUser = (User) session.getAttribute("sessionUser");

    // JobOpening jobOpening = jobOpeningRepository.findById(id).get();
    // model.addAttribute("jobOpening", jobOpening);
    // List<Apply> applyList = applyRepository.findAll();
    // model.addAttribute("applyList", applyList);

    // List<Resume> resumeList = resumeRepository.findAll();
    // int totalResume = resumeList.size();
    // model.addAttribute("totalResume", totalResume);
    // model.addAttribute("resumeList", resumeList);
    // List<Resume> resumeList2 =
    // resumeRepository.findByUserId(sessionUser.getId());
    // model.addAttribute("resumeList", resumeList2);

    // return resumeList;
    // }

    @PostMapping("/userApply")
    public String UserApply(ApplySaveDTO applySaveDTO, Model model) {
        applyService.지원(applySaveDTO);
        Integer id = applySaveDTO.getSelectedjobOpeningId();
        return "redirect:/user/jobOpening/" + id + "/applyForm";
    }

    // @GetMapping("/compMyPageForm")
    // public String jobOpeningList(Model model) {
    // List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
    // int totalJopOpeningList = jobOpeningList.size();
    // model.addAttribute("totalJopOpeningList", totalJopOpeningList);
    // model.addAttribute("jobOpeningList", jobOpeningList);
    // return "comp/comp_info";
    // }

    @GetMapping("/user/applyAndSuggest")
    public String userApplyAndSuggest(Model model) {
        // User sessionUser = (User) session.getAttribute("sessionUser");
        // User user = userRepository.findById(sessionUser.getId()).get();
        User user = userRepository.findById(1).get();

        List<Resume> resumeList = resumeRepository.findByUserId(user.getId());
        model.addAttribute("resumeList", resumeList);
        int totalResume = resumeList.size();
        List<Apply> applyList = applyRepository.findByUserId(user.getId());
        int totalApply = applyList.size();
        List<Suggest> suggestList = suggestRepository.findBySuggestUserId(user.getId());
        model.addAttribute("suggestList", suggestList);
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList", applyList);
        model.addAttribute("totalResume", totalResume);

        return "user/user_resume_management";
    }

    // @GetMapping("/comp/applyAndSuggest")
    // public String compApplyAndSuggest(Model model) {
    //     // User sessionUser = (User) session.getAttribute("sessionUser");
    //     // User user = userRepository.findById(sessionUser.getId()).get();
    //     User user = userRepository.findById(4).get();

    //     List<Resume> resumeList = resumeRepository.findByUserIdofJobOpeningList(user.getId());
    //     model.addAttribute("resumeList", resumeList);
    //     int totalResume = resumeList.size();
    //     List<Apply> applyList = applyRepository.findByUserId(user.getId());
    //     int totalApply = applyList.size();
    //     List<Suggest> suggestList = suggestRepository.findBySuggestUserId(user.getId());
    //     model.addAttribute("suggestList", suggestList);
    //     model.addAttribute("totalApply", totalApply);
    //     model.addAttribute("applyList", applyList);
    //     model.addAttribute("totalResume", totalResume);
    //     return "comp/comp_info";
    // }


}
