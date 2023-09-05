package shop.mtcoding.project.apply;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Transactional
    public void 지원(ApplyRequest.ApplySaveDTO applySaveDTO) {
        Apply apply = Apply.builder()
                .resume(Resume.builder().id(applySaveDTO.getSelectedResumeId()).build())
                .jobOpening(JobOpening.builder().id(applySaveDTO.getSelectedjobOpeningId()).build())
                .build();
        applyRepository.save(apply);

    }

    

}
