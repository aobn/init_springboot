# Java代码命名规则文档

## 目录
- [1. 概述](#1-概述)
- [2. 基本命名原则](#2-基本命名原则)
- [3. Java类命名规范](#3-java类命名规范)
- [4. Java方法命名规范](#4-java方法命名规范)
- [5. Java变量命名规范](#5-java变量命名规范)
- [6. Java文件命名规范](#6-java文件命名规范)
- [7. Java包命名规范](#7-java包命名规范)
- [8. 代码命名示例对照](#8-代码命名示例对照)
- [9. 命名规范总结](#9-命名规范总结)

## 1. 概述

本文档定义了Java项目开发中的命名规范，旨在提高代码的可读性、可维护性和团队协作效率。所有开发人员必须严格遵循本规范。

## 2. 基本命名原则

### 2.1 通用原则
- **见名知意**：命名应清晰表达其用途和含义
- **统一性**：同类型的命名保持一致的风格
- **简洁性**：在保证清晰的前提下尽量简洁
- **英文命名**：禁止使用拼音或中英文混合
- **避免缩写**：除非是广泛认知的缩写（如id、url、dto等）

### 2.2 禁用命名
- 拼音命名：`shuju`、`yonghu`
- 无意义命名：`a`、`temp`、`data1`
- 中英文混合：`userShuJu`、`getData数据`
- 过度缩写：`usr`、`pwd`、`mgr`

## 3. Java类命名规范
| 类型 | 命名规范 | 示例 | 说明 |
|------|----------|------|------|
| **实体类** | 大驼峰+Entity后缀 | `UserEntity`, `OrderEntity` | 数据库实体映射类 |
| **控制器类** | 大驼峰+Controller后缀 | `UserController`, `AuthController` | REST控制器 |
| **服务类** | 大驼峰+Service后缀 | `UserService`, `EmailService` | 业务逻辑服务类 |
| **服务实现类** | 大驼峰+ServiceImpl后缀 | `UserServiceImpl` | 服务接口实现类 |
| **数据访问类** | 大驼峰+Mapper/Repository后缀 | `UserMapper`, `UserRepository` | 数据访问层 |
| **配置类** | 大驼峰+Config后缀 | `DatabaseConfig`, `SecurityConfig` | 配置类 |
| **工具类** | 大驼峰+Utils后缀 | `DateUtils`, `StringUtils` | 工具类 |
| **常量类** | 大驼峰+Constants后缀 | `ApiConstants`, `SystemConstants` | 常量定义类 |
| **异常类** | 大驼峰+Exception后缀 | `BusinessException`, `ValidationException` | 自定义异常类 |
| **DTO类** | 大驼峰+DTO后缀 | `UserDTO`, `LoginDTO` | 数据传输对象 |
| **VO类** | 大驼峰+VO后缀 | `UserVO`, `ResponseVO` | 视图对象 |

## 4. Java方法命名规范
| 方法类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **查询方法** | get/find/query开头 | `getUserById()`, `findUserByEmail()` | 数据查询 |
| **保存方法** | save/create/add开头 | `saveUser()`, `createOrder()` | 数据保存 |
| **更新方法** | update/modify开头 | `updateUser()`, `modifyPassword()` | 数据更新 |
| **删除方法** | delete/remove开头 | `deleteUser()`, `removeById()` | 数据删除 |
| **验证方法** | validate/check/verify开头 | `validateEmail()`, `checkPassword()` | 数据验证 |
| **转换方法** | convert/transform/to开头 | `convertToDTO()`, `toEntity()` | 数据转换 |
| **判断方法** | is/has/can开头 | `isActive()`, `hasPermission()` | 布尔判断 |
| **处理方法** | handle/process开头 | `handleRequest()`, `processData()` | 业务处理 |

## 5. Java变量命名规范
| 变量类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **局部变量** | 小驼峰命名 | `userName`, `createTime` | 方法内部变量 |
| **成员变量** | 小驼峰命名 | `private String userName` | 类的属性字段 |
| **静态变量** | 全大写+下划线 | `public static final String DEFAULT_PASSWORD` | 静态常量 |
| **集合变量** | 复数形式 | `List<User> users`, `Map<String, Object> params` | 集合类型 |
| **布尔变量** | is/has/can开头 | `isActive`, `hasPermission`, `canDelete` | 布尔类型 |
| **临时变量** | 简短有意义 | `temp`, `result`, `count` | 临时使用 |

## 6. Java文件命名规范
| 文件类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **Java类文件** | 与类名一致 | `UserController.java` | 类名与文件名完全一致 |
| **接口文件** | I开头或直接命名 | `UserService.java`, `IUserService.java` | 服务接口 |
| **配置文件** | 小写+连字符 | `application.yml`, `logback-spring.xml` | 配置文件 |
| **SQL文件** | 小写+下划线 | `create_user_table.sql` | 数据库脚本 |
| **测试文件** | 类名+Test | `UserControllerTest.java` | 单元测试 |

## 7. Java包命名规范
| 包类型 | 命名规范 | 示例 | 说明 |
|--------|----------|------|------|
| **根包** | 公司域名倒置 | `com.example.demo` | 项目根包 |
| **控制器包** | controller | `com.example.demo.controller` | REST控制器 |
| **服务包** | service | `com.example.demo.service` | 业务服务层 |
| **数据访问包** | mapper/repository | `com.example.demo.mapper` | 数据访问层 |
| **实体包** | entity/model | `com.example.demo.entity` | 实体类 |
| **配置包** | config | `com.example.demo.config` | 配置类 |
| **工具包** | util/utils | `com.example.demo.util` | 工具类 |
| **常量包** | constant | `com.example.demo.constant` | 常量定义 |
| **异常包** | exception | `com.example.demo.exception` | 异常类 |
| **DTO包** | dto | `com.example.demo.dto` | 数据传输对象 |

## 8. 代码命名示例对照

##### ✅ 正确的Java命名示例
```java
// 包命名
package com.example.demo.controller;

// 导入
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

// 类命名
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // 成员变量
    private final UserService userService;
    private static final String DEFAULT_ROLE = "USER";
    
    // 构造方法
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // 方法命名
    @GetMapping("/{id}")
    public ApiResponse<UserEntity> getUserById(@PathVariable Long userId) {
        // 局部变量
        UserEntity userEntity = userService.findById(userId);
        boolean isActive = userEntity.getIsActive();
        
        if (isActive) {
            return ApiResponse.success(userEntity);
        }
        return ApiResponse.error("用户未激活");
    }
    
    // 保存方法
    @PostMapping
    public ApiResponse<UserEntity> createUser(@RequestBody UserDTO userDTO) {
        UserEntity savedUser = userService.saveUser(userDTO);
        return ApiResponse.success(savedUser);
    }
    
    // 验证方法
    private boolean validateUserData(UserDTO userDTO) {
        return userDTO != null && 
               StringUtils.isNotBlank(userDTO.getUsername());
    }
}

// 服务类
@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    @Override
    public UserEntity findById(Long userId) {
        return userMapper.selectById(userId);
    }
    
    @Override
    public UserEntity saveUser(UserDTO userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        userMapper.insert(userEntity);
        return userEntity;
    }
    
    private UserEntity convertToEntity(UserDTO userDTO) {
        // 转换逻辑
        return new UserEntity();
    }
}
```

##### ❌ 错误的Java命名示例
```java
// 错误示例，不要这样命名
package com.example.demo.Controller;  // 包名应该小写

import com.example.demo.entity.user;   // 类名应该大驼峰

@RestController
public class usercontroller {          // 类名应该大驼峰
    
    private final UserService User_Service;  // 变量名不应该有下划线
    private static final String default_role = "USER";  // 常量应该全大写
    
    @GetMapping("/{id}")
    public ApiResponse<UserEntity> get_user_by_id(@PathVariable Long user_id) {  // 方法名应该小驼峰
        UserEntity User = User_Service.findById(user_id);  // 变量名应该小驼峰
        boolean IsActive = User.getIsActive();  // 变量名应该小驼峰
        
        return ApiResponse.success(User);
    }
    
    private boolean validate_user_data(UserDTO userDTO) {  // 方法名应该小驼峰
        return true;
    }
}
```

## 9. 命名规范总结

### 9.1 快速参考表

| 层级 | 命名规范 | 示例 | 说明 |
|------|----------|------|------|
| **包名** | 全小写+点分隔 | `com.example.demo.controller` | 域名倒置+功能模块 |
| **类名** | 大驼峰+功能后缀 | `UserController`, `UserService` | 首字母大写+功能描述 |
| **方法名** | 小驼峰+动词开头 | `getUserById()`, `saveUser()` | 动词+名词的组合 |
| **变量名** | 小驼峰 | `userName`, `createTime` | 首字母小写的驼峰 |
| **常量名** | 全大写+下划线 | `DEFAULT_PASSWORD`, `MAX_RETRY_COUNT` | 全大写+下划线分隔 |
| **文件名** | 与类名一致 | `UserController.java` | 完全匹配类名 |

### 9.2 命名检查清单

在代码提交前，请确认以下命名规范：

- [ ] 包名全部小写，使用点分隔
- [ ] 类名使用大驼峰命名，包含功能后缀
- [ ] 方法名使用小驼峰命名，动词开头
- [ ] 变量名使用小驼峰命名，见名知意
- [ ] 常量名全部大写，下划线分隔
- [ ] 文件名与类名完全一致
- [ ] 所有命名均使用英文，避免拼音和缩写

### 9.3 常见命名错误及修正

| 错误命名 | 正确命名 | 说明 |
|----------|----------|------|
| `getUserinfo()` | `getUserInfo()` | 驼峰命名规范 |
| `user_name` | `userName` | Java使用驼峰而非下划线 |
| `getdata()` | `getData()` | 方法名应清晰表达含义 |
| `UserDao` | `UserMapper` | 使用项目统一的命名后缀 |
| `validate_email` | `validateEmail` | 方法名使用驼峰命名 |
| `DEFAULT_password` | `DEFAULT_PASSWORD` | 常量全部大写 |

### 9.4 IDE配置建议

为确保命名规范的执行，建议在IDE中配置以下检查规则：

1. **IDEA设置**：
   - File → Settings → Editor → Inspections
   - 启用Java命名约定检查
   - 配置自定义命名模式

2. **Eclipse设置**：
   - Window → Preferences → Java → Code Style
   - 配置命名约定规则

3. **代码模板**：
   - 创建符合规范的类、方法模板
   - 自动生成标准的getter/setter方法

---

**文档版本**：v1.0.0  
**最后更新**：2025-01-09  
**维护人员**：开发团队
