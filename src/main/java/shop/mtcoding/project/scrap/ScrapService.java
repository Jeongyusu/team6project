package shop.mtcoding.project.scrap;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.scrap.CompScrapRequest.CompScrapDTO;
import shop.mtcoding.project.scrap.CompScrapRequest.CompScrapDeleteDTO;
import shop.mtcoding.project.scrap.UserScrapRequest.UserScrapDTO;
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

        userScrapRepository.save(userScrap);
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
        userScrapRepository.mdeleteByJobOpeningId(userScrapDeleteDTO.getJobOpeningId(), sessionId);
    }

    @Transactional
    public void 이력서스크랩(Integer sessionId, CompScrapDTO compScrapDTO) {
        CompScrap compScrap = CompScrap.builder()
                .user(User.builder().id(sessionId).build())
                .resume(Resume.builder().id(compScrapDTO.getResumeId()).build())
                .build();

        compScrapRepository.save(compScrap);
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
        compScrapRepository.mdeleteByResumeId(compScrapDeleteDTO.getResumeId(), sessionId);

    }

}