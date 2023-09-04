package shop.mtcoding.project.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project.position.WishPosition;
import shop.mtcoding.project.position.WishPositionRepository;
import shop.mtcoding.project.position.WishPositionService;
import shop.mtcoding.project.position.PositionRequest.WishPositionResponseDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserSaveResumeDTO;
import shop.mtcoding.project.resume.ResumeRequest.UserUpdateResumeDTO;
import shop.mtcoding.project.resume.ResumeResponse.ResumeCareerAndEduResponseDTO;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.HasSkillRepository;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.SkillRequest.HasSkillResponseDTO;
import shop.mtcoding.project.skill.SkillRequest.MySkill;
import shop.mtcoding.project.user.User;

@Controller
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    WishPositionService wishPositionService;

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
        return "user/user_resume_update";
    }

    @PostMapping("/user/resume/{id}/update")
    public String updateResume(@PathVariable Integer id, UserUpdateResumeDTO userUpdateResumeDTO) {
        resumeService.이력서수정(userUpdateResumeDTO, id);
        return "redirect:/";
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

    @PostMapping("/api/getCareerAndEdu")
    public @ResponseBody ResumeCareerAndEduResponseDTO getResumeCareerAndEdu(
            @RequestBody Map<String, Integer> requestBody) {
        Integer resumeId = requestBody.get("resumeId");
        Resume resume = resumeRepository.findById(resumeId).get();
        ResumeCareerAndEduResponseDTO resumeCareerAndEduResponseDTO = ResumeCareerAndEduResponseDTO.builder()
                .career(resume.getCareer())
                .careerYear(resume.getCareerYear())
                .edu(resume.getEdu())
                .openCheck(resume.getOpenCheck())
                .build();
        return resumeCareerAndEduResponseDTO;

    }

}
