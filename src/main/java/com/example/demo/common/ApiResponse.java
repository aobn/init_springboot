package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 统一API响应格式
 * 
 * @author xxh
 * @since 2025-09-19
 */
public class ApiResponse<T> {
    
    private Integer code;
    private String message;
    private T data;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 成功响应 - 200
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data);
    }

    /**
     * 成功响应（无数据） - 200
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "操作成功", null);
    }

    /**
     * 成功响应（自定义消息） - 200
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    /**
     * 创建成功响应 - 201
     */
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, "创建成功", data);
    }

    /**
     * 创建成功响应（自定义消息） - 201
     */
    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(201, message, data);
    }

    /**
     * 请求错误响应 - 400
     */
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message, null);
    }

    /**
     * 未授权响应 - 401
     */
    public static <T> ApiResponse<T> unauthorized() {
        return new ApiResponse<>(401, "未授权，请先登录", null);
    }

    /**
     * 未授权响应（自定义消息） - 401
     */
    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message, null);
    }

    /**
     * 禁止访问响应 - 403
     */
    public static <T> ApiResponse<T> forbidden() {
        return new ApiResponse<>(403, "无权限访问", null);
    }

    /**
     * 禁止访问响应（自定义消息） - 403
     */
    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message, null);
    }

    /**
     * 资源不存在响应 - 404
     */
    public static <T> ApiResponse<T> notFound() {
        return new ApiResponse<>(404, "资源不存在", null);
    }

    /**
     * 资源不存在响应（自定义消息） - 404
     */
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }

    /**
     * 资源冲突响应 - 409
     */
    public static <T> ApiResponse<T> conflict(String message) {
        return new ApiResponse<>(409, message, null);
    }

    /**
     * 账户锁定响应 - 423
     */
    public static <T> ApiResponse<T> locked() {
        return new ApiResponse<>(423, "账户已锁定", null);
    }

    /**
     * 账户锁定响应（自定义消息） - 423
     */
    public static <T> ApiResponse<T> locked(String message) {
        return new ApiResponse<>(423, message, null);
    }

    /**
     * 服务器错误响应 - 500
     */
    public static <T> ApiResponse<T> serverError() {
        return new ApiResponse<>(500, "系统繁忙，请稍后重试", null);
    }

    /**
     * 服务器错误响应（自定义消息） - 500
     */
    public static <T> ApiResponse<T> serverError(String message) {
        return new ApiResponse<>(500, message, null);
    }

    /**
     * 失败响应（兼容旧版本）
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(400, message, null);
    }

    /**
     * 失败响应（自定义状态码）
     */
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // Getters and Setters
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}