package shop.mtcoding.project.jobpening;

import java.sql.Timestamp;
import java.util.List;

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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.Apply.Apply;
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

    @Column(nullable = false)
    private String careerYear;

    @Column(nullable = false)
    private String edu;

    @Column(nullable = false)
    private String compAddress;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp deadLine;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<RequiredSkill> requiredSkillList;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<Apply> applyList;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<UserScrap> userScrapList;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<Task> taskList;

    @OneToMany(mappedBy = "jobOpening", fetch = FetchType.LAZY)
    private List<Qualified> qualifiedList;

    @Builder
    public JobOpening(Integer id, String title, String process, String career, String careerYear, String edu,
            String compAddress, Timestamp deadLine, Timestamp createdAt, User user,
            List<RequiredSkill> requiredSkillList, List<Apply> applyList, List<UserScrap> userScrapList,
            List<Task> taskList, List<Qualified> qualifiedList) {
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
        this.requiredSkillList = requiredSkillList;
        this.applyList = applyList;
        this.userScrapList = userScrapList;
        this.taskList = taskList;
        this.qualifiedList = qualifiedList;
    }

}
