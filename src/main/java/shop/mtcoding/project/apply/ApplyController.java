package shop.mtcoding.project.apply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.project.apply.ApplyRequest.ApplySaveDTO;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;

@Controller
public class ApplyController {

    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @GetMapping("/user/jobOpening/{id}/applyForm")
    public String ApplyList(@PathVariable Integer id, Model model) {
        JobOpening jobOpening = jobOpeningRepository.findById(id).get();
        model.addAttribute("jobOpening", jobOpening);
        List<Apply> applyList = applyRepository.findAll();
        model.addAttribute("applyList", applyList);

        List<Resume> resumeList = resumeRepository.findAll();
        int totalResume = resumeList.size();
        model.addAttribute("totalResume", totalResume);
        model.addAttribute("resumeList", resumeList);

        return "user/user_job_opening_apply";
    }

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

}
