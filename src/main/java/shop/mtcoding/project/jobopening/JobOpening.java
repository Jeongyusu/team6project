package shop.mtcoding.project.jobopening;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.position.RequiredPosition;
import shop.mtcoding.project.qualified.Qualified;
import shop.mtcoding.project.scrap.UserScrap;
import shop.mtcoding.project.skill.RequiredSkill;
import shop.mtcoding.project.task.Task;
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "job_opening_tb")
@Entity
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String title;

    @Column(nullable = false)
    private String process;

    @Column(nullable = false)
    private String career;

    @Column(nullable = true)
    private String careerYear;

    @Column(nullable = false)
    private String edu;

    @Column(nullable = false)
    private String compAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<UserScrap> userScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RequiredSkill> requiredSkillList = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RequiredPosition> requiredPositionList = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Qualified> qualifiedList = new ArrayList<>();

    @Builder
    public JobOpening(Integer id, String title, String process, String career, String careerYear, String edu,
            String compAddress, LocalDate deadLine, Timestamp createdAt, User user) {
        this.id = id;
        this.title = title;
        this.process = process;
        this.career = career;
        this.careerYear = careerYear;
        this.edu = edu;
        this.compAddress = compAddress;
        this.deadLine = deadLine;
        this.createdAt = createdAt;
        this.user = user;
    }

}