package shop.mtcoding.project.jobOpening;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.resume.ResumeRepository;
import shop.mtcoding.project.resume.ResumeRequest.CompUserOpenResumeDTO;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;

@DataJpaTest
public class JobOpeningRepositoryTest {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void findById_test(Integer id) {
        JobOpening jobOpeningOP = jobOpeningRepository.findById(1).get();
    }

    @Test
    public void test_test() {
        List<RequiredSkill> jobOpeningSkillList = requiredSkillRepository.findByJobOpeningId(1); // 1, 3, 5

        Integer skillId1 = jobOpeningSkillList.get(0).getSkill().getId();
        Integer skillId2 = jobOpeningSkillList.get(1).getSkill().getId();

        List<Resume> resumeList = resumeRepository.findByHasSkillIds(skillId1, skillId2);

        List<CompUserOpenResumeDTO> compUserOpenResumeDTOList = new ArrayList<>();
        for (Resume resume : resumeList) {

            CompUserOpenResumeDTO compUserOpenResumeDTO = CompUserOpenResumeDTO.builder()
                    .resumeId(resume.getId())
                    .userName(resume.getUserName())
                    .resumePic(resume.getResumePicUrl())
                    .address(resume.getAddress())
                    .career(resume.getCareer())
                    .careerYear(resume.getCareerYear())
                    .title(resume.getTitle())
                    .openCheck(resume.getOpenCheck())
                    .build();

            compUserOpenResumeDTOList.add(compUserOpenResumeDTO);
        }

        for (CompUserOpenResumeDTO compUserOpenResumeDTO : compUserOpenResumeDTOList) {
        }
    }
}
