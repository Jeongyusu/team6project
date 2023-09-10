
package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
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

    @GetMapping("/comp/indexForm")
    public String compIndexForm() {
        return "comp_index";
    }

    // 채용 공고 목록 페이지
    @GetMapping("/comp/jobOpening/compResume")
    public String compResumForm(Model model, Integer id) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        List<JobOpening> JobOpeningList = jobOpeningRepository.findByUserId(sessionUser.getId());
        int totalJobOpening = JobOpeningList.size();
        model.addAttribute("totalJobOpening", totalJobOpening);

        List<JobOpening> jobOpeningList = jobOpeningRepository.findAll();
        int totalJopOpeningList = jobOpeningList.size();
        model.addAttribute("totalJopOpeningList", totalJopOpeningList);
        model.addAttribute("jobOpeningList", jobOpeningList);

        return "/comp/comp_resume";
    }

    @GetMapping("/comp/mainForm")
    public String compMainForm(Model model) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        model.addAttribute("jobOpeningMainDTO", jobOpeningMainDTO);
        return "comp_index";
    }

    // 채용 공고 작성 페이지
    @GetMapping("/comp/jobOpening/saveForm")
    public String saveCompForm(Model model) {

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        return "comp/comp_job_opening_write";
    }

    // 채용 공고 수정 페이지
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

    // --------- get

    // 채용 공고 작성
    @PostMapping("/comp/jobOpening/save")
    public String saveComp(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobOpeningService.공고등록(jobOpeningSaveDTO, sessionUser.getId());
        return "redirect:/comp/jobOpening/compResume";
    }

    // 채용 공고 수정
    @PostMapping("/comp/jobOpening/{id}/update")
    public String updateComp(@PathVariable Integer id,
            JobOpeningRequest.JobOpeningUpdateDTO jobOpeningUpdateDTO) {
        jobOpeningService.공고수정(jobOpeningUpdateDTO, id);
        return "redirect:/comp/jobOpening/compResume";
    }

    // --------- post

    @PostMapping("/api/comp/jobOpening/{id}/delete")
    public @ResponseBody ApiUtil<String> delete(@PathVariable("id") Integer id) {
        // 2. 핵심로직
        jobOpeningService.공고삭제(id);
        // 3. 응답
        return new ApiUtil<String>(true, "공고가 삭제되었습니다");
    }

    // --------- delete

    // 스킬 체크 박스
    @GetMapping("/api/jobOpening/{jobOpeningId}/skillList")
    public @ResponseBody List<RequiredSkillResponseDTO> chekboxSkillList(@PathVariable Integer jobOpeningId) {

        List<RequiredSkill> requiredSkillList = requiredSkillRepository.findByJobOpeningId(jobOpeningId);
        List<RequiredSkillResponseDTO> requiredSkillResponseDTOList = new ArrayList<>();

        for (RequiredSkill skillList : requiredSkillList) {
            RequiredSkillResponseDTO dtos = RequiredSkillResponseDTO.builder()
                    .skill(skillList.getSkill().getSkill())
                    .build();
            requiredSkillResponseDTOList.add(dtos);
        }
        return requiredSkillResponseDTOList;
    }

    // 포지션 체크 박스
    @GetMapping("/api/jobOpening/{jobOpeningId}/positionList")
    public @ResponseBody List<RequiredPositionResponseDTO> checkboxPositionList(@PathVariable Integer jobOpeningId) {

        List<RequiredPosition> requiredPositionList = requiredPositionRepository.findByJobOpeningId(jobOpeningId);
        List<RequiredPositionResponseDTO> requiredPositionResponseDTOList = new ArrayList<>();

        for (RequiredPosition positionList : requiredPositionList) {
            RequiredPositionResponseDTO dtos = RequiredPositionResponseDTO.builder()
                    .position(positionList.getPosition().getPosition())
                    .build();
            requiredPositionResponseDTOList.add(dtos);
        }
        return requiredPositionResponseDTOList;
    }

    // --------- api
}