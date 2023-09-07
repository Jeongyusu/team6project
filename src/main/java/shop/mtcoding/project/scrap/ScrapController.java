package shop.mtcoding.project.scrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.project._core.util.ApiUtil;

@Controller
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    // user_채용공고 스크랩
    @PostMapping("/api/user/jobOpening/scrap/save")
    public @ResponseBody ApiUtil<String> userJobOpeningSaveScrap(
            @RequestBody UserScrapRequest.UserScrapDTO userScrapDTO) {
        scrapService.채용공고스크랩(1, userScrapDTO);
        return new ApiUtil<String>(true, "스크랩 성공");
    }

    // user_채용공고 스크랩 삭제
    @DeleteMapping("/api/user/jobOpening/scrap/delete")
    public @ResponseBody ApiUtil<String> userJopOpeningDeleteScrap(
            @RequestBody UserScrapRequest.UserScrapDeleteDTO userScrapDeleteDTO) {
        scrapService.채용공고스크랩삭제(1, userScrapDeleteDTO);
        return new ApiUtil<String>(true, "스크랩 삭제");
    }
}