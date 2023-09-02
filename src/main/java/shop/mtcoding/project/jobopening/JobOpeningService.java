package shop.mtcoding.project.jobopening;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionRepository;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillRepository;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.task.TaskRepository;
import shop.mtcoding.project.user.User;

@Service
public class JobOpeningService {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private RequiredPositionRepository requiredPositionRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private RequiredSkillRepository requiredSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private QualifiedRepository qualifiedRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public void 공고등록(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO, Integer sessionUserId) {

        String dateString = jobOpeningSaveDTO.getDeadLine();
        LocalDate date = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        // 내가 선택한 날짜 지정

        JobOpening jobOpening = JobOpening.builder()
                .title(jobOpeningSaveDTO.getTitle())
                .process(jobOpeningSaveDTO.getProcess())
                .career(jobOpeningSaveDTO.getCareer())
                .careerYear(jobOpeningSaveDTO.getCareerYear())
                .edu(jobOpeningSaveDTO.getEdu())
                .compAddress(jobOpeningSaveDTO.getCompAddress())
                .user(User.builder().id(sessionUserId).build())
                .deadLine(date)
                .build();

        jobOpeningRepository.save(jobOpening);

        List<String> requiredPositionList = jobOpeningSaveDTO.getPosition();
        for (String positionName : requiredPositionList) {
            Position position = positionRepository.findByName(positionName);

            RequiredPosition requiredPosition = RequiredPosition.builder()
                    .jobOpening(jobOpening)
                    .position(position)
                    .build();

            requiredPositionRepository.save(requiredPosition);
        }
        // 내가 선택한 체크박스 포지션 등록

        List<String> requiredSkillList = jobOpeningSaveDTO.getSkill();
        for (String skillName : requiredSkillList) {
            Skill skill = skillRepository.findByName(skillName);

            RequiredSkill requiredSkill = RequiredSkill.builder()
                    .jobOpening(jobOpening)
                    .skill(skill)
                    .build();
            requiredSkillRepository.save(requiredSkill);
        }
        // 내가 선택한 체크박스 스킬 등록

        List<String> qualContList = jobOpeningSaveDTO.getQual();

        for (String qual : qualContList) {
            Qualified qualCont = Qualified.builder()
                    .jobOpening(jobOpening)
                    .qualifiedContent(qual)
                    .build();
            qualifiedRepository.save(qualCont);
        }
        // 자격요건 등록

        List<String> taskContList = jobOpeningSaveDTO.getTask();

        for (String task : taskContList) {
            Task taskCont = Task.builder()
                    .jobOpening(jobOpening)
                    .taskContent(task)
                    .build();
            taskRepository.save(taskCont);
        }
        // 주요업무 등록
    }
}
