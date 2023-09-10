package shop.mtcoding.project.suggest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.suggest.SuggestRequest.SuggestSaveDTO;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserRepository;

@Controller
public class SuggestController {
    @Autowired
    private SuggestService suggestService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}/resume/detail")
    public String userOpenResumeDetail(@PathVariable Integer id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        model.addAttribute("resume", resume);

        return "user/user_resume_detail";
    }

    @GetMapping("/openResumeList")
    public String OpenResumeList(Model model) {
        List<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("resumeList", resumeList);

        return "comp/comp_user_open_resume";
    }
    // @GetMapping("/openResumeList")
    // public @ResponseBody List<Resume> OpenResumeList(Model model) {
    // List<Resume> resumeList = resumeRepository.findAll();
    // model.addAttribute("resumeList", resumeList);

    // return resumeList;
    // }

    @PostMapping("/userSuggest")
    public String UserSuggest(SuggestSaveDTO suggestSaveDTO, Model model) {
        suggestService.제안(suggestSaveDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        model.addAttribute("sessionUser", sessionUser);
        Integer id = suggestSaveDTO.getSelectedResumeId();
        return "redirect:/user/" + id + "/resume/detail";
    }

    @PostMapping("/api/suggest/answer/update")
    public @ResponseBody ApiUtil<String> AnswerSuggest(@RequestBody SuggestRequest.SuggestStateDTO suggestStateDTO, Model model){
        System.out.println("테스트중" + suggestStateDTO.getSugState());
        // User sessionUser = (User) session.getAttribute("sessionUser");
        // User user = userRepository.findById(sessionUser.getId()).get();
        User user = userRepository.findById(1).get();
        suggestService.제안응답(suggestStateDTO, user.getId());
        // if (suggestStateDTO.getSugState().equals("대기중")){
        //     model.addAttribute("isWaiting", "대기중");
        // } else if (suggestStateDTO.getSugState().equals("수락")){
        //     model.addAttribute("isAccepted", "합격");
        // } else if (suggestStateDTO.getSugState().equals("거절")){
        //     model.addAttribute("isRejected", "불합격");
        // }
        return new ApiUtil<String>(true, suggestStateDTO.getSugState());

    }


    

}
