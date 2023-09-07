package shop.mtcoding.project.task;

import lombok.Getter;
import lombok.Setter;

public class TaskRequest {

    @Getter
    @Setter
    public static class TaskContentDTO {
        private String taskContent;

        public TaskContentDTO(String taskContent) {
            this.taskContent = taskContent;
        }

    }
}