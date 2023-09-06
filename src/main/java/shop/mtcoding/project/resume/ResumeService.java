package shop.mtcoding.project.resume;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyApiException;
import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project._core.vo.MyPath;
import shop.mtcoding.project.position.Position;
import shop.mtcoding.project.position.PositionRepository;
import shop.mtcoding.project.position.WishPosition;
import shop.mtcoding.project.position.WishPositionRepository;
import shop.mtcoding.project.resume.ResumeResponse.ApplyResumeInJobOpeningDTO;
import shop.mtcoding.project.resume.ResumeResponse.ResumeInJobOpeningDTO;
import shop.mtcoding.project.skill.HasSkill;
import shop.mtcoding.project.skill.HasSkillRepository;
import shop.mtcoding.project.skill.Skill;
import shop.mtcoding.project.skill.SkillRepository;
import shop.mtcoding.project.user.User;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    WishPositionRepository wishPositionRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    HasSkillRepository hasSkillRepository;

    @Transactional
    public void 이력서작성(ResumeRequest.UserSaveResumeDTO userSaveResumeDTO, int sessionUserId) {

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + userSaveResumeDTO.getResumePic().getOriginalFilename();
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            Files.write(filePath, userSaveResumeDTO.getResumePic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        Resume resume = Resume.builder()
                .title(userSaveResumeDTO.getTitle())
                .userName(userSaveResumeDTO.getUserName())
                .userEmailId(userSaveResumeDTO.getUserEmailId())
                .birth(userSaveResumeDTO.getBirth())
                .tel(userSaveResumeDTO.getTel())
                .address(userSaveResumeDTO.getAddress())
                .subIntro(userSaveResumeDTO.getSubIntro())
                .career(userSaveResumeDTO.getCareer())
                .careerYear(userSaveResumeDTO.getCareerYear())
                .edu(userSaveResumeDTO.getEdu())
                .openCheck(userSaveResumeDTO.getOpenCheck())
                .mainIntro(userSaveResumeDTO.getMainIntro())
                .createdAt(userSaveResumeDTO.getCreatedAt())
                .resumePicUrl(fileName)
                .user(User.builder().id(sessionUserId).build())
                .build();

        resumeRepository.save(resume);

        List<String> positionList = userSaveResumeDTO.getPositionList();
        System.out.println("테스트" + positionList.get(0));

        for (String positionName : positionList) {
            Position position = positionRepository.findByPositionName(positionName);

            WishPosition wishPosition = WishPosition.builder()
                    .resume(resume)
                    .position(position)
                    .build();

            wishPositionRepository.save(wishPosition);
        }
        // 내가 선택한 체크박스 포지션 등록

        List<String> skillList = userSaveResumeDTO.getSkillList();
        for (String skillName : skillList) {
            Skill skill = skillRepository.findBySkillName(skillName);

            HasSkill hasSkill = HasSkill.builder()
                    .resume(resume)
                    .skill(skill)
                    .build();
            hasSkillRepository.save(hasSkill);
        }
        // 내가 선택한 체크박스 스킬 등록

    }

    @Transactional
    public void 이력서수정(ResumeRequest.UserUpdateResumeDTO userUpdateResumeDTO, Integer id) {

        Optional<Resume> resumeOP = resumeRepository.findById(id);

        if (resumeOP.isPresent()) {
            Resume resume = resumeOP.get();

            UUID uuid = UUID.randomUUID();

            String fileName = "";

            fileName = uuid + "_" + userUpdateResumeDTO.getResumePic().getOriginalFilename();

            Path filePath = Paths.get(MyPath.IMG_PATH + fileName);

            try {
                Files.write(filePath, userUpdateResumeDTO.getResumePic().getBytes());
            } catch (Exception e) {
                throw new MyException(e.getMessage());
            }

            resume.setTitle(userUpdateResumeDTO.getTitle());
            resume.setUserName(userUpdateResumeDTO.getUserName());
            resume.setUserEmailId(userUpdateResumeDTO.getUserEmailId());
            resume.setBirth(userUpdateResumeDTO.getBirth());
            resume.setTel(userUpdateResumeDTO.getTel());
            resume.setAddress(userUpdateResumeDTO.getAddress());
            resume.setSubIntro(userUpdateResumeDTO.getSubIntro());
            resume.setResumePicUrl(fileName);
            resume.setCareer(userUpdateResumeDTO.getCareer());
            resume.setCareerYear(userUpdateResumeDTO.getCareerYear());
            resume.setEdu(userUpdateResumeDTO.getEdu());
            resume.setMainIntro(userUpdateResumeDTO.getMainIntro());
            resume.setOpenCheck(userUpdateResumeDTO.getOpenCheck());
            resume.setCreatedAt(userUpdateResumeDTO.getCreatedAt());
            resumeRepository.save(resume);

            List<String> positionList = userUpdateResumeDTO.getPositionList();
            for (String positionName : positionList) {
                Position position = positionRepository.findByPositionName(positionName);

                WishPosition wishPosition = WishPosition.builder()
                        .resume(resume)
                        .position(position)
                        .build();

                wishPositionRepository.save(wishPosition);
            }
            // 내가 선택한 체크박스 포지션 등록

            List<String> skillList = userUpdateResumeDTO.getSkillList();
            for (String skillName : skillList) {
                Skill skill = skillRepository.findBySkillName(skillName);

                HasSkill hasSkill = HasSkill.builder()
                        .resume(resume)
                        .skill(skill)
                        .build();
                hasSkillRepository.save(hasSkill);
            }
            // 내가 선택한 체크박스 스킬 등록

        } else {
            throw new MyException("수정시 에러가 발생했습니다.");
        }

    }

    public Resume 업데이트폼(Integer id) {

        return resumeRepository.findById(id).get();
    }

    @Transactional
    public void 삭제(Integer id) {
        List<HasSkill> hasSkillList = hasSkillRepository.findByResumeId(id);
        for (HasSkill hasSkill : hasSkillList) {
            hasSkill.setResume(null);
            hasSkillRepository.save(hasSkill);
        }

        List<WishPosition> wishPositionList = wishPositionRepository.findByResumeId(id);
        for (WishPosition wishPosition : wishPositionList) {
            wishPosition.setResume(null);
            wishPositionRepository.save(wishPosition);
        }

        try {
            resumeRepository.deleteById(id);
        } catch (Exception e) {
            throw new MyException("삭제에 실패했습니다.");
        }

    }

    public ResumeInJobOpeningDTO 지원화면() {
        // User sessionUser = (User) session.getAttribute("sessionUser");

        List<Resume> resumeList = resumeRepository.findByUserId(1);

        // 부가로직 - 이력서 null
        if (resumeList.isEmpty()) {
            throw new MyApiException("이력서를 먼저 작성해주세요.");
        }

        // 유저의 이력서 갯수
        Integer sumResume = resumeList.size();

        // 날짜포맷한 resume 담은 DTO 생성
        List<ApplyResumeInJobOpeningDTO> formatResume = new ArrayList<>();

        for (Resume resume : resumeList) {
            // 날짜포맷
            Timestamp resumeCreatedAt = resume.getCreatedAt();
            String resumeCreatedAtFormat = FormatDate.formatDate(resumeCreatedAt);

            ApplyResumeInJobOpeningDTO dtos = ApplyResumeInJobOpeningDTO.builder()
                    .resumeId(resume.getId())
                    .userTel(resume.getTel())
                    .resumeTitle(resume.getTitle())
                    .createdAtFormat(resumeCreatedAtFormat)
                    .build();

            formatResume.add(dtos);
        }

        // view를 위한 DTO 생성
        ResumeInJobOpeningDTO resumeInJobOpeningDTO = ResumeInJobOpeningDTO.builder()
                .userEmail("nnnnn@nnnnte.com")
                .applyResumeInJobOpeningDTO(formatResume)
                .totalResume(sumResume)
                .build();

        return resumeInJobOpeningDTO;

    }

}