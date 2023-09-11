package shop.mtcoding.project.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.util.ApiUtil;
import shop.mtcoding.project._core.util.Script;
import shop.mtcoding.project.apply.Apply;
import shop.mtcoding.project.apply.ApplyRepository;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.jobopening.JobOpeningService;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.scrap.ScrapService;
import shop.mtcoding.project.scrap.UserScrapResponse.ScrapJobOpeningDTO;
import shop.mtcoding.project.suggest.SuggestQueryRepository;
import shop.mtcoding.project.suggest.SuggestRepository;
import shop.mtcoding.project.user.UserRequest.CompInfoUpdateDTO;

@Controller
public class UserController {

    @Autowired
    private JobOpeningService jobOpeningService;

    @Autowired
    private SuggestRepository suggestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    SuggestQueryRepository suggestQueryRepository;

    ///////// 구직자홈
    @GetMapping("/user")
    public String home(Model model) {
        return "user_index";
    }

    ///////// 회사홈
    @GetMapping("/comp")
    public String compHome() {
        return "comp_index";
    }

    ///////// 구직자 회원가입페이지
    @GetMapping("/user/joinForm")
    public String userJoinForm() {
        return "user/user_join";
    }

    ///////// 회사 회원가입페이지
    @GetMapping("/comp/joinForm")
    public String compJoinForm() {
        return "comp/comp_join";
    }

    ///////// 유저 회원가입
    @PostMapping("/user/join")
    public @ResponseBody String userJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/user", "회원가입 완료");

    }

    ///////// 구직자 회원가입
    @PostMapping("/comp/join")
    public @ResponseBody String compJoin(UserRequest.UserJoinDTO userJoinDTO) {
        userService.유저회원가입(userJoinDTO);
        return Script.href("/comp", "회원가입 완료");
    }

    ///////// 유저 비번 변경하기 완료
    @PostMapping("/user/password/update")
    public @ResponseBody String userUpdate(UserRequest.UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return Script.href("/user/myPageForm", "비밀번호 변경완료");
    }

    ///////// 회사 비번 변경페이지
    @GetMapping("/comp/password/updateForm")
    public String compPWUpdateForm() {
        return "comp/comp_password_update";
    }

    ///////// 회사 비번 변경
    @PostMapping("/comp/password/update")
    public @ResponseBody String compPWUpdate(UserRequest.UserUpdateDTO userUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회원정보수정(userUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return Script.href("/comp/myPageForm", "비밀번호 변경완료");
    }

    @PostMapping("/user/picUpdate")
    public String userPicUpdate(UserRequest.UserPicUpdateDTO userPicUpdateDTO, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.유저사진수정(userPicUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "user/user_mypage";
    }

    @GetMapping("/user/loginForm")
    public String userLoginForm() {
        return "user/user_login";

    }

    @GetMapping("/comp/loginForm")
    public String compLoginForm() {
        return "comp/comp_login";
    }

    //////// 구직자///////

    @GetMapping("/user/myPageForm")
    public String userMyPageForm(Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = resumeRepository.findByUserId(sessionUser.getId());
        model.addAttribute("resumeList", resumeList);
        int totalResume = resumeList.size();
        model.addAttribute("totalResume", totalResume);
        List<Apply> applyList2 = applyRepository.findByUserId(sessionUser.getId());
        int totalApply = applyList2.size();
        List<JobOpening> jobOpeningInfo = suggestQueryRepository.findJobOpeningsByUserId(sessionUser.getId());
        model.addAttribute("jobOpeningInfo", jobOpeningInfo);
        model.addAttribute("totalApply", totalApply);
        model.addAttribute("applyList2", applyList2);

        List<ScrapJobOpeningDTO> scrapJobOpeningDTOList = scrapService.채용공고스크랩조회(sessionUser.getId());
        Integer scrapJobOpeningSum = scrapJobOpeningDTOList.size();
        model.addAttribute("scrapJobOpeningDTOList", scrapJobOpeningDTOList);
        model.addAttribute("scrapJobOpeningSum", scrapJobOpeningSum);

        return "user/user_mypage";

    }

    @GetMapping("/comp/myPageForm")
    public String compMyPageForm() {
        return "comp/comp_info_update";
    }

    @PostMapping("/user/login")
    public @ResponseBody String userLogin(UserRequest.UserLoginDTO userLoginDTO) {

        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/user", "로그인 완료");
    }

    @PostMapping("/comp/login")
    public @ResponseBody String compLogin(UserRequest.UserLoginDTO userLoginDTO) {
        User sessionUser = userService.유저로그인(userLoginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/comp", "로그인 완료");
    }

    @GetMapping("/api/check")
    public @ResponseBody ApiUtil<String> check(String userEmailId) {
        User user = userRepository.findByUserEmailId(userEmailId);
        if (user != null) {
            throw new MyApiException("EmailID를 사용할 수 없습니다");
        }
        return new ApiUtil<String>(true, "EmailID를 사용할 수 있습니다.");

    }

    @GetMapping("/user/logout")
    public String userLogout() {
        session.invalidate();
        return "redirect:/user";
    }

    @GetMapping("/comp/logout")
    public String compLogout() {
        session.invalidate();
        return "redirect:/comp";
    }

    @GetMapping("/user/info/updateForm")
    public String userInfoUpdateForm() {
        return "user/user_update";
    }

    @GetMapping("/user/scrap")
    public String userScrap() {
        return "user/user_scrap";
    }

    //////// 회사정보 수정페이지
    @GetMapping("/comp/info/updateForm")
    public String compInfoUpdateForm() {
        return "comp/comp_info_update";
    }

    /////// 회사정보 수정
    @PostMapping("/comp/info/update")
    public String compInfoUpdate(CompInfoUpdateDTO compInfoUpdateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userService.회사정보수정(compInfoUpdateDTO, sessionUser.getId());
        session.setAttribute("sessionUser", user);
        return "comp/comp_info_update";
    }
}