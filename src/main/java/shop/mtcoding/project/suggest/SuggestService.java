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
                .resume(Resume.builder().id(suggestSaveDTO.getSelectedResumeId()).build())
                .user(User.builder().id(suggestSaveDTO.getSelectedUserId()).build())
                .build();
        suggestRepository.save(suggest);
    }

}
