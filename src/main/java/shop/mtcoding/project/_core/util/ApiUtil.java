package shop.mtcoding.project._core.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.project.resume.Resume;

@Getter
@Setter
public class ApiUtil<T> {
    private boolean success; // true
    private T data; // 댓글쓰기 성공

    public ApiUtil(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

   
}