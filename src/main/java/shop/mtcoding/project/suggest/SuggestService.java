package shop.mtcoding.project.suggest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.suggest.SuggestRequest.SuggestStateDTO;
import shop.mtcoding.project.user.User;

@Service
public class SuggestService {
    @Autowired
    private HttpSession session;

    @Autowired
    private SuggestRepository suggestRepository;

    @Transactional
    public void 제안(SuggestRequest.SuggestSaveDTO suggestSaveDTO) {
        Suggest suggest = Suggest.builder()
                .resume(Resume.builder().id(suggestSaveDTO.getSelectedResumeId()).build())
                .user(User.builder().id(suggestSaveDTO.getSelectedUserId()).build())
                .build();
        suggestRepository.save(suggest);
    }

    // @Transactional
    // public void 제안수락(SuggestRequest.SuggestStateDTO suggestStateDTO, Integer id)
    // {
    // System.out.println("저요기요");
    // List<Suggest> suggest = suggestRepository.userSUggestAccecpt(id);
    // System.out.println("저요기요2");
    // suggestRepository.save(suggest);
    // System.out.println("저요기요3");
    // }

    @Transactional
    public void 제안수락(SuggestRequest.SuggestStateDTO suggestStateDTO, Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Suggest> suggestAccept = suggestRepository.findByResumeId(resumeId);

    }

    // @Transactional
    // public void 제안수락(SuggestRequest.SuggestStateDTO suggestStateDTO, Integer id)
    // {
    // User sessionUser = (User) session.getAttribute("sessionUser");
    // List<Suggest> userSuggests = suggestRepository.findByUser(sessionUser);

    // for (Suggest suggest : userSuggests) {
    // // 상태를 "수락"으로 변경
    // suggest.setSugState("수락");
    // }

    // suggestRepository.saveAll(userSuggests);
    // }
}
