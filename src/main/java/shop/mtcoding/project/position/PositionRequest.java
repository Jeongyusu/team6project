package shop.mtcoding.project.position;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PositionRequest {

    public static class WishPositionCheckboxDTO {

        @Getter
        @Setter
        private List<String> positionList;
    }
}
