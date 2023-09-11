package shop.mtcoding.project.scrap;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.scrap.CompScrapResponse.ScrapResumeDTO;
import shop.mtcoding.project.scrap.UserScrapResponse.ScrapJobOpeningDTO;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    // user_채용공고 스크랩
    @PostMapping("/api/user/jobOpening/scrap/save")
    public @ResponseBody ApiUtil<String> userJobOpeningSaveScrap(
            @RequestBody UserScrapRequest.UserScrapDTO userScrapDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.채용공고스크랩(sessionUser.getId(), userScrapDTO);
        return new ApiUtil<String>(true, "스크랩 성공");
    }

    // user_채용공고 스크랩 삭제
    @DeleteMapping("/api/user/jobOpening/scrap/delete")
    public @ResponseBody ApiUtil<String> userJopOpeningDeleteScrap(
            @RequestBody UserScrapRequest.UserScrapDeleteDTO userScrapDeleteDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.채용공고스크랩삭제(sessionUser.getId(), userScrapDeleteDTO);
        return new ApiUtil<String>(true, "스크랩 삭제");
    }

    // comp_이력서 스크랩
    @PostMapping("/api/comp/resume/scrap/save")
    public @ResponseBody ApiUtil<String> compResumeSaveScrap(
            @RequestBody CompScrapRequest.CompScrapSaveDTO compScrapSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.이력서스크랩(sessionUser.getId(), compScrapSaveDTO);
        return new ApiUtil<String>(true, "스크랩 성공");
    }

    // comp_이력서 스크랩 삭제
    @DeleteMapping("/api/comp/resume/scrap/delete")
    public @ResponseBody ApiUtil<String> compResumeDeleteScrap(
            @RequestBody CompScrapRequest.CompScrapDeleteDTO compScrapDeleteDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        scrapService.이력서스크랩삭제(sessionUser.getId(), compScrapDeleteDTO);
        return new ApiUtil<String>(true, "스크랩 삭제");
    }

    @GetMapping("/user/scrap")
    public String userScrap(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        List<ScrapJobOpeningDTO> scrapJobOpeningDTOList = scrapService.채용공고스크랩조회(user.getId());
        Integer scrapJobOpeningSum = scrapJobOpeningDTOList.size();
        model.addAttribute("scrapJobOpeningDTOList", scrapJobOpeningDTOList);
        model.addAttribute("scrapJobOpeningSum", scrapJobOpeningSum);
        return "user/user_scrap";
    }

    @GetMapping("/comp/scrap")
    public String compScrap(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId()).get();
        List<ScrapResumeDTO> scrapResumeDTOList = scrapService.이력서스크랩조회(user.getId());
        Integer scrapResumeSum = scrapResumeDTOList.size();
        model.addAttribute("scrapResumeDTOList", scrapResumeDTOList);
        model.addAttribute("scrapResumeSum", scrapResumeSum);
        return "comp/comp_scrap";
    }

}
