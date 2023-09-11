package shop.mtcoding.project.apply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.suggest.SuggestRequest;
import shop.mtcoding.project.user.User;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    //// user_ 공고 지원
    @Transactional
    public void 지원(ApplyRequest.ApplySaveDTO applySaveDTO) {
        Apply apply = Apply.builder()
                .resume(Resume.builder().id(applySaveDTO.getSelectedResumeId()).build())
                .jobOpening(JobOpening.builder().id(applySaveDTO.getSelectedjobOpeningId()).build())
                .build();
        applyRepository.save(apply);

    }

    @Transactional
    public void 지원응답(ApplyRequest.ApplyStateDTO applyStateDTO, Integer userId) {

        Apply apply = applyRepository.findByResumeIdAndJobOpeningId(applyStateDTO.getResumeId(),
                applyStateDTO.getResumeId());
        apply.setUser(User.builder().id(userId).build());
        apply.setResume(Resume.builder().id(applyStateDTO.getResumeId()).build());
        apply.setJobOpening(JobOpening.builder().id(applyStateDTO.getJobOpeningId()).build());
        apply.setApplyState(applyStateDTO.getApplyState());
        applyRepository.save(apply);

    }

}
