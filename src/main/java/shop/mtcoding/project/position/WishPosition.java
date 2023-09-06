package shop.mtcoding.project.position;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.project.resume.Resume;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Table(name = "wish_position_tb")
@Entity
public class WishPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Position position;

    @Builder
    public WishPosition(Integer id, Resume resume, Position position) {
        this.id = id;
        this.resume = resume;
        this.position = position;
    }

}