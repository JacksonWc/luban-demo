package luban.demo.gateway.result;

import lombok.Data;

@Data
public class Result<T> {
    private String message;
    private String code;
    private T data;
}
