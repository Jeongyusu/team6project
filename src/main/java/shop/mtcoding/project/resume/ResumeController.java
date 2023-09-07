package shop.mtcoding.project.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.position.PositionResponse.WishPositionResponseDTO;
import shop.mtcoding.project.position.WishPosition;
import shop.mtcoding.project.position.WishPositionRepository;
import shop.mtcoding.project.resume.ResumeRequest.UserSaveResumeDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserUpdateResumeDTO;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.HasSkillRepository;
import shop.mtcoding.project.skill.SkillResponse.HasSkillResponseDTO;
import shop.mtcoding.project.user.User;

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
    public String saveResume(UserSaveResumeDTO UserSaveResumeDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서작성(UserSaveResumeDTO, sessionUser.getId());
        return "redirect:/";

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
        return Script.back("수정완료");
    }

    @PostMapping("/api/getSkillList")
    public @ResponseBody List<HasSkillResponseDTO> getSkillList(@RequestBody Map<String, Integer> requestBody) {
        Integer resumeId = requestBody.get("resumeId");
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

    @PostMapping("/api/getPositionList")
    public @ResponseBody List<WishPositionResponseDTO> getPositionList(@RequestBody Map<String, Integer> requestBody) {
        Integer resumeId = requestBody.get("resumeId");
        List<WishPosition> wishPositionList = wishPositionRepository.wishPositionofResume(resumeId);
        List<WishPositionResponseDTO> wishPositionResponseDTOList = new ArrayList<>();
        for (WishPosition positionList : wishPositionList) {
            WishPositionResponseDTO dtos = WishPositionResponseDTO.builder()
                    .position(positionList.getPosition().getPosition())
                    .build();
            wishPositionResponseDTOList.add(dtos);
        }
        System.out.println("테스트" + wishPositionResponseDTOList.get(0).getPosition());
        return wishPositionResponseDTOList;
    }

    @PostMapping("/user/resume/{id}/delete")
    public @ResponseBody String delete(@PathVariable Integer id) {
        // 인증체크
        resumeService.삭제(id);
        return Script.back("삭제완료");

    }

    @GetMapping("/comp/resume/{id}")
    public String compResumeDetail() {
        return "user/user_resume_detail";
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

    @DeleteMapping("/api/resume/{id}/delete")
    public @ResponseBody ApiUtil<String> deleteResume(@PathVariable Integer id) {
        // 1.인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyApiException("인증되지 않았습니다");
        }

        // 2.핵심로직
        resumeService.삭제(id);
        // 3.응답
        return new ApiUtil<String>(true, "이력서 삭제 완료");

    }

    @GetMapping("/user/resume")
    public String userResumeList(Model model) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeRepository.findByUserId(sessionUser.getId());
        model.addAttribute("resumeList", resumeList);
        return "user/user_resume";
    }

}
