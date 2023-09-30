package shop.mtcoding.project.task;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TaskResponse {

    // 채용공고 주요업무에 대한 DTO
    @NoArgsConstructor
    @Getter
    @Setter
    public static class TaskContentDTO {
        private String taskContent;

        @Builder
        public TaskContentDTO(String taskContent) {
            this.taskContent = taskContent;
        }
    }
}