package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取所有用户列表
     * 
     * @return 用户列表响应
     */
    @PostMapping("/list")
    public ApiResponse<List<UserEntity>> getUserList() {
        try {
            List<UserEntity> userList = userService.getAllUsers();
            return ApiResponse.success("获取用户列表成功", userList);
        } catch (Exception e) {
            return ApiResponse.error("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户ID获取用户信息
     * 
     * @param request 获取用户请求
     * @return 用户信息响应
     */
    @PostMapping("/getById")
    public ApiResponse<UserEntity> getUserById(@RequestBody GetUserByIdRequest request) {
        try {
            Long userId = request.getUserId();
            UserEntity userEntity = userService.getUserById(userId);
            if (userEntity == null) {
                return ApiResponse.notFound("用户不存在");
            }
            return ApiResponse.success("获取用户信息成功", userEntity);
        } catch (Exception e) {
            return ApiResponse.error("获取用户信息失败: " + e.getMessage());
        }
    }

    /**
     * 创建新用户
     * 
     * @param userDTO 用户数据传输对象
     * @return 创建结果响应
     */
    @PostMapping("/create")
    public ApiResponse<UserEntity> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            // 检查用户名是否已存在
            UserEntity existingUser = userService.getUserByUsername(userDTO.getUsername());
            if (existingUser != null) {
                return ApiResponse.conflict("用户名已存在");
            }

            UserEntity createdUser = userService.createUser(userDTO);
            return ApiResponse.created("创建用户成功", createdUser);
        } catch (Exception e) {
            return ApiResponse.error("创建用户失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * 
     * @param userId 用户ID
     * @param userDTO 用户数据传输对象
     * @return 更新结果响应
     */
    @PostMapping("/update")
    public ApiResponse<UserEntity> updateUser(@RequestBody UpdateUserRequest request) {
        try {
            Long userId = request.getUserId();
            UserDTO userDTO = request.getUserDTO();

            // 检查用户是否存在
            UserEntity existingUser = userService.getUserById(userId);
            if (existingUser == null) {
                return ApiResponse.notFound("用户不存在");
            }

            // 检查新用户名是否与其他用户冲突
            UserEntity userWithSameName = userService.getUserByUsername(userDTO.getUsername());
            if (userWithSameName != null && !userWithSameName.getId().equals(userId)) {
                return ApiResponse.conflict("用户名已被其他用户使用");
            }

            UserEntity updatedUser = userService.updateUser(userId, userDTO);
            return ApiResponse.success("更新用户成功", updatedUser);
        } catch (Exception e) {
            return ApiResponse.error("更新用户失败: " + e.getMessage());
        }
    }

    /**
     * 删除用户
     * 
     * @param request 删除用户请求
     * @return 删除结果响应
     */
    @PostMapping("/delete")
    public ApiResponse<Void> deleteUser(@RequestBody DeleteUserRequest request) {
        try {
            Long userId = request.getUserId();

            // 检查用户是否存在
            UserEntity existingUser = userService.getUserById(userId);
            if (existingUser == null) {
                return ApiResponse.notFound("用户不存在");
            }

            boolean deleted = userService.deleteUser(userId);
            if (deleted) {
                return ApiResponse.success("删除用户成功", null);
            } else {
                return ApiResponse.error("删除用户失败");
            }
        } catch (Exception e) {
            return ApiResponse.error("删除用户失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户名查询用户
     * 
     * @param request 查询请求
     * @return 用户信息响应
     */
    @PostMapping("/findByUsername")
    public ApiResponse<UserEntity> getUserByUsername(@RequestBody FindUserByUsernameRequest request) {
        try {
            String username = request.getUsername();
            UserEntity userEntity = userService.getUserByUsername(username);
            if (userEntity == null) {
                return ApiResponse.notFound("用户不存在");
            }
            return ApiResponse.success("查询用户成功", userEntity);
        } catch (Exception e) {
            return ApiResponse.error("查询用户失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户请求对象
     */
    public static class UpdateUserRequest {
        private Long userId;
        private UserDTO userDTO;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public UserDTO getUserDTO() {
            return userDTO;
        }

        public void setUserDTO(UserDTO userDTO) {
            this.userDTO = userDTO;
        }
    }

    /**
     * 删除用户请求对象
     */
    public static class DeleteUserRequest {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    /**
     * 获取用户请求对象
     */
    public static class GetUserByIdRequest {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    /**
     * 根据用户名查询用户请求对象
     */
    public static class FindUserByUsernameRequest {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}