package shop.mtcoding.project._core.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUtil<T> {
    private boolean sucuess; // true
    private T data; // 댓글쓰기 성공

    public ApiUtil(boolean success, T data) {
        this.sucuess = success;
        this.data = data;
    }
}
