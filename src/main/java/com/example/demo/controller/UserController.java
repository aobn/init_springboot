package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.UserIdRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户API控制器
 * 
 * @author xxh
 * @since 2025-09-19
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     * 接口标识: USER_LIST
     */
    @PostMapping("/list")
    public ApiResponse<List<UserEntity>> getAllUsers(@RequestBody(required = false) Object params) {
        List<UserEntity> users = userService.getAllUsers();
        return ApiResponse.success("获取用户列表成功", users);
    }

    /**
     * 根据ID获取用户
     * 接口标识: USER_GET_BY_ID
     */
    @PostMapping("/getById")
    public ApiResponse<UserEntity> getUserById(@RequestBody UserIdRequest request) {
        UserEntity user = userService.getUserById(request.getUserId());
        if (user != null) {
            return ApiResponse.success("获取用户成功", user);
        } else {
            return ApiResponse.error(404, "用户不存在");
        }
    }

    /**
     * 创建用户
     * 接口标识: USER_CREATE
     */
    @PostMapping
    public ApiResponse<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return ApiResponse.success("创建用户成功", createdUser);
    }
}