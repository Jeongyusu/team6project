package shop.mtcoding.project.resume;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.resume.ResumeRequest.CompUserOpenResumeDTO;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;

@DataJpaTest
public class RequiredSkillServiceTest {

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void mfindByAllJoinSkillAndJobOpening_test() {
        List<RequiredSkill> compUserResumeList = requiredSkillRepository.findAll();
        for (RequiredSkill requiredSkill : compUserResumeList) {
        }
    }

    @Test
    public void findByOpenList_Test() {
        List<Resume> resumeOpenList = resumeRepository.findByCheckUserId();
        for (Resume resume : resumeOpenList) {
        }
    }

    @Test
    public void 공개이력서목록_test() {

        List<Resume> resumeOpenList = resumeRepository.findByCheckUserId();

        List<CompUserOpenResumeDTO> compUserOpenResumeDTOList = new ArrayList<>();
        for (Resume resume : resumeOpenList) {
            List<String> skills = new ArrayList<>();
            for (HasSkill skill : resume.getHasSkillList()) {
                String skillName = skill.getSkill().getSkill();
                skills.add(skillName);
            }
            String skillFormat = String.join(" · ", skills);

            CompUserOpenResumeDTO compUserOpenResumeDTO = CompUserOpenResumeDTO.builder()
                    .resumeId(resume.getId())
                    .userName(resume.getUserName())
                    .resumePic(resume.getResumePicUrl())
                    .address(resume.getAddress())
                    .career(resume.getCareer())
                    .careerYear(resume.getCareerYear())
                    .title(resume.getTitle())
                    .openCheck(resume.getOpenCheck())
                    .userSkillList(skillFormat)
                    .build();

            compUserOpenResumeDTOList.add(compUserOpenResumeDTO);
        }
        for (CompUserOpenResumeDTO compUserOpenResumeDTO : compUserOpenResumeDTOList) {
        }
    }
}
