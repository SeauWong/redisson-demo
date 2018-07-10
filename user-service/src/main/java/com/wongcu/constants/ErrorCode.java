package com.wongcu.constants;

/**
 *
 * @author WongCU
 * @date 2018/7/10
 */
public enum ErrorCode {

    PUSH_USER_ERROR("100","推送客户发生异常"),

    ;

    public static final String APP_CODE = "USER-SERVICE";

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
