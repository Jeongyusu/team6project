package shop.mtcoding.project.apply;

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
import shop.mtcoding.project.apply.ApplyRequest.ApplySaveDTO;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.suggest.Suggest;
import shop.mtcoding.project.suggest.SuggestQueryRepository;
import shop.mtcoding.project.suggest.SuggestRepository;
import shop.mtcoding.project.suggest.SuggestRequest;
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

    @PostMapping("/user/Apply")
    public String userApply(ApplySaveDTO applySaveDTO) {
        applyService.지원(applySaveDTO);
        Integer id = applySaveDTO.getSelectedjobOpeningId();
        return "redirect:/user/jobOpening/" + id + "/applyForm";
    }

    @GetMapping("/user/applyAndSuggest")
    public String userApplyAndSuggest(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        List<Apply> applyList = applyRepository.findByUserId(user.getId());
        int totalApply = applyList.size();
        List<Suggest> suggestList = suggestRepository.findBySuggestUserId(user.getId());
        int totalSuggest = suggestList.size();
        model.addAttribute("suggestList", suggestList);
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList", applyList);
        model.addAttribute("totalSuggest", totalSuggest);

        return "user/user_resume_management";
    }

    @GetMapping("/comp/applyAndSuggest")
    public String compApplyAndSuggest(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        List<Apply> applyList = applyRepository.findByUserIdofJobOpening(user.getId());
        int totalApply = applyList.size();
        List<Suggest> suggestList = suggestRepository.findByUserIdofJobOpeningInSuggest(user.getId());
        int totalSuggest = suggestList.size();
        model.addAttribute("suggestList", suggestList);
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList", applyList);
        model.addAttribute("totalSuggest", totalSuggest);
        return "comp/comp_apply_management";
    }

    @PostMapping("/api/apply/answer/update")
    public @ResponseBody ApiUtil<String> AnswerApply(@RequestBody ApplyRequest.ApplyStateDTO applyStateDTO,
            Model model) {
        System.out.println("테스트중" + applyStateDTO.getApplyState());
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        applyService.지원응답(applyStateDTO, user.getId());

        return new ApiUtil<String>(true, applyStateDTO.getApplyState());

    }

}
