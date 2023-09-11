package shop.mtcoding.project.reply;

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
import shop.mtcoding.project.community.Community;
import shop.mtcoding.project.user.User;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "reply_tb")
@Entity
public class Reply {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false, length = 300)
    private String comment;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    @Builder
    public Reply(Integer id, String comment, Timestamp createdAt, User user, Community community) {
        this.id = id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.user = user;
        this.community = community;
    }

}