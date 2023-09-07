package shop.mtcoding.project.qualified;

import javax.persistence.Column;
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
@Table(name = "qualified_tb")
@Entity

public class Qualified {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String qualifiedContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobOpening jobOpening;

    @Builder
    public Qualified(Integer id, String qualifiedContent, JobOpening jobOpening) {
        this.id = id;
        this.qualifiedContent = qualifiedContent;
        this.jobOpening = jobOpening;
    }
}