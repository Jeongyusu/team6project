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
import shop.mtcoding.project.jobopening.JobOpening;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "required_skill_tb")
@Entity
public class RequiredSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @Builder
    public RequiredSkill(Integer id, Skill skill, JobOpening jobOpening) {
        this.id = id;
        this.skill = skill;
        this.jobOpening = jobOpening;
    }

}
