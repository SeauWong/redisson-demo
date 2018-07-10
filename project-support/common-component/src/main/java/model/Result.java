package model;

import java.io.Serializable;

/**
 *
 * @author WongCU
 * @date 2018/7/10
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String cause;
    private T data;

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public Result setCause(String cause) {
        this.cause = cause;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static Boolean isSuccess(Result result){
        return null != result && "0".equals(result.getCode());
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode("0")
                .setMessage("成功")
                .setData(data);
    }

    public static Result genErrorResult(String appId, String code, String message, String cause) {
        return new Result()
            .setCode(appId + "-" + code)
            .setMessage(message)
            .setCause(cause);
    }
}
