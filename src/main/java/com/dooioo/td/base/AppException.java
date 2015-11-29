package com.dooioo.td.base;

/**
 * 应用异常，message可以直接前台显示
 *
 * @author huang.peijie
 * @since 2015/11/29 14:58
 */
public class AppException extends RuntimeException {

    /**
     * 错误代码
     */
    public enum CodeEnum {
        未知(0),
        无权限(401);
        public int code;

        CodeEnum(int code) {
            this.code = code;
        }
    }

    private int code = 0;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
