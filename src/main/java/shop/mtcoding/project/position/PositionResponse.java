package shop.mtcoding.project.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PositionResponse {
    @Getter
    @Setter
    @Builder
    public static class WishPositionResponseDTO {
        private String position;
    }

    @Getter
    @Setter
    public static class PositionNameDTO {

        private String positionName;

        @Builder
        public PositionNameDTO(String positionName) {
            this.positionName = positionName;
        }
    }

    @Getter
    @Setter
    public static class RequiredPositionResponseDTO {
        String position;

        @Builder
        public RequiredPositionResponseDTO(String position) {
            this.position = position;
        }
    }

}