package org.pedia.core.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.pedia.core.constant.CommonConstant;
import org.pedia.core.enums.EStatus;

import java.io.Serializable;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 9207024430780737083L;

    /**
     * 消息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误异常
     */
    private Error error;

    static class Error implements Serializable {

        private static final long serialVersionUID = 4563307809481L;
        /**
         * Exception类名
         */
        private String clazz;

        private String message;

        private String cause;

        public Error() {
        }

        public Error(String clazz, String message, String cause) {
            this.clazz = clazz;
            this.message = message;
            this.cause = cause;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }

        @Override
        public String toString() {
            return "error{" +
                    "clazz='" + clazz + '\'' +
                    ", message='" + message + '\'' +
                    ", cause='" + cause + '\'' +
                    '}';
        }
    }

    public static <T> Result<T> success() {
        return success(EStatus.SUCCESS.getMessage(), EStatus.SUCCESS.getStatus(), null);
    }

    public static <T> Result<T> success(T data) {
        return success(EStatus.SUCCESS.getMessage(), EStatus.SUCCESS.getStatus(), data);
    }


    public static <T> Result<T> success(String message, T data) {
        return success(message, EStatus.SUCCESS.getStatus(), data);
    }

    public static <T> Result<T> success(String message, Integer status ,T data) {
        return new Result<>(message,status,data,null);
    }

    public static <T> Result<T> err() {
        return new Result<>(EStatus.FAIL.getMessage(),EStatus.FAIL.getStatus(), null,null);
    }

    /**
     * 调用Result.error时，请保证传入的message是您想要的，否则请直接选择没有message入参的重载方法
     * @param e exception
     * @param <T> 泛型
     * @return Result<T>
     * @author lwr
     */
    public static <T> Result<T> err(Exception e) {
        Error error = buildError(e);
        String message = error != null ? error.getMessage() : e.getMessage();
        return new Result<>(message, EStatus.FAIL.getStatus(), null, error);
    }

    public static <T> Result<T> err(String message) {
        return new Result<>(message, EStatus.FAIL.getStatus(), null, null);
    }

    public static <T> Result<T> err(Exception e, String message) {
        return new Result<>(message, EStatus.FAIL.getStatus(), null, buildError(e));
    }

    public static <T> Result<T> err(Exception e, Integer status) {
        Error error = buildError(e);
        String message = error != null ? error.getMessage() : e.getMessage();
        return new Result<>(message, status, null, buildError(e));
    }

    public static <T> Result<T> err(String message,T data) {
        return new Result<>(message, EStatus.FAIL.getStatus(), data, null);
    }

    public static <T> Result<T> err(Exception e, String message,T data) {
        return new Result<>(message, EStatus.FAIL.getStatus(), data, buildError(e));
    }

    public static <T> Result<T> err(String message, Integer status ,T data) {
        return new Result<>(message, status, data, null);
    }

    public static <T> Result<T> err(Exception e, String message, Integer status ,T data) {
        return new Result<>(message, status, data, buildError(e));
    }

    /**
     * 根据传入的error，找到自定义的异常和异常信息，并找到异常代码行，封装成Result.Error并返回
     * 若未找到，则使用这个第三方的异常创建Result.Error对象并返回
     * @param e 系统抛出的异常
     * @return Result.Error 内部类
     * @author lwr
     */
    public static Error buildError(Throwable e) {
        Error error = findOurError(e);
        // 这是第三方代码抛出的异常  the error threw by others
        if(error == null && e != null) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            if(stackTrace != null && stackTrace.length > 0 ) {
                StackTraceElement element = Arrays.stream(stackTrace)
                        .filter(ste -> ste.getClassName().startsWith(CommonConstant.basePackage))
                        .findFirst().orElse(stackTrace[0]);
                // 找到项目中出现异常的某行
                String cause = element.toString();
                error = new Error(e.getClass().toString(), e.getMessage(), cause);
            }
        }
        return error;
    }

    /**
     * 层层递归，找到属于本系统自定义的异常并封装成Result.Error返回
     * find the error that threw by us
     * @param e 系统抛出的异常
     * @return Result.Error 内部类
     * @author lwr
     */
    private static Error findOurError(Throwable e) {
        if (e == null) {
            return null;
        }
        Error error = null;
        // 是自定义的异常
        if(e.getClass().getName().startsWith(CommonConstant.basePackage)) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            // 出现异常的某行代码 cause
            String cause = null;
            if(stackTrace != null && stackTrace.length > 0 ) {
                StackTraceElement element = Arrays.stream(stackTrace)
                        .filter(ste -> ste.getClassName().startsWith(CommonConstant.basePackage))
                        .findFirst().orElse(stackTrace[0]);
                // 找到项目中出现异常的某行
                cause = element.toString();
            }
            error = new Error(e.getClass().toString(), e.getMessage(), cause);
        } else {
            error = findOurError(e.getCause());
        }
        return error;
    }
    
}
