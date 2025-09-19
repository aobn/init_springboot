package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.List;

/**
 * 用户服务接口
 * 
 * @author xxh
 * @since 2025-09-19
 */
public interface UserService {
    
    /**
     * 获取所有用户
     */
    List<UserEntity> getAllUsers();
    
    /**
     * 根据ID获取用户
     */
    UserEntity getUserById(Long id);
    
    /**
     * 创建用户
     */
    UserEntity createUser(UserEntity user);
}