package shop.mtcoding.project.skill;

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
import shop.mtcoding.project.resume.Resume;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "has_skill_tb")
@Entity
public class HasSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder
    public HasSkill(Integer id, Skill skill, Resume resume) {
        this.id = id;
        this.skill = skill;
        this.resume = resume;
    }

}