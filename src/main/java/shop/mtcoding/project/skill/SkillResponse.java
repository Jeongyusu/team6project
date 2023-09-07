package shop.mtcoding.project.skill;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SkillResponse {

    @Getter
    @Setter
    @Builder
    public static class HasSkillResponseDTO {
        String skill;
    }

    @Getter
    @Setter
    public static class SkillNameDTO {
        private String skillName;

        @Builder
        public SkillNameDTO(String skillName) {
            this.skillName = skillName;
        }

    }

    @Getter
    @Setter
    public static class RequiredSkillResponseDTO {
        String skill;

        @Builder
        public RequiredSkillResponseDTO(String skill) {
            this.skill = skill;
        }
    }
}