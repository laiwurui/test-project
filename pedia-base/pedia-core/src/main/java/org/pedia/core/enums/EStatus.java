package org.pedia.core.enums;

public enum EStatus {

    /**
     * 成功
     */
    SUCCESS(10200, "成功"),

    /**
     * 失败
     */
    FAIL(10400, "失败"),

    /**
     * 没有权限
     */
    UNAUTHORIZED(10401, "没有权限"),

    /**
     * 禁止访问
     */
    FORBIDDEN(10403, "禁止访问"),

    /**
     * 请求方式错误
     */
    METHOD_NOT_ALLOWED(10405, "请求方式错误"),

    /**
     * 不支持的媒体文件类型
     */
    UNSUPPORTED_MEDIA_TYPE(10415, "不支持的媒体文件类型"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(10500, "服务器内部错误"),

    /**
     * 网关错误
     */
    BAD_GATEWAY(10502, "网关错误"),

    /**
     * 服务器不可用
     */
    SERVICE_UNAVAILABLE(10503, "服务器不可用");

    private Integer status;

    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    EStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
