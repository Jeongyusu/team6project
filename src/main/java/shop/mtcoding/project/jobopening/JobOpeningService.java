package shop.mtcoding.project.jobopening;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project._core.util.Split;
import shop.mtcoding.project.jobopening.JobOpeningRequest.JobOpeningUpdateDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningDetailDTO;
import shop.mtcoding.project.jobopening.JobOpeningResponse.JobOpeningMainDTO;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionRepository;
import shop.mtcoding.project.position.PositionResponse.PositionNameDTO;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.position.RequiredPositionRepository;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.qualified.QualifiedRepository;
import shop.mtcoding.project.qualified.QualifiedResponse.QualifiedContentDTO;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.skill.RequiredSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillRepository;
import shop.mtcoding.project.skill.SkillResponse.SkillNameDTO;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.task.TaskRepository;
import shop.mtcoding.project.task.TaskResponse.TaskContentDTO;
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

    @Autowired
    private JobOpeningQueryRepository jobOpeningQueryRepository;

    @Transactional
    public void 공고등록(JobOpeningRequest.JobOpeningSaveDTO jobOpeningSaveDTO, Integer sessionUserId) {

        String dateString = jobOpeningSaveDTO.getDeadLine();
        LocalDate date = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getClass().toString());
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
        try {
            jobOpeningRepository.save(jobOpening);
        } catch (Exception e) {
            throw new MyException("공고 등록 중 에러가 발생했습니다. 이유 : " + e.getClass().toString());
        }

        List<String> requiredPositionList = jobOpeningSaveDTO.getPositionList();
        for (String positionName : requiredPositionList) {
            Position position = positionRepository.findByPositionName(positionName);

            RequiredPosition requiredPosition = RequiredPosition.builder()
                    .jobOpening(jobOpening)
                    .position(position)
                    .build();
            try {
                requiredPositionRepository.save(requiredPosition);
            } catch (Exception e) {
                throw new MyException("공고 등록 중 에러가 발생했습니다(포지션 등록). 이유 : " + e.getClass().toString());
            }

        }
        // 내가 선택한 체크박스 포지션 등록

        List<String> requiredSkillList = jobOpeningSaveDTO.getSkillList();
        for (String skillName : requiredSkillList) {
            Skill skill = skillRepository.findBySkillName(skillName);

            RequiredSkill requiredSkill = RequiredSkill.builder()
                    .jobOpening(jobOpening)
                    .skill(skill)
                    .build();
            try {
                requiredSkillRepository.save(requiredSkill);
            } catch (Exception e) {
                throw new MyException("공고 등록 중 에러가 발생했습니다(스킬 등록). 이유 : " + e.getClass().toString());
            }

        }
        // 내가 선택한 체크박스 스킬 등록

        List<String> qualContList = jobOpeningSaveDTO.getQualList();

        for (String qual : qualContList) {
            Qualified qualCont = Qualified.builder()
                    .jobOpening(jobOpening)
                    .qualifiedContent(qual)
                    .build();
            try {
                qualifiedRepository.save(qualCont);
            } catch (Exception e) {
                throw new MyException("공고 등록 중 에러가 발생했습니다(자격요건 등록). 이유 : " + e.getClass().toString());
            }

        }
        // 자격요건 등록

        List<String> taskContList = jobOpeningSaveDTO.getTaskList();

        for (String task : taskContList) {
            Task taskCont = Task.builder()
                    .jobOpening(jobOpening)
                    .taskContent(task)
                    .build();
            try {
                taskRepository.save(taskCont);
            } catch (Exception e) {
                throw new MyException("공고 등록 중 에러가 발생했습니다(주요업무 등록). 이유 : " + e.getClass().toString());
            }

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

            try {
                jobOpeningRepository.save(jobOpening);
            } catch (Exception e) {
                throw new MyException("공고 수정 중 에러가 발생했습니다. 이유 : " + e.getClass().toString());
            }

            List<String> positionList = jobOpeningUpdateDTO.getPositionList();
            for (String positionName : positionList) {
                Position position = positionRepository.findByPositionName(positionName);
                RequiredPosition requiredPosition = RequiredPosition.builder()
                        .jobOpening(jobOpening)
                        .position(position)
                        .build();
                try {
                    requiredPositionRepository.save(requiredPosition);
                } catch (Exception e) {
                    throw new MyException("공고 수정 중 에러가 발생했습니다(포지션 수정). 이유 : " + e.getClass().toString());
                }

            }

            List<String> skillList = jobOpeningUpdateDTO.getSkillList();
            for (String skillName : skillList) {
                Skill skill = skillRepository.findBySkillName(skillName);
                RequiredSkill requiredSkill = RequiredSkill.builder()
                        .jobOpening(jobOpening)
                        .skill(skill)
                        .build();
                try {
                    requiredSkillRepository.save(requiredSkill);
                } catch (Exception e) {
                    throw new MyException("공고 수정 중 에러가 발생했습니다(스킬 수정). 이유 : " + e.getClass().toString());
                }

            }

            qualifiedRepository.deleteByJobOpeningId(id);
            List<String> qualList = jobOpeningUpdateDTO.getQualList();

            for (String qualName : qualList) {
                Qualified requiredQualified = Qualified.builder()
                        .jobOpening(jobOpening)
                        .qualifiedContent(qualName)
                        .build();
                try {
                    qualifiedRepository.save(requiredQualified);
                } catch (Exception e) {
                    throw new MyException("에러가 발생했습니다(자격요건 수정). 이유 : " + e.getClass().toString());
                }

            }

            taskRepository.deleteByJobOpeningId(id);
            List<String> taskList = jobOpeningUpdateDTO.getTaskList();

            for (String taskName : taskList) {
                Task requiredTask = Task.builder()
                        .jobOpening(jobOpening)
                        .taskContent(taskName)
                        .build();
                try {
                    taskRepository.save(requiredTask);
                } catch (Exception e) {
                    throw new MyException("에러가 발생했습니다(주요업무 수정). 이유 : " + e.getClass().toString());
                }

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
            throw new MyException("삭제에 실패했습니다. 이유 : " + e.getClass().toString());
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
            String compAddressFormat = Split.AddressSplit(Address);

            // careeryear null 방지
            String JcareerYear = jobOpening.getCareerYear();
            if (JcareerYear == null || "null".equals(JcareerYear)) {
                JcareerYear = "";
            }
            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(JcareerYear)
                    .skill(skillListString)
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }

    /////////////////////////////////////////////////////////////////////////////

    public JobOpeningDetailDTO 상세채용공고(Integer id) {
        Optional<JobOpening> jobOpeningOP = jobOpeningRepository.findById(id);
        if (jobOpeningOP.isPresent()) {
            JobOpening jobOpening = jobOpeningOP.get();

            // 연력 날짜포맷
            Date compCreatedAt = jobOpening.getUser().getCompHistory();
            String compCreatedAtFormat = FormatDate.formatDateYear(compCreatedAt);

            // task - content - 가져오기
            List<Task> taskList = taskRepository.mfindByJobOpeningId(jobOpening.getId());
            List<TaskContentDTO> taskDetailDTOList = new ArrayList<>();
            for (Task task : taskList) {
                TaskContentDTO taskContentDTO = TaskContentDTO.builder()
                        .taskContent(task.getTaskContent())
                        .build();
                taskDetailDTOList.add(taskContentDTO);
            }
            // quelified - qualifiedContent - 가져오기
            List<Qualified> qualifiedList = qualifiedRepository.mfindByJobOpeningId(jobOpening.getId());
            List<QualifiedContentDTO> qualifiedContentDTOList = new ArrayList<>();
            for (Qualified qualified : qualifiedList) {
                QualifiedContentDTO qualifiedContentDTO = QualifiedContentDTO.builder()
                        .qualifiedContent(qualified.getQualifiedContent())
                        .build();
                qualifiedContentDTOList.add(qualifiedContentDTO);
            }

            // required Position - name 가져오기
            List<RequiredPosition> requiredPosition = requiredPositionRepository
                    .mfindByIdJoinPosition(jobOpening.getId());
            List<PositionNameDTO> positionNameDTOList = new ArrayList<>();
            for (RequiredPosition rpList : requiredPosition) {
                PositionNameDTO positionNameDTO = PositionNameDTO.builder()
                        .positionName(rpList.getPosition().getPosition())
                        .build();
                positionNameDTOList.add(positionNameDTO);
            }

            // required Skill - name 가져오기
            List<RequiredSkill> requiredSkill = requiredSkillRepository.mfindByIdJoinSkill(jobOpening.getId());
            List<SkillNameDTO> skillNameDTOList = new ArrayList<>();
            for (RequiredSkill skill : requiredSkill) {
                String skillName = skill.getSkill().getSkill();
                SkillNameDTO skillNameDTO = SkillNameDTO.builder()
                        .skillName(skillName)
                        .build();
                skillNameDTOList.add(skillNameDTO);
            }

            // careeryear null 방지
            String JcareerYear = jobOpening.getCareerYear();
            if (JcareerYear == null || "null".equals(JcareerYear)) {
                JcareerYear = "";
            }

            // view를 하기 위한 DTO
            JobOpeningDetailDTO jobOpeningDetailDTO = JobOpeningDetailDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .process(jobOpening.getProcess())
                    .career(jobOpening.getCareer())
                    .careerYear(JcareerYear)
                    .edu(jobOpening.getEdu())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(jobOpening.getCompAddress())
                    .compIntro(jobOpening.getUser().getCompIntro())
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .compFormatCreatedAt(compCreatedAtFormat)
                    .deadLine(jobOpening.getDeadLine())
                    .createdAt(jobOpening.getCreatedAt())
                    .taskList(taskDetailDTOList)
                    .qulifiedList(qualifiedContentDTOList)
                    .requiredSkillList(skillNameDTOList)
                    .requiredPositionList(positionNameDTOList)
                    .build();

            return jobOpeningDetailDTO;
        } else {
            throw new MyException("해당 채용공고를 찾을 수 없습니다.");
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public List<JobOpeningMainDTO> 포지션과스킬선택(List<Integer> positionIdList, List<Integer> skillIdList) {

        List<JobOpening> selectJobOpeningList = jobOpeningQueryRepository.findByIdJoinPositionAndSkill(positionIdList,
                skillIdList);

        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();
        // 선택된 jobOpening을 DTO에 담기 위한 List
        for (JobOpening jobOpening : selectJobOpeningList) {

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
            String compAddressFormat = Split.AddressSplit(Address);

            // careeryear null 방지
            String JcareerYear = jobOpening.getCareerYear();
            if (JcareerYear == null || "null".equals(JcareerYear)) {
                JcareerYear = "";
            }

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(JcareerYear)
                    .skill(skillListString)
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }

    //////////////////////////////////////////////////////////////////////////////////

    public List<JobOpeningMainDTO> 경력과지역선택(String career, String careerYear, String location) {

        List<JobOpening> jobCareer = null;
        List<JobOpeningMainDTO> jobOpeningMainDTOList = new ArrayList<>();

        // career = 경력/신입 ,careerYear = 1년차/2년차/3년차/4년차/5년차, location = 지역
        // career = 신입, location만 선택 가능(careerYear 옵션 비활성화)
        // career = 경력, careerYear, location 선택 가능
        // location = 전국, " "으로 처리
        if (career == null && careerYear == null && location != null) {
            jobCareer = jobOpeningQueryRepository.mFindBySelectedCareerOrCareerYearOrLocation(null, null, location);
        } else if (career == null && careerYear != null && location == null) {
            jobCareer = jobOpeningQueryRepository.mFindBySelectedCareerOrCareerYearOrLocation(null, careerYear, null);
        } else if (career != null && careerYear == null && location == null) {
            jobCareer = jobOpeningQueryRepository.mFindBySelectedCareerOrCareerYearOrLocation(career, null, null);
        } else if ((career == null || careerYear == null) && (location != null || location == " ")) {
            jobCareer = jobOpeningQueryRepository.mFindBySelectedCareerOrCareerYearAndLocation(career, careerYear,
                    location);
        }

        // jobOpening을 담기 위한 List
        for (JobOpening jobOpening : jobCareer) {

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
            String compAddressFormat = Split.AddressSplit(Address);

            // careeryear null 방지
            String JcareerYear = jobOpening.getCareerYear();
            if (JcareerYear == null || "null".equals(JcareerYear)) {
                JcareerYear = "";
            }

            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(JcareerYear)
                    .skill(skillListString)
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }

    public List<JobOpeningMainDTO> 검색후메인화면(String keyword) {

        List<JobOpening> jobOpeningList = jobOpeningRepository.mfindBySearchJobOpeningList(keyword);

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
            String compAddressFormat = Split.AddressSplit(Address);

            // careeryear null 방지
            String JcareerYear = jobOpening.getCareerYear();
            if (JcareerYear == null || "null".equals(JcareerYear)) {
                JcareerYear = "";
            }
            JobOpeningMainDTO jobOpeningMainDTO = JobOpeningMainDTO.builder()
                    .jobOpeningId(jobOpening.getId())
                    .title(jobOpening.getTitle())
                    .compName(jobOpening.getUser().getUserName())
                    .compAddress(compAddressFormat)
                    .career(jobOpening.getCareer())
                    .careerYear(JcareerYear)
                    .skill(skillListString)
                    .compPicUrl(jobOpening.getUser().getCompPicUrl())
                    .build();
            jobOpeningMainDTOList.add(jobOpeningMainDTO);
        }
        return jobOpeningMainDTOList;
    }
}