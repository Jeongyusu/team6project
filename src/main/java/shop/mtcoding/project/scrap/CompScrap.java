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
import shop.mtcoding.project.resume.Resume;
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "comp_scrap_tb")
@Entity
public class CompScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder
    public CompScrap(Integer id, Timestamp timestamp, User user, Resume resume) {
        this.id = id;
        this.createdAt = timestamp;
        this.user = user;
        this.resume = resume;
    }

}
