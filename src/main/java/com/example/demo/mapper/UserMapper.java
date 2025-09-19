package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层
 * 
 * @author xxh
 * @since 2025-09-19
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}