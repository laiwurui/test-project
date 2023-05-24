package org.pedia.core.configure;

import lombok.extern.slf4j.Slf4j;
import org.pedia.core.enums.EStatus;
import org.pedia.core.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> commonExceptionHandler(Exception e) {
        log.error("异常：", e);
        return Result.err(e);
    }

    @ExceptionHandler(value = { BindException.class, MethodArgumentNotValidException.class })
    public Result<Object> bindingExceptionHandler(Exception e) {
        log.error("绑定异常：", e);
        BindingResult bindingResult = ((BindException) e).getBindingResult();
        // 如果没有参数异常则直接返回错误信息
        if (bindingResult.hasErrors()) {
            //获取到 所有错误
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            // 如果有参数异常，则获取default message，并返回
            StringBuilder message = new StringBuilder("提示：");
            //拼接 错误
            fieldErrors.forEach(i -> message.append(i.getDefaultMessage()).append("！"));

            return Result.err(message.toString());
        } else {
            //bindingResult没有异常，但是exception有异常
            return Result.err(e);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> badRequestException(IllegalArgumentException e) {
        log.error("参数错误：", e);
        return Result.err(e, EStatus.FAIL.getMessage(), EStatus.FAIL.getStatus(), null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Object> handleError(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式错误：", e);
        return Result.err(e, EStatus.METHOD_NOT_ALLOWED.getMessage(), EStatus.METHOD_NOT_ALLOWED.getStatus(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> runtimeException(RuntimeException e) {
        log.error("运行时异常：", e);
        return Result.err(e, EStatus.INTERNAL_SERVER_ERROR.getStatus());
    }

}
