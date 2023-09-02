package shop.mtcoding.project.jobopening;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionService;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillService;

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

    @GetMapping("/jobOpening/saveForm")
    public String saveForm(Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        return "comp/comp_job_opening_write";
    }

    @GetMapping("/jobOpening/mypage/compResum")
    public String compResumForm() {
        return "/jobOpening/compInfo";
    }

    @PostMapping("/jobOpening/save")
    public String save(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO, Integer id) {
        jobOpeningService.공고등록(jobOpeningSaveDTO, 5);
        return "redirect:/jobOpening/compInfo";
    }
}
