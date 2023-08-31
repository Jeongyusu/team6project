package shop.mtcoding.project.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, unique = true)
    private String userEmailId;

    @Column(length = 60, unique = true)
    private String compEmailId;

    @Column(length = 20)
    private String userName;

    @Column(length = 80)
    private String userPassword;

    private String userPicUrl;

    private String compPicUrl;

    @Column(length = 30)
    private String compName;

    @Column(length = 100)
    private String compHistory;

    @Column(length = 100)
    private String compIntro;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String userEmailId, String compEmailId, String userName, String userPassword,
            String userPicUrl, String compPicUrl, String compName, String compHistory, String compIntro,
            Timestamp createdAt) {
        this.id = id;
        this.userEmailId = userEmailId;
        this.compEmailId = compEmailId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPicUrl = userPicUrl;
        this.compPicUrl = compPicUrl;
        this.compName = compName;
        this.compHistory = compHistory;
        this.compIntro = compIntro;
        this.createdAt = createdAt;
    }

    

}
