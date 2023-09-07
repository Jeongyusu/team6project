package shop.mtcoding.project.user;

import java.sql.Date;
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
    @Column(length = 30)
    private String userName; // 통합 Name
    @Column(length = 80)
    private String userPassword;
    private String userPicUrl;
    private String compPicUrl;
    @Column(length = 100)

    private Date compHistory;

    @Column(length = 100)
    private String compIntro;
    @CreationTimestamp
    private Timestamp createdAt;
    private Integer gubun;

    // 1 -> user / 2 -> comp
    @Builder
    public User(Integer id, String userEmailId, String compEmailId, String userName, String userPassword,
            String userPicUrl, String compPicUrl, Date compHistory, String compIntro, Timestamp createdAt,
            Integer gubun) {
        this.id = id;
        this.userEmailId = userEmailId;
        this.compEmailId = compEmailId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPicUrl = userPicUrl;
        this.compPicUrl = compPicUrl;
        this.compHistory = compHistory;
        this.compIntro = compIntro;
        this.createdAt = createdAt;
        this.gubun = gubun;
    }

}