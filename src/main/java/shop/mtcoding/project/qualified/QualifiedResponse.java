package shop.mtcoding.project.qualified;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class QualifiedResponse {

    // 채용공고 자격요건 content DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class QualifiedContentDTO {
        private String qualifiedContent;

        @Builder
        public QualifiedContentDTO(String qualifiedContent) {
            this.qualifiedContent = qualifiedContent;
        }
    }
}