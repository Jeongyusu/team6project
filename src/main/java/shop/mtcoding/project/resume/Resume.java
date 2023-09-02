package shop.mtcoding.project.resume;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

    @Column(length = 20)
    private String userName;

    @Column(length = 40)
    private String userEmailId;

    @Column(length = 100)
    private String title;

    @Column(length = 10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(length = 20)
    private String tel;

    @Column(length = 100)
    private String address;

    @Column(length = 200)
    private String subIntro;

    private String career;

    private String careerYear;

    private String edu;

    private MultipartFile resumePic;

    private String openCheck;

    @Column(length = 1000)
    private String mainIntro;

    @CreationTimestamp
    private Timestamp createdAt;

    private List<String> positionList = new ArrayList<>();
    private List<String> skillist = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Resume(Integer id, String userName, String userEmailId, String title, LocalDate birth, String tel,
            String address, String subIntro, String career, String careerYear, String edu, MultipartFile resumePic,
            String openCheck, String mainIntro, Timestamp createdAt, List<String> positionList, List<String> skillist,
            User user) {
        this.id = id;
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.title = title;
        this.birth = birth;
        this.tel = tel;
        this.address = address;
        this.subIntro = subIntro;
        this.career = career;
        this.careerYear = careerYear;
        this.edu = edu;
        this.resumePic = resumePic;
        this.openCheck = openCheck;
        this.mainIntro = mainIntro;
        this.createdAt = createdAt;
        this.positionList = positionList;
        this.skillist = skillist;
        this.user = user;
    }
 
    
    
    
    
}
