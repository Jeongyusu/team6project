package shop.mtcoding.project.jobOpening;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.jobopening.JobOpeningRequest;
import shop.mtcoding.project.jobopening.JobOpeningRequest.JobOpeningSaveDTO;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.skill.Skill;

@DataJpaTest
public class JobOpeningRepositoryTest {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Test
    public void save_test() {
        // List<RequiredPosition> jobPositionList = new ArrayList<>();
        // List<String> selectPositionList = jobOpeningSaveDTO.getPosition();

        // 체크한 목록 가져오기

        // 선택한 직무 정보를 포지션선택 메서드를 사용하여 받아옴

        // JobOpening jobOpening = JobOpening.builder()
        // .title(jobOpeningSaveDTO.getTitle())
        // .process(jobOpeningSaveDTO.getProcess())
        // .career(jobOpeningSaveDTO.getCareer())
        // .careerYear(jobOpeningSaveDTO.getCareerYear())
        // .edu(jobOpeningSaveDTO.getEdu())
        // .compAddress(jobOpeningSaveDTO.getCompAddress())
        // .build();

        // jobOpeningRepository.save(jobOpening);

        // List<String> requiredPositionList = jobOpeningSaveDTO.getPosition();
        // for (String positionName : requiredPositionList) {
        // Position position = positionRepository.findByName(positionName);
        // RequiredPosition requiredPosition = RequiredPosition.builder()
        // .jobOpening(jobOpening)
        // .position(position)
        // .build();

        // requiredPositionRepository.save(requiredPosition);
    }
}
