package shop.mtcoding.project.jobopening;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.jobopening.JobOpeningRequest.JobOpeningUpdateDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
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

        List<String> requiredPositionList = jobOpeningSaveDTO.getPositionList();
        for (String positionName : requiredPositionList) {
            Position position = positionRepository.findByPositionName(positionName);

            RequiredPosition requiredPosition = RequiredPosition.builder()
                    .jobOpening(jobOpening)
                    .position(position)
                    .build();

            requiredPositionRepository.save(requiredPosition);
        }
        // 내가 선택한 체크박스 포지션 등록

        List<String> requiredSkillList = jobOpeningSaveDTO.getSkillList();
        for (String skillName : requiredSkillList) {
            Skill skill = skillRepository.findBySkillName(skillName);

            RequiredSkill requiredSkill = RequiredSkill.builder()
                    .jobOpening(jobOpening)
                    .skill(skill)
                    .build();
            requiredSkillRepository.save(requiredSkill);
        }
        // 내가 선택한 체크박스 스킬 등록

        List<String> qualContList = jobOpeningSaveDTO.getQualList();

        for (String qual : qualContList) {
            Qualified qualCont = Qualified.builder()
                    .jobOpening(jobOpening)
                    .qualifiedContent(qual)
                    .build();
            qualifiedRepository.save(qualCont);
        }
        // 자격요건 등록

        List<String> taskContList = jobOpeningSaveDTO.getTaskList();

        for (String task : taskContList) {
            Task taskCont = Task.builder()
                    .jobOpening(jobOpening)
                    .taskContent(task)
                    .build();
            taskRepository.save(taskCont);
        }
        // 주요업무 등록
    }

    public JobOpening 공고수정페이지(Integer id) {
        Optional<JobOpening> jobOpening = jobOpeningRepository.findById(id);

        if (jobOpening.isPresent()) {
            return jobOpening.get();
        } else {
            throw new MyException("해당 채용공고를 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void 공고수정(JobOpeningUpdateDTO jobOpeningUpdateDTO, Integer id) {
        Optional<JobOpening> jobOpeningOP = jobOpeningRepository.findById(id);

        if (jobOpeningOP.isPresent()) {

            JobOpening jobOpening = jobOpeningOP.get();

            // 업데이트할 내용 설정
            jobOpening.setId(jobOpeningUpdateDTO.getId());
            jobOpening.setTitle(jobOpeningUpdateDTO.getTitle());
            jobOpening.setCareer(jobOpeningUpdateDTO.getCareer());
            jobOpening.setCareerYear(jobOpeningUpdateDTO.getCareerYear());
            jobOpening.setEdu(jobOpeningUpdateDTO.getEdu());
            jobOpening.setCompAddress(jobOpeningUpdateDTO.getCompAddress());
            jobOpening.setProcess(jobOpeningUpdateDTO.getProcess());
            jobOpening.setUser(jobOpeningUpdateDTO.getUser());
            jobOpeningRepository.save(jobOpening);

            List<String> positionList = jobOpeningUpdateDTO.getPositionList();
            for (String positionName : positionList) {
                Position position = positionRepository.findByPositionName(positionName);
                RequiredPosition requiredPosition = RequiredPosition.builder()
                        .jobOpening(jobOpening)
                        .position(position)
                        .build();
                requiredPositionRepository.save(requiredPosition);
            }

            List<String> skillList = jobOpeningUpdateDTO.getSkillList();
            for (String skillName : skillList) {
                Skill skill = skillRepository.findBySkillName(skillName);
                RequiredSkill requiredSkill = RequiredSkill.builder()
                        .jobOpening(jobOpening)
                        .skill(skill)
                        .build();
                requiredSkillRepository.save(requiredSkill);

            }

            qualifiedRepository.deleteByJobOpeningId(id);
            List<String> qualList = jobOpeningUpdateDTO.getQualList();

            for (String qualName : qualList) {
                Qualified requiredQualified = Qualified.builder()
                        .jobOpening(jobOpening)
                        .qualifiedContent(qualName)
                        .build();
                qualifiedRepository.save(requiredQualified);
            }

            taskRepository.deleteByJobOpeningId(id);
            List<String> taskList = jobOpeningUpdateDTO.getTaskList();

            for (String taskName : taskList) {
                Task requiredTask = Task.builder()
                        .jobOpening(jobOpening)
                        .taskContent(taskName)
                        .build();
                taskRepository.save(requiredTask);
            }

        } else {
            throw new MyException(id + "를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void 공고삭제(Integer id) {
        List<RequiredSkill> requiredSkillList = requiredSkillRepository.findByJobOpeningId(id);
        for (RequiredSkill requiredSkill : requiredSkillList) {
            requiredSkill.setJobOpening(null);
            requiredSkillRepository.save(requiredSkill);
        }
        List<RequiredPosition> requiredPositionList = requiredPositionRepository.findByJobOpeningId(id);
        for (RequiredPosition requiredPosition : requiredPositionList) {
            requiredPosition.setJobOpening(null);
            requiredPositionRepository.save(requiredPosition);
        }
        try {
            jobOpeningRepository.deleteById(id);
        } catch (Exception e) {
            throw new MyException("삭제에 실패했습니다.");
        }
    }

    public List<JobOpeningMainDTO> 메인화면() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.mfindByAllJoinJobOpeningAndUser();

        // jobOpening을 담기 위한 List
        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningList) {

            // skillName을 담기 위한 List
            List<String> skillName = new ArrayList<>();
            for (RequiredSkill requiredSkill : jobOpening.getRequiredSkillList()) {
                String skill = requiredSkill.getSkill().getSkill();
                skillName.add(skill);
            }

            // 이중 for문을 방지하기 위해, 배열을 하나의 문자열로 만들기
            String skillListString = String.join(" · ", skillName);

            // 주소 포맷
            String Address = jobOpening.getCompAddress();
            String compAddressFormat = shop.mtcoding.project._core.util.Split.AddressSplit(Address);

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(jobOpening.getCareerYear())
                    .skill(skillListString)
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }

}
