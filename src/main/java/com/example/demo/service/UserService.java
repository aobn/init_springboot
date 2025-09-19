package com.example.demo.service;

import com.example.demo.dto.UserDTO;
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
     * 获取所有用户列表
     * 
     * @return 用户列表
     */
    List<UserEntity> getAllUsers();

    /**
     * 根据用户ID获取用户信息
     * 
     * @param userId 用户ID
     * @return 用户实体
     */
    UserEntity getUserById(Long userId);

    /**
     * 创建新用户
     * 
     * @param userDTO 用户数据传输对象
     * @return 创建的用户实体
     */
    UserEntity createUser(UserDTO userDTO);

    /**
     * 更新用户信息
     * 
     * @param userId 用户ID
     * @param userDTO 用户数据传输对象
     * @return 更新后的用户实体
     */
    UserEntity updateUser(Long userId, UserDTO userDTO);

    /**
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Long userId);

    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户实体
     */
    UserEntity getUserByUsername(String username);
}