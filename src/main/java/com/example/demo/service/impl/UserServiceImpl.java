package com.example.demo.service.impl;

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
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        userMapper.insert(user);
        return user;
    }
}