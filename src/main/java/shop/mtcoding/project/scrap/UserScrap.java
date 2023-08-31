package shop.mtcoding.project.scrap;

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
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_scrap_tb")
@Entity
public class UserScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @Builder
    public UserScrap(Integer id, Timestamp createdAt, User user, JobOpening jobOpening) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.jobOpening = jobOpening;
    }

}
