package com.example.demo.exception;

import com.example.demo.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 * 统一处理系统中的各种异常，返回标准的API响应格式
 * 
 * @author xxh
 * @since 2025-09-19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理请求体缺失异常
     * 当@RequestBody注解的参数缺失时触发
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("请求体解析异常: {}", e.getMessage());
        
        String message = "请求体格式错误或缺失";
        if (e.getMessage().contains("Required request body is missing")) {
            message = "请求体不能为空，请提供JSON格式的请求参数";
        } else if (e.getMessage().contains("JSON parse error")) {
            message = "JSON格式错误，请检查请求体格式";
        }
        
        return ApiResponse.badRequest(message);
    }

    /**
     * 处理参数验证异常
     * 当@Valid注解验证失败时触发
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("参数验证异常: {}", e.getMessage());
        
        StringBuilder errorMsg = new StringBuilder("参数验证失败: ");
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMsg.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
        });
        
        return ApiResponse.badRequest(errorMsg.toString());
    }

    /**
     * 处理约束验证异常
     * 当@Validated注解验证失败时触发
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintViolationException(ConstraintViolationException e) {
        logger.warn("约束验证异常: {}", e.getMessage());
        
        StringBuilder errorMsg = new StringBuilder("参数验证失败: ");
        e.getConstraintViolations().forEach(violation -> {
            errorMsg.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append("; ");
        });
        
        return ApiResponse.badRequest(errorMsg.toString());
    }

    /**
     * 处理请求参数缺失异常
     * 当@RequestParam注解的必填参数缺失时触发
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.warn("请求参数缺失异常: {}", e.getMessage());
        return ApiResponse.badRequest("缺少必填参数: " + e.getParameterName());
    }

    /**
     * 处理参数类型不匹配异常
     * 当请求参数类型转换失败时触发
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.warn("参数类型不匹配异常: {}", e.getMessage());
        Class<?> requiredType = e.getRequiredType();
        String typeName = requiredType != null ? requiredType.getSimpleName() : "未知";
        return ApiResponse.badRequest("参数类型错误: " + e.getName() + " 应为 " + typeName + " 类型");
    }

    /**
     * 处理HTTP请求方法不支持异常
     * 当使用了不支持的HTTP方法时触发
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.warn("HTTP请求方法不支持异常: {}", e.getMessage());
        return ApiResponse.badRequest("不支持的请求方法: " + e.getMethod() + "，支持的方法: " + String.join(", ", e.getSupportedMethods()));
    }

    /**
     * 处理404异常
     * 当请求的URL不存在时触发
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResponse<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
        logger.warn("404异常: {}", e.getMessage());
        return ApiResponse.notFound("请求的接口不存在: " + e.getRequestURL());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<Object> handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常: ", e);
        return ApiResponse.serverError("系统内部错误，请联系管理员");
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("非法参数异常: {}", e.getMessage());
        return ApiResponse.badRequest("参数错误: " + e.getMessage());
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleRuntimeException(RuntimeException e) {
        logger.error("运行时异常: ", e);
        return ApiResponse.serverError("系统运行异常，请稍后重试");
    }

    /**
     * 处理通用异常
     * 兜底处理所有未被上述方法捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleException(Exception e) {
        logger.error("系统异常: ", e);
        return ApiResponse.serverError("系统繁忙，请稍后重试");
    }
}