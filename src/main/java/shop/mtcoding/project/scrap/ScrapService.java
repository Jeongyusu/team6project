package shop.mtcoding.project.scrap;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.scrap.UserScrapRequest.UserScrapDTO;
import shop.mtcoding.project.user.User;
import shop.mtcoding.project.user.UserService;

@Service
public class ScrapService {

    @Autowired
    private UserScrapRepository userScrapRepository;

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

}