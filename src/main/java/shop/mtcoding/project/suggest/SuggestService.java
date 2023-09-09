package shop.mtcoding.project.suggest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.user.User;

@Service
public class SuggestService {
    @Autowired
    private SuggestRepository suggestRepository;

    @Transactional
    public void 제안(SuggestRequest.SuggestSaveDTO suggestSaveDTO) {
        Suggest suggest = Suggest.builder()
                .user(User.builder().id(suggestSaveDTO.getSelectedUserId()).build())
                .resume(Resume.builder().id(suggestSaveDTO.getSelectedResumeId()).build())
                .build();
        suggestRepository.save(suggest);
    }

    @Transactional
    public void 제안응답(SuggestRequest.SuggestStateDTO suggestStateDTO, Integer userId, Integer resumeId) {
        Suggest suggest = Suggest.builder()
                .user(User.builder().id(userId).build())
                .resume(Resume.builder().id(resumeId).build())
                .sugState(suggestStateDTO.getSugState())
                .build();
            suggestRepository.save(suggest);
    }

}
