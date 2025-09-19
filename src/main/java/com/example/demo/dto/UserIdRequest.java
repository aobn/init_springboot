package com.example.demo.dto;

/**
 * 用户ID请求参数
 * 
 * @author xxh
 * @since 2025-09-19
 */
public class UserIdRequest {
    
    private Long userId;
    
    public UserIdRequest() {
    }
    
    public UserIdRequest(Long userId) {
        this.userId = userId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}