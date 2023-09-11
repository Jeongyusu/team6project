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
    public void 제안응답(SuggestRequest.SuggestStateDTO suggestStateDTO, Integer userId) {

        Suggest suggest = suggestRepository.findByResumeIdAndJobOpeningId(suggestStateDTO.getResumeId(),
                suggestStateDTO.getJobOpeningId());
        suggest.setUser(User.builder().id(userId).build());
        suggest.setResume(Resume.builder().id(suggestStateDTO.getResumeId()).build());
        suggest.setJobOpening(JobOpening.builder().id(suggestStateDTO.getJobOpeningId()).build());
        ;
        suggest.setSugState(suggestStateDTO.getSugState());
        suggestRepository.save(suggest);

    }

}
