package shop.mtcoding.project.position;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PositionRequest {

    public static class WishPositionCheckboxDTO {

        @Getter
        @Setter
        private List<String> positionList;
    }

    @Getter
    @Setter
    @Builder
    public static class WishPositionResponseDTO {
        private String position;
    }
}
