package shop.mtcoding.project.jobopening;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionService;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillService;
import shop.mtcoding.project.user.User;

@Controller
public class JobOpeningController {

    @Autowired
    private JobOpeningService jobOpeningService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private HttpSession session;

    @GetMapping("/jobOpening/compInfo")
    public String compInfoFrom() {
        return "/comp/comp_info";
    }

    @GetMapping("/jobOpening/mypage/compResum")
    public String compResumForm() {
        return "/jobOpening/compInfo";
    }

    @GetMapping("/jobOpening/saveForm")
    public String saveCompForm(Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        return "comp/comp_job_opening_write";
    }

    @PostMapping("/jobOpening/save")
    public String saveComp(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobOpeningService.공고등록(jobOpeningSaveDTO, sessionUser.getId());
        return "redirect:/jobOpening/compInfo";
    }

    @GetMapping("/jobOpening/{id}/updateForm")
    public String updateCompForm(@PathVariable Integer id, Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        Optional<JobOpening> jobOpening = jobOpeningService.공고수정페이지(id);
        model.addAttribute("jobOpening", jobOpening);
        // mustache 오류 - jobOpening 찾지 못함

        return "comp/comp_job_opening_update";
    }

    @PostMapping("/jobOpening/{id}/update")
    public String updateComp(@PathVariable Integer id,
            JobOpeningRequest.JobOpeningUpdateDTO jobOpeningUpdateDTO) {
        jobOpeningService.공고수정(jobOpeningUpdateDTO, id);
        return "redirect:/jobOpening/" + id;
    }
}
