package shop.mtcoding.project.jobOpening;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.jobopening.JobOpeningRepository;
import shop.mtcoding.project.position.RequiredPositionRepository;

@DataJpaTest
public class JobOpeningRepositoryTest {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Test
    public void findById_test() {
        Optional<JobOpening> jobOpeningOP = jobOpeningRepository.findById(4);
    }
}
