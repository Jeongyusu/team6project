
package shop.mtcoding.project.jobopening;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.apply.ApplyRepository;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionResponse.RequiredPositionResponseDTO;
import shop.mtcoding.project.position.PositionService;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.resume.ResumeResponse.ResumeInJobOpeningDTO;
import shop.mtcoding.project.resume.ResumeService;
import shop.mtcoding.project.scrap.ScrapService;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillResponse.RequiredSkillResponseDTO;
import shop.mtcoding.project.skill.SkillService;
import shop.mtcoding.project.suggest.SuggestQueryRepository;
import shop.mtcoding.project.suggest.SuggestRepository;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.task.TaskRepository;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class JobOpeningController {
    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private SuggestQueryRepository suggestQueryRepository;

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private ApplyRepository applyRepository;

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
    private ResumeService resumeService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

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

        return "comp/comp_resume";
    }

    // comp_ 채용공고 메인 화면
    @GetMapping("/comp/mainForm")
    public String compMainForm(Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null) {
            if (sessionUser.getGubun() != 2) {
                session.invalidate();
            }
            // User user = userRepository.findById(sessionUser.getId()).get();
        }

        // if (user != null || user.getGubun() == 2) {
        // session.invalidate();
        // }

        // if (userOP.isPresent()) {
        // User user = userOP.get();
        // if (user.getGubun() == 1) {
        // session.invalidate();
        // }
        // }
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        model.addAttribute("jobOpeningMainDTO", jobOpeningMainDTO);
        return "comp_index";

    }

    // user_ 채용공고 메인 화면
    @GetMapping("/user/mainForm")
    public String userMainForm(String keyword, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null) {
            if (sessionUser.getGubun() != 1) {
                session.invalidate();
            }
        }

        List<JobOpeningMainDTO> jobOpeningMainDTO = null;
        if (keyword == null || keyword.trim().isEmpty()) {
            jobOpeningMainDTO = jobOpeningService.메인화면();
        } else {
            jobOpeningMainDTO = jobOpeningService.검색후메인화면(keyword);
        }
        model.addAttribute("jobOpeningMainDTO", jobOpeningMainDTO);
        return "user_index";
    }

    @GetMapping("/comp/jobOpening/saveForm")
    public String saveCompForm(Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/comp/loginForm";
        }

        List<Skill> skillList = skillService.스킬이름();
        List<Position> positionList = positionService.포지션이름();

        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);

        return "comp/comp_job_opening_write";
    }

    // 채용 공고 수정 페이지
    @GetMapping("/comp/jobOpening/{id}/updateForm")
    public String updateCompForm(@PathVariable Integer id, Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/comp/loginForm";
        }

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
        if (sessionUser == null) {
            return "redirect:/comp/loginForm";
        }

        if (jobOpeningSaveDTO.getTitle() == null || jobOpeningSaveDTO.getTitle().isEmpty()) {
            return "redirect:/40x";
        }

        jobOpeningService.공고등록(jobOpeningSaveDTO, sessionUser.getId());
        return "redirect:/comp/jobOpening/compResume";
    }

    // 채용 공고 수정
    @PostMapping("/comp/jobOpening/{id}/update")
    public String updateComp(@PathVariable Integer id,
            JobOpeningRequest.JobOpeningUpdateDTO jobOpeningUpdateDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/comp/loginForm";
        }

        jobOpeningService.공고수정(jobOpeningUpdateDTO, id);
        return "redirect:/comp/jobOpening/compResume";
    }

    // --------- post

    @PostMapping("/api/comp/jobOpening/{id}/delete")
    public @ResponseBody ApiUtil<String> delete(@PathVariable("id") Integer id) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyApiException("인증되지 않았습니다");
        }
        // 2. 핵심로직
        jobOpeningService.공고삭제(id);
        return new ApiUtil<String>(true, "공고가 삭제되었습니다");
        // 3. 응답
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

    //// comp_ 채용공고 상세 화면
    @GetMapping("/comp/jobOpening/{id}")
    public String compJobOpeningDetailForm(@PathVariable Integer id, Model model) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 열람이 가능합니다.");
        }
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        ResumeInJobOpeningDTO resumeInJobOpeningDTO = resumeService.지원화면();
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        model.addAttribute("resumeInJobOpeningDTO", resumeInJobOpeningDTO);
        return "comp/comp_job_opening_detail";
    }

    //// user_채용공고 상세 화면
    @GetMapping("/user/jobOpening/{id}")
    public String userJobOpeningDetailForm(@PathVariable Integer id, Model model) {
        // 로그인 인증검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("로그인 후 열람이 가능합니다.");
        }
        JobOpeningDetailDTO jobOpeningDetailDTO = jobOpeningService.상세채용공고(id);
        ResumeInJobOpeningDTO resumeInJobOpeningDTO = resumeService.지원화면();
        model.addAttribute("jobOpeningDetailDTO", jobOpeningDetailDTO);
        model.addAttribute("resumeInJobOpeningDTO", resumeInJobOpeningDTO);
        return "user/user_job_opening_apply";
    }

    //// user_ 채용정보 화면
    @GetMapping("/user/jobOpening/select")
    public String userJobOpeningSelectForm(Model model) {
        List<Position> positionList = positionService.포지션이름();
        List<Skill> skillList = skillService.스킬이름();
        model.addAttribute("positionList", positionList);
        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);
        return "user/user_emp_info";
    }

    //// 채용정보 제일 첫 화면
    @GetMapping("/api/jobOpening/select/all")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectAll() {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.메인화면();
        return jobOpeningMainDTO;
    }

    //// 경력or지역/경력and지역을 기반으로 데이터 필터링
    @GetMapping("/api/jobOpening/select/cl")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectByCareerOrLocation(
            @RequestParam(name = "career", required = false) String career,
            @RequestParam(name = "careerYear", required = false) String careerYear,
            @RequestParam(name = "location", required = false) String location) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.경력과지역선택(career, careerYear, location);
        return jobOpeningMainDTO;
    }

    //// 포지션or스킬/포지션ans스킬을 기반으로 데이터 필터링
    @GetMapping("/api/jobOpening/select/pk")
    public @ResponseBody List<JobOpeningMainDTO> jobOpeningSelectByPositionOrSkill(
            @RequestParam(required = false) List<Integer> positionIdList,
            @RequestParam(required = false) List<Integer> skillIdList) {
        List<JobOpeningMainDTO> jobOpeningMainDTO = jobOpeningService.포지션과스킬선택(positionIdList, skillIdList);
        return jobOpeningMainDTO;
    }

    //// comp_ 채용정보 화면
    @GetMapping("/comp/jobOpening/select")
    public String compJobOpeningSelectForm(Model model) {
        List<Position> positionList = positionService.포지션이름();
        List<Skill> skillList = skillService.스킬이름();
        model.addAttribute("positionList", positionList);
        model.addAttribute("skillList", skillList);
        model.addAttribute("positionList", positionList);
        return "comp/comp_emp_info";
    }

}