package shop.mtcoding.project.skill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SkillRequest {

    @Getter
    @Setter
    @Entity
    public static class MySkill {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private Integer resumeId;
        private Integer skillId;
        private String skill;
    }

}