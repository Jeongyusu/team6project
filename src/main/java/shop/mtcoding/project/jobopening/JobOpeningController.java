
package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionResponse.RequiredPositionResponseDTO;
import shop.mtcoding.project.position.PositionService;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillResponse.RequiredSkillResponseDTO;
import shop.mtcoding.project.skill.SkillService;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.task.TaskRepository;
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
    private TaskRepository taskRepository;

    @Autowired
    private QualifiedRepository qualifiedRepository;

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private HttpSession session;

    @GetMapping("/comp/jobOpening/myPageForm")
    public String compInfoFrom(Model model, Integer id) {
        JobOpening jobOpening = jobOpeningService.공고수정페이지(1);
        model.addAttribute("jobOpening", jobOpening);
        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        int totalJopOpeningList = jobOpeningList.size();
        model.addAttribute("totalJopOpeningList", totalJopOpeningList);
        model.addAttribute("jobOpeningList", jobOpeningList);
        return "/comp/comp_info";
    }

    @GetMapping("/comp/jobOpening/mypage/compResum")
    public String compResumForm() {
        return "/jobOpening/compInfo";
    }

    // --------- get

    @GetMapping("/comp/jobOpening/saveForm")
    public String saveCompForm(Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        return "comp/comp_job_opening_write";
    }

    @GetMapping("/comp/jobOpening/{id}/updateForm")
    public String updateCompForm(@PathVariable Integer id, Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        JobOpening jobOpening = jobOpeningService.공고수정페이지(id);
        model.addAttribute("jobOpening", jobOpening);

        List<Task> taskList = taskRepository.findByJobOpeningId(id);
        model.addAttribute("taskList", taskList);

        List<Qualified> qualList = qualifiedRepository.findByJobOpeningId(id);
        model.addAttribute("qualList", qualList);

        if (jobOpening.getCareer().equals("신입")) {
            model.addAttribute("isCareerNew", true);
        } else if (jobOpening.getCareer().equals("경력")) {
            model.addAttribute("isCareerOld", true);
            if (jobOpening.getCareerYear().equals("1년차")) {
                model.addAttribute("1year", true);
                System.out.println("테스트 경력");
            } else if (jobOpening.getCareerYear().equals("2년차")) {
                model.addAttribute("2years", true);
            } else if (jobOpening.getCareerYear().equals("3년차")) {
                model.addAttribute("3years", true);
            } else if (jobOpening.getCareerYear().equals("4년차")) {
                model.addAttribute("4years", true);
            } else if (jobOpening.getCareerYear().equals("5년차")) {
                model.addAttribute("5years", true);
            }
        }
        System.out.println("테스트1");

        if (jobOpening.getEdu().equals("대졸")) {
            model.addAttribute("isEduUniversity", true);
        } else if (jobOpening.getEdu().equals("초대졸")) {
            model.addAttribute("isEduCollege", true);
        } else if (jobOpening.getEdu().equals("고졸")) {
            model.addAttribute("isEduHighSchool", true);
        }
        System.out.println("테스트2");

        return "comp/comp_job_opening_update";
    }

    // --------- Post

    @PostMapping("/comp/jobOpening/save")
    public String saveComp(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobOpeningService.공고등록(jobOpeningSaveDTO, sessionUser.getId());
        return "redirect:/comp/jobOpening/myPageForm";
    }

    @PostMapping("/comp/jobOpening/{id}/update")
    public String updateComp(@PathVariable Integer id,
            JobOpeningRequest.JobOpeningUpdateDTO jobOpeningUpdateDTO) {
        jobOpeningService.공고수정(jobOpeningUpdateDTO, id);
        return "redirect:/comp/jobOpening/myPageForm";
    }

    // --------- api

    @PostMapping("/api/getCheckBoxSkill")
    public @ResponseBody List<RequiredSkillResponseDTO> getCheckboxSkill(
            @RequestBody Map<String, Integer> requestBody) {
        System.out.println("테스트");
        Integer jobId = requestBody.get("jobId");
        System.out.println("테스트" + jobId);

        List<RequiredSkill> requiredSkillList = requiredSkillRepository.findByJobOpeningId(jobId);
        List<RequiredSkillResponseDTO> requiredSkillResponseDTOList = new ArrayList<>();

        for (RequiredSkill skillList : requiredSkillList) {
            RequiredSkillResponseDTO dtos = RequiredSkillResponseDTO.builder()
                    .skill(skillList.getSkill().getSkill())
                    .build();
            requiredSkillResponseDTOList.add(dtos);
        }
        System.out.println("테스트" + requiredSkillResponseDTOList.get(0).getSkill());
        return requiredSkillResponseDTOList;
    }

    @PostMapping("/api/getCheckBoxPosition")
    public @ResponseBody List<RequiredPositionResponseDTO> getCheckboxPosition(
            @RequestBody Map<String, Integer> requestBody) {
        System.out.println("테스트");
        Integer jobId = requestBody.get("jobId");
        System.out.println("테스트" + jobId);

        List<RequiredPosition> requiredPositionList = requiredPositionRepository.findByJobOpeningId(jobId);
        List<RequiredPositionResponseDTO> requiredPositionResponseDTOList = new ArrayList<>();

        for (RequiredPosition positionList : requiredPositionList) {
            RequiredPositionResponseDTO dtos = RequiredPositionResponseDTO.builder()
                    .position(positionList.getPosition().getPosition())
                    .build();
            requiredPositionResponseDTOList.add(dtos);
        }
        System.out.println("테스트" + requiredPositionResponseDTOList.get(0).getPosition());
        return requiredPositionResponseDTOList;
    }

}