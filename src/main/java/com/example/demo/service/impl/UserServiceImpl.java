package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 * 
 * @author xxh
 * @since 2025-09-19
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        
        userMapper.insert(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(Long userId, UserDTO userDTO) {
        UserEntity userEntity = userMapper.selectById(userId);
        if (userEntity == null) {
            return null;
        }
        
        userEntity.setUsername(userDTO.getUsername());
        userMapper.updateById(userEntity);
        return userEntity;
    }

    @Override
    public boolean deleteUser(Long userId) {
        int result = userMapper.deleteById(userId);
        return result > 0;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }
}