package shop.mtcoding.project.suggest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.user.User;

@Service
public class SuggestService {
    @Autowired
    private SuggestRepository suggestRepository;

    @Transactional
    public void 제안(SuggestRequest.SuggestSaveDTO suggestSaveDTO, Integer userId) {
        System.out.println("테스트" + suggestSaveDTO.getJobOpeningId());
        System.out.println("테스트" + suggestSaveDTO.getSelectedResumeId());
        System.out.println("테스트" + suggestSaveDTO.getSelectedUserId());
        Suggest suggest = Suggest.builder()
                .user(User.builder().id(userId).build())
                .resume(Resume.builder().id(suggestSaveDTO.getSelectedResumeId()).build())
                .jobOpening(JobOpening.builder().id(suggestSaveDTO.getJobOpeningId()).build())
                .build();
        try {
            suggestRepository.save(suggest);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

    @Transactional
    public void 제안응답(SuggestRequest.SuggestStateDTO suggestStateDTO) {

        Suggest suggest = suggestRepository.findByResumeIdAndJobOpeningId(suggestStateDTO.getResumeId(),
                suggestStateDTO.getJobOpeningId());
        suggest.setResume(Resume.builder().id(suggestStateDTO.getResumeId()).build());
        suggest.setJobOpening(JobOpening.builder().id(suggestStateDTO.getJobOpeningId()).build());
        ;
        suggest.setSugState(suggestStateDTO.getSugState());

        try {
            suggestRepository.save(suggest);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

}
