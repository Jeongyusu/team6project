package shop.mtcoding.project.suggest;

import java.sql.Timestamp;

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
import shop.mtcoding.project.jobopening.JobOpening;
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "suggest_tb")
@Entity
public class Suggest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sugState = "대기중";

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Suggest(Integer id, String sugState, Timestamp createdAt, Resume resume, JobOpening jobOpening, User user) {
        this.id = id;
        this.sugState = sugState;
        this.createdAt = createdAt;
        this.resume = resume;
        this.jobOpening = jobOpening;
        this.user = user;
    }

    

}
