package shop.mtcoding.project.resume;

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
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.position.PositionResponse.WishPositionResponseDTO;
import shop.mtcoding.project.position.WishPosition;
import shop.mtcoding.project.position.WishPositionRepository;
import shop.mtcoding.project.resume.ResumeRequest.CompUserOpenResumeDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserSaveResumeDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserUpdateResumeDTO;
import shop.mtcoding.project.scrap.ScrapService;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.HasSkillRepository;
import shop.mtcoding.project.skill.SkillRepository;
import shop.mtcoding.project.skill.SkillResponse.HasSkillResponseDTO;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    HasSkillRepository hasSkillRepository;

    @Autowired
    WishPositionRepository wishPositionRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private HttpSession session;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/resume/saveForm")
    public String resumeSaveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/user/login";
        }
        return "user/user_resume_write";
    }

    @GetMapping("/comp/resume/{id}")
    public String userOpenResumeDetail(@PathVariable Integer id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        model.addAttribute("resume", resume);
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        List<JobOpening> jobOpeningList = jobOpeningRepository.findByUserId(user.getId());
        model.addAttribute("jobOpeningList", jobOpeningList);
        return "comp/comp_resume_detail";
    }

    @PostMapping("/user/resume/save")
    public @ResponseBody String saveResume(UserSaveResumeDTO UserSaveResumeDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서작성(UserSaveResumeDTO, sessionUser.getId());
        return Script.href("/user/resume", "작성 완료");

    }

    @GetMapping("/user/resume/{id}/updateForm")
    public String resumeUpdateForm(@PathVariable Integer id, Model model) {
        Resume resume = resumeService.업데이트폼(id);
        model.addAttribute("resume", resume);

        if (resume.getCareer().equals("신입")) {
            model.addAttribute("isCareerNew", true);
        } else if (resume.getCareer().equals("경력")) {
            model.addAttribute("isCareerOld", true);
            if (resume.getCareerYear().equals("1년차")) {
                model.addAttribute("1year", true);
            } else if (resume.getCareerYear().equals("2년차")) {
                model.addAttribute("2years", true);
            } else if (resume.getCareerYear().equals("3년차")) {
                model.addAttribute("3years", true);
            } else if (resume.getCareerYear().equals("4년차")) {
                model.addAttribute("4years", true);
            } else if (resume.getCareerYear().equals("5년차")) {
                model.addAttribute("5years", true);
            }
        }

        if (resume.getEdu().equals("대졸")) {
            model.addAttribute("isEduUniversity", true);
        } else if (resume.getEdu().equals("초대졸")) {
            model.addAttribute("isEduCollege", true);
        } else if (resume.getEdu().equals("고졸")) {
            model.addAttribute("isEduHighSchool", true);
        }

        return "user/user_resume_update";
    }

    @PostMapping("/user/resume/{id}/update")
    public @ResponseBody String updateResume(@PathVariable Integer id, UserUpdateResumeDTO userUpdateResumeDTO) {
        resumeService.이력서수정(userUpdateResumeDTO, id);
        return Script.href("/user/resume", "수정완료");
    }

    @GetMapping("/api/resume/{resumeId}/skillList")
    public @ResponseBody List<HasSkillResponseDTO> checkboxSkillList(@PathVariable Integer resumeId) {
        List<HasSkill> hasSkillList = hasSkillRepository.hasSkillofResume(resumeId);
        List<HasSkillResponseDTO> hasSkillResponseDTOList = new ArrayList<>();
        for (HasSkill skillList : hasSkillList) {
            HasSkillResponseDTO dtos = HasSkillResponseDTO.builder()
                    .skill(skillList.getSkill().getSkill())
                    .build();
            hasSkillResponseDTOList.add(dtos);
        }
        System.out.println("테스트" + hasSkillResponseDTOList.get(0).getSkill());
        return hasSkillResponseDTOList;
    }

    @GetMapping("/api/resume/{resumeId}/positionList")
    public @ResponseBody List<WishPositionResponseDTO> checkboxPositionList(@PathVariable Integer resumeId) {
        List<WishPosition> wishPositionList = wishPositionRepository.wishPositionofResume(resumeId);
        List<WishPositionResponseDTO> wishPositionResponseDTOList = new ArrayList<>();
        for (WishPosition positionList : wishPositionList) {
            WishPositionResponseDTO dtos = WishPositionResponseDTO.builder()
                    .position(positionList.getPosition().getPosition())
                    .build();
            wishPositionResponseDTOList.add(dtos);
        }
        return wishPositionResponseDTOList;
    }

    @GetMapping("/user/resume/{id}")
    public String userResumeDetail(@PathVariable Integer id, Model model) {

        Resume resume = resumeRepository.findById(id).get();
        List<WishPosition> wishPositionList = wishPositionRepository.positionFindByResumeId(id);
        List<HasSkill> hasSkillList = hasSkillRepository.hasSkillofResume(id);

        model.addAttribute("hasSkillList", hasSkillList);
        model.addAttribute("resume", resume);
        model.addAttribute("wishPositionList", wishPositionList);
        return "user/user_resume_detail_check";

    }

    @PostMapping("/api/resume/{id}/delete")
    public @ResponseBody ApiUtil<String> deleteResume(@PathVariable Integer id) {
        System.out.println("test : 삭제 요청됨 : id : " + id + "통과");
        // 1.인증체크
        // User sessionUser = (User) session.getAttribute("sessionUser");
        // if (sessionUser == null) {
        // throw new MyApiException("인증되지 않았습니다");
        // }

        // 2.핵심로직
        resumeService.삭제(id);
        // 3.응답
        System.out.println("test : 삭제 요청됨2 : id : " + id + "통과");
        return new ApiUtil<String>(true, "이력서 삭제 완료");

    }

    @GetMapping("/user/resume")
    public String userResumeList(Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeRepository.findByUserId(sessionUser.getId());
        Integer totalResume = resumeList.size();
        model.addAttribute("totalResume", totalResume);
        model.addAttribute("resumeList", resumeList);
        return "user/user_resume";
    }

    // @GetMapping("/api/openResum/condition")
    // public @ResponseBody List<CompUserOpenResumeDTO> career(
    // @RequestParam(name = "career", required = false) String career,
    // @RequestParam(name = "careerYear", required = false) String careerYear,
    // @RequestParam(name = "address", required = false) String address) {
    // List<CompUserOpenResumeDTO> compUserOpenResumeDTO =
    // resumeService.조건선택(career, careerYear, address);
    // return compUserOpenResumeDTO;
    // }

    @GetMapping("/comp/userOpenResumeForm")
    public String compOpenResumForm(Model model) {

        List<CompUserOpenResumeDTO> compUserOpenResumeDTO = resumeService.공개이력서목록();
        model.addAttribute("compUserOpenResumeDTO", compUserOpenResumeDTO);

        return "comp/comp_user_open_resume";
    }

}
