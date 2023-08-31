package shop.mtcoding.project.resume;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "resume_tb")
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true, length = 100)
    private String title;

    @Column(nullable = true, length = 10)
    private String age;

    @Column(nullable = true, length = 20)
    private String tel;

    @Column(nullable = true, length = 100)
    private String address;

    @Column(nullable = true, length = 200)
    private String subIntro;

    private String career;

    private String careerYear;

    private String edu;

    @Column(length = 1000)
    private String mainIntro;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Resume(Integer id, String title, String age, String tel, String address, String subIntro, String career,
            String careerYear, String edu, String mainIntro, Timestamp createdAt, User user) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.subIntro = subIntro;
        this.career = career;
        this.careerYear = careerYear;
        this.edu = edu;
        this.mainIntro = mainIntro;
        this.createdAt = createdAt;
        this.user = user;
    }

    
}
