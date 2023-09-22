package shop.mtcoding.project.scrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.scrap.CompScrapRequest.CompScrapDeleteDTO;
import shop.mtcoding.project.scrap.CompScrapRequest.CompScrapSaveDTO;
import shop.mtcoding.project.scrap.CompScrapResponse.ScrapResumeDTO;
import shop.mtcoding.project.scrap.UserScrapRequest.UserScrapDTO;
import shop.mtcoding.project.scrap.UserScrapResponse.ScrapJobOpeningDTO;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserService;

@Service
public class ScrapService {

    @Autowired
    private UserScrapRepository userScrapRepository;

    @Autowired
    private CompScrapRepository compScrapRepository;

    @Transactional
    public void 채용공고스크랩(Integer sessionId, UserScrapRequest.UserScrapDTO userScrapDTO) {
        UserScrap userScrap = UserScrap.builder()
                .user(User.builder().id(sessionId).build())
                .jobOpening(JobOpening.builder().id(userScrapDTO.getJobOpeningId()).build())
                .build();

        try {
            userScrapRepository.save(userScrap);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유: " + e.getMessage());
        }

    }

    @Transactional
    public void 채용공고스크랩삭제(Integer sessionId, UserScrapRequest.UserScrapDeleteDTO userScrapDeleteDTO) {
        Optional<UserScrap> userScrapOP = userScrapRepository.mfindByJobOpeningId(userScrapDeleteDTO.getJobOpeningId(),
                sessionId);

        // null 스크랩 확인
        if (userScrapOP.isEmpty()) {
            throw new MyException("삭제할 스크랩이 없습니다.");
        }
        UserScrap userScrap = userScrapOP.get();

        // 권한인증
        if (sessionId != userScrap.getUser().getId()) {
            throw new MyException("삭제할 권한이 없습니다.");
        }

        try {
            userScrapRepository.mdeleteByJobOpeningId(userScrapDeleteDTO.getJobOpeningId(), sessionId);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

    @Transactional
    public void 이력서스크랩(Integer sessionId, CompScrapSaveDTO compScrapSaveDTO) {
        CompScrap compScrap = CompScrap.builder()
                .user(User.builder().id(sessionId).build())
                .resume(Resume.builder().id(compScrapSaveDTO.getResumeId()).build())
                .build();

        try {
            compScrapRepository.save(compScrap);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유: " + e.getMessage());
        }
    }

    @Transactional
    public void 이력서스크랩삭제(Integer sessionId, CompScrapDeleteDTO compScrapDeleteDTO) {

        Optional<CompScrap> compScrapOP = compScrapRepository.mfindByResumeId(compScrapDeleteDTO.getResumeId(),
                sessionId);

        // null 스크랩 확인
        if (compScrapOP.isEmpty()) {
            throw new MyException("삭제할 스크랩이 없습니다.");
        }
        CompScrap compScrap = compScrapOP.get();

        // 권한인증
        if (sessionId != compScrap.getUser().getId()) {
            throw new MyException("삭제할 권한이 없습니다.");
        }

        try {
            compScrapRepository.mdeleteByResumeId(compScrapDeleteDTO.getResumeId(), sessionId);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

    public List<ScrapResumeDTO> 이력서스크랩조회(Integer sessionId) {
        List<CompScrap> compScrapList = compScrapRepository.findByUserIdFromComp(sessionId);

        List<ScrapResumeDTO> scrapResumeDTOList = new ArrayList<>();
        for (CompScrap compScrap : compScrapList) {

            // 이력서의 구직자가 할 수 있는 기술 이름을 출력
            List<String> skillList = new ArrayList<>();
            for (HasSkill skill : compScrap.getResume().getHasSkillList()) {
                String skillName = skill.getSkill().getSkill();
                skillList.add(skillName);
            }

            // 이중 for문을 방지하기 위해, 기술 이름의 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillList);

            ScrapResumeDTO scrapResumeDTO = ScrapResumeDTO.builder()
                    .resumeId(compScrap.getResume().getId())
                    .title(compScrap.getResume().getTitle())
                    .userName(compScrap.getUser().getUserName())
                    .edu(compScrap.getResume().getEdu())
                    .skillName(skillListString)
                    .build();

            scrapResumeDTOList.add(scrapResumeDTO);
        }
        return scrapResumeDTOList;
    }

    public List<ScrapJobOpeningDTO> 채용공고스크랩조회(Integer sessionId) {
        List<UserScrap> userScrapList = userScrapRepository.findByUserIdFromUser(sessionId);

        List<ScrapJobOpeningDTO> scrapJobOpeningDTOList = new ArrayList<>();
        for (UserScrap userScrap : userScrapList) {

            // 이력서의 구직자가 할 수 있는 기술 이름을 출력
            List<String> skillList = new ArrayList<>();
            for (RequiredSkill skill : userScrap.getJobOpening().getRequiredSkillList()) {
                String skillName = skill.getSkill().getSkill();
                skillList.add(skillName);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillList);

            // D-day 마감일 만들기
            String DdayDeadLine = FormatDate.DdayFormatDate(userScrap.getJobOpening().getDeadLine());

            ScrapJobOpeningDTO scrapJobOpeningDTO = ScrapJobOpeningDTO.builder()
                    .jobOpeningId(userScrap.getJobOpening().getId())
                    .title(userScrap.getJobOpening().getTitle())
                    .compName(userScrap.getUser().getUserName())
                    .edu(userScrap.getJobOpening().getEdu())
                    .skillName(skillListString)
                    .compPicUrl(userScrap.getJobOpening().getUser().getCompPicUrl())
                    .dDayDeadLine(DdayDeadLine)
                    .build();

            scrapJobOpeningDTOList.add(scrapJobOpeningDTO);
        }

        return scrapJobOpeningDTOList;
    }

}
