# 后端API接口开发规则文档

## 目录
- [1. 概述](#1-概述)
- [2. 接口规范](#2-接口规范)
- [3. 参数命名规范](#3-参数命名规范)
- [4. 数据库字段命名规范](#4-数据库字段命名规范)
- [5. 统一响应格式](#5-统一响应格式)
- [6. 状态码定义](#6-状态码定义)
- [7. 认证与安全](#7-认证与安全)
- [8. 接口示例](#8-接口示例)
- [9. 开发规范](#9-开发规范)
- [10. 测试规范](#10-测试规范)

## 1. 概述

### 1.1 文档目标
本文档定义了后端API接口开发的标准规范，包括接口设计、参数格式、响应逻辑等核心要素，确保前后端开发的一致性、可测试性及协作效率。

### 1.2 设计原则
- **接口优先**: API设计先行于代码实现，所有开发基于已确认的接口合约开展
- **版本管理**: 支持API版本迭代，版本变更需同步更新合约并通知相关方
- **标准化**: 统一请求/响应格式、数据类型、状态码等基础规范
- **可测试**: 每个接口需包含明确的测试用例，支持独立验证
- **可追溯**: 合约变更需记录版本号、修改内容、责任人及时间

## 2. 接口规范

### 2.1 基础信息
| 项目 | 内容 | 说明 |
|------|------|------|
| **Base URL** | `http://localhost:8080/api/` | 接口基础路径 |
| **Content-Type** | `application/json` | 统一采用JSON格式 |
| **字符编码** | `UTF-8` | 确保多语言字符兼容 |
| **时间格式** | `ISO 8601` | 示例：`2025-01-09T10:30:00Z` |
| **请求方法** | 仅支持GET和POST | GET（查询）、POST（创建/更新/删除） |

### 2.2 参数传输规范
- **GET请求**: 查询参数通过JSON格式在请求体中传输（特殊情况下可使用URL参数）
- **POST请求**: 所有参数统一使用JSON格式在请求体中传输
- **路径参数**: 资源ID等关键参数可在URL路径中传递
- **禁用方法**: 不支持PUT、DELETE、PATCH等其他HTTP方法

### 2.3 认证方式
- **JWT Token**: 在请求头中携带 `Authorization: Bearer <token>`
- **公开接口**: 注册、登录等接口无需认证
- **保护接口**: 需要有效的JWT Token

## 3. 参数命名规范

### 3.1 基本命名规范
- **命名风格**: 统一使用驼峰命名法（camelCase）
- **长度**: 参数名长度建议3-30个字符
- **可读性**: 参数名应具有明确的业务含义

### 3.2 常用参数命名标准
| 参数类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **ID类** | 以Id结尾 | `userId`, `adminId`, `orderId` | 唯一标识符 |
| **时间类** | 以Time结尾 | `createTime`, `updateTime`, `expireTime` | 时间戳字段 |
| **状态类** | 以Status结尾 | `userStatus`, `orderStatus` | 状态枚举值 |
| **数量类** | 以Count/Num结尾 | `totalCount`, `pageNum`, `itemCount` | 数值统计 |
| **标志类** | 以Flag/Is开头 | `isActive`, `deleteFlag`, `isEnabled` | 布尔值 |
| **列表类** | 以List结尾 | `userList`, `dataList`, `itemList` | 数组/集合 |
| **分页类** | 标准分页参数 | `pageNum`, `pageSize`, `totalCount` | 分页查询 |

### 3.3 特殊业务参数规范
| 业务场景 | 参数名 | 类型 | 说明 |
|----------|--------|------|------|
| **用户认证** | `username` | String | 用户名（登录名） |
| **用户认证** | `password` | String | 密码 |
| **邮箱验证** | `email` | String | 邮箱地址 |
| **验证码** | `code` | String | 验证码（通常6位数字） |
| **令牌** | `token` | String | JWT令牌 |
| **角色权限** | `role` | String | 用户角色（USER/ADMIN等） |
| **操作类型** | `type` | String | 操作类型枚举 |
| **排序** | `sortBy` | String | 排序字段 |
| **排序** | `sortOrder` | String | 排序方向（ASC/DESC） |

### 3.4 禁用命名规范
❌ **避免使用的命名方式**:
- 拼音命名: `yonghuming`, `mima`
- 缩写命名: `usr`, `pwd`, `addr`
- 下划线命名: `user_name`, `create_time`
- 数字开头: `1stName`, `2ndEmail`
- 特殊字符: `user@name`, `user-id`

✅ **使用驼峰命名方式**:
- 驼峰命名: `userName`, `createTime`
- 语义明确: `userEmail`, `verificationCode`
- 标准后缀: `userId`, `userList`

### 3.5 参数验证规范
| 验证类型 | 规则说明 | 示例 |
|----------|----------|------|
| **必填验证** | 标注required字段 | `username`(必填), `email`(必填) |
| **长度验证** | 指定最小/最大长度 | `username`(3-20字符) |
| **格式验证** | 正则表达式验证 | `email`(邮箱格式), `phone`(手机号格式) |
| **枚举验证** | 限定可选值 | `role`(USER/ADMIN), `status`(ACTIVE/INACTIVE) |
| **数值验证** | 范围限制 | `pageSize`(1-100), `age`(1-150) |

### 3.6 参数命名示例对照表

##### ✅ 正确的参数命名示例
```json
{
  "userId": 1,
  "username": "testUser",
  "userEmail": "test@example.com",
  "createTime": "2025-09-19T01:30:00Z",
  "updateTime": "2025-09-19T01:30:00Z",
  "isActive": true,
  "userStatus": "ACTIVE",
  "roleType": "USER",
  "pageNum": 1,
  "pageSize": 10,
  "totalCount": 100,
  "sortBy": "createTime",
  "sortOrder": "DESC",
  "verificationCode": "123456",
  "expireTime": "2025-09-19T01:35:00Z",
  "userList": [
    {
      "userId": 1,
      "username": "user1"
    }
  ]
}
```

##### ❌ 错误的参数命名示例
```json
{
  "user_id": 1,           // 应该用 userId
  "usr_name": "testUser", // 应该用 username
  "mail": "test@example.com", // 应该用 email 或 userEmail
  "create_time": "2025-09-19T01:30:00Z", // 应该用 createTime
  "is_del": true,         // 应该用 isDeleted 或 deleteFlag
  "status": "1",          // 应该用 userStatus 且值为枚举
  "page": 1,              // 应该用 pageNum
  "size": 10,             // 应该用 pageSize
  "total": 100,           // 应该用 totalCount
  "yonghuming": "user1",  // 应该用英文 username
  "mima": "password123",  // 应该用英文 password
  "1stName": "张三",      // 不能数字开头，应该用 firstName
  "user@email": "test@example.com" // 不能包含特殊字符
}
```

##### 📋 常见业务场景参数命名规范
```json
{
  // 用户相关
  "userId": 1,
  "username": "testuser",
  "userEmail": "test@example.com",
  "userPhone": "13800138000",
  "userAvatar": "http://example.com/avatar.jpg",
  "userRole": "USER",
  "userStatus": "ACTIVE",
  
  // 时间相关
  "createTime": "2025-09-19T01:30:00Z",
  "updateTime": "2025-09-19T01:30:00Z",
  "loginTime": "2025-09-19T01:30:00Z",
  "expireTime": "2025-09-19T01:35:00Z",
  
  // 分页相关
  "pageNum": 1,
  "pageSize": 10,
  "totalCount": 100,
  "totalPages": 10,
  
  // 排序相关
  "sortBy": "createTime",
  "sortOrder": "DESC",
  
  // 搜索相关
  "keyword": "搜索关键词",
  "searchType": "USERNAME",
  
  // 验证相关
  "verificationCode": "123456",
  "codeType": "REGISTER",
  
  // 状态标志
  "isActive": true,
  "isDeleted": false,
  "isEnabled": true,
  "deleteFlag": 0,
  
  // 文件相关
  "fileName": "document.pdf",
  "fileSize": 1024000,
  "fileType": "PDF",
  "filePath": "/uploads/2025/09/document.pdf"
}
```

## 4. 数据库字段命名规范

### 4.1 数据库字段命名规范
- **命名风格**: 统一使用下划线命名法（snake_case）
- **字符集**: 仅使用小写字母、数字和下划线
- **长度**: 字段名长度建议3-30个字符
- **语义性**: 字段名应具有明确的业务含义

### 4.2 数据库字段命名标准
| 字段类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **主键ID** | id | `id`, `user_id`, `admin_id` | 主键统一使用id，外键加表名前缀 |
| **时间字段** | 以_time结尾 | `create_time`, `update_time`, `delete_time` | 时间戳字段 |
| **状态字段** | 以_status结尾 | `user_status`, `order_status`, `pay_status` | 状态枚举值 |
| **标志字段** | 以_flag结尾或is_开头 | `delete_flag`, `is_active`, `is_enabled` | 布尔值标志 |
| **数量字段** | 以_count/_num结尾 | `total_count`, `page_num`, `retry_count` | 数值统计 |
| **外键字段** | 关联表名_id | `user_id`, `order_id`, `category_id` | 外键关联 |
| **索引字段** | 以_index结尾 | `sort_index`, `display_index` | 排序索引 |

### 4.3 常用数据库字段规范
| 业务场景 | 数据库字段名 | Java属性名 | JSON参数名 | 类型 | 说明 |
|----------|--------------|------------|------------|------|------|
| **用户信息** | `username` | `username` | `username` | VARCHAR(50) | 用户名 |
| **用户信息** | `password` | `password` | `password` | VARCHAR(100) | 密码（加密后） |
| **用户信息** | `email` | `email` | `email` | VARCHAR(100) | 邮箱地址 |
| **用户信息** | `phone` | `phone` | `phone` | VARCHAR(20) | 手机号码 |
| **用户信息** | `real_name` | `realName` | `realName` | VARCHAR(50) | 真实姓名 |
| **用户信息** | `avatar_url` | `avatarUrl` | `avatarUrl` | VARCHAR(255) | 头像地址 |
| **用户信息** | `user_role` | `userRole` | `userRole` | VARCHAR(20) | 用户角色 |
| **状态字段** | `user_status` | `userStatus` | `userStatus` | TINYINT | 用户状态(0:禁用,1:启用) |
| **标志字段** | `is_deleted` | `isDeleted` | `isDeleted` | TINYINT | 删除标志(0:未删除,1:已删除) |
| **标志字段** | `is_active` | `isActive` | `isActive` | TINYINT | 激活状态(0:未激活,1:已激活) |
| **时间字段** | `create_time` | `createTime` | `createTime` | TIMESTAMP | 创建时间 |
| **时间字段** | `update_time` | `updateTime` | `updateTime` | TIMESTAMP | 更新时间 |
| **时间字段** | `delete_time` | `deleteTime` | `deleteTime` | TIMESTAMP | 删除时间 |
| **时间字段** | `login_time` | `loginTime` | `loginTime` | TIMESTAMP | 最后登录时间 |
| **版本控制** | `version` | `version` | `version` | INT | 乐观锁版本号 |
| **排序字段** | `sort_order` | `sortOrder` | `sortOrder` | INT | 排序序号 |

### 4.4 数据库表命名规范
| 表类型 | 命名规范 | 示例 | 说明 |
|--------|----------|------|------|
| **业务表** | 单数形式 | `user`, `order`, `product` | 主要业务实体表 |
| **关联表** | 表名_表名 | `user_role`, `order_item` | 多对多关联表 |
| **配置表** | 以_config结尾 | `system_config`, `email_config` | 系统配置表 |
| **日志表** | 以_log结尾 | `operation_log`, `login_log` | 日志记录表 |
| **临时表** | 以temp_开头 | `temp_import`, `temp_export` | 临时数据表 |

### 4.5 字段类型映射规范
| 数据库类型 | Java类型 | JSON类型 | 说明 | 示例 |
|------------|----------|----------|------|------|
| **BIGINT** | Long | number | 主键ID、大整数 | `user_id` |
| **INT** | Integer | number | 普通整数 | `age`, `count` |
| **TINYINT** | Integer/Boolean | number/boolean | 状态标志 | `is_active` |
| **VARCHAR** | String | string | 字符串 | `username`, `email` |
| **TEXT** | String | string | 长文本 | `description`, `content` |
| **TIMESTAMP** | LocalDateTime | string | 时间戳 | `create_time` |
| **DECIMAL** | BigDecimal | number | 精确小数 | `price`, `amount` |
| **JSON** | String/Object | object | JSON数据 | `extra_data` |

### 4.6 数据库命名示例对照

##### ✅ 正确的数据库字段命名
```sql
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `user_role` varchar(20) NOT NULL DEFAULT 'USER' COMMENT '用户角色',
  `user_status` tinyint NOT NULL DEFAULT '1' COMMENT '用户状态(0:禁用,1:启用)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标志(0:未删除,1:已删除)',
  `is_active` tinyint NOT NULL DEFAULT '0' COMMENT '激活状态(0:未激活,1:已激活)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`),
  KEY `idx_user_status` (`user_status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
```

##### ❌ 错误的数据库字段命名
```sql
-- 错误示例，不要这样命名
CREATE TABLE `User` (  -- 表名应该小写
  `ID` bigint,         -- 字段名应该小写
  `UserName` varchar(50),  -- 应该用 username
  `createTime` timestamp,  -- 应该用 create_time
  `isDelete` tinyint,      -- 应该用 is_deleted
  `userRole` varchar(20),  -- 应该用 user_role
  `phoneNumber` varchar(20), -- 应该用 phone
  `emailAddress` varchar(100), -- 应该用 email
  `1stName` varchar(50),   -- 不能数字开头
  `user-status` tinyint    -- 不能使用连字符
);
```

### 4.7 数据库与API参数映射规则
为确保数据库字段与API参数的一致性，遵循以下映射规则：

| 层级 | 命名规范 | 示例 | 转换规则 |
|------|----------|------|----------|
| **数据库字段** | snake_case | `create_time`, `user_status` | 下划线分隔 |
| **Java属性** | camelCase | `createTime`, `userStatus` | 驼峰命名 |
| **JSON参数** | camelCase | `createTime`, `userStatus` | 与Java属性保持一致 |

**自动转换配置**:
```yaml
# application.yml 中配置MyBatis自动转换
mybatis:
  configuration:
    map-underscore-to-camel-case: true  # 自动转换下划线到驼峰
```

## 5. 统一响应格式

所有API接口统一使用以下响应格式：

```typescript
interface ApiResponse<T> {
  code: number;           // 业务状态码
  message: string;        // 响应描述信息
  data: T | null;         // 响应数据
  timestamp: string;      // 服务器响应时间戳
}
```

### 5.1 成功响应示例
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "username": "testuser"
  },
  "timestamp": "2025-09-19T01:30:00Z"
}
```

### 5.2 失败响应示例
```json
{
  "code": 400,
  "message": "参数验证失败",
  "data": {
    "errors": [
      {
        "field": "username",
        "message": "用户名不能为空"
      }
    ]
  },
  "timestamp": "2025-09-19T01:30:00Z"
}
```

## 6. 状态码定义

| 状态码 | 含义 | 适用场景 | 前端处理建议 |
|--------|------|----------|--------------|
| 200 | 成功 | 请求处理完成且结果正常 | 解析data展示业务数据 |
| 201 | 创建成功 | 资源创建完成 | 可跳转至详情页或提示创建结果 |
| 400 | 请求错误 | 参数格式错误、缺失必填项等 | 展示具体参数错误信息 |
| 401 | 未授权 | 未登录、token过期或无效 | 跳转至登录页 |
| 403 | 禁止访问 | 登录状态下无操作权限 | 提示"无权限" |
| 404 | 资源不存在 | 请求的URL或资源ID不存在 | 提示"资源不存在" |
| 409 | 资源冲突 | 资源已存在（如用户名重复） | 提示冲突原因 |
| 423 | 账户锁定 | 账户因安全原因被临时锁定 | 提示锁定原因及解锁方式 |
| 500 | 服务器错误 | 后端逻辑异常、数据库错误等 | 提示"系统繁忙，请稍后重试" |

## 7. 认证与安全

### 7.1 JWT认证机制
- **Token格式**: `Bearer <JWT_TOKEN>`
- **Token位置**: HTTP请求头 `Authorization`
- **Token有效期**: 24小时（可配置）
- **刷新机制**: 支持Token刷新（可选实现）

### 7.2 安全策略
- **HTTPS**: 生产环境必须使用HTTPS
- **CORS**: 配置跨域资源共享策略
- **Rate Limiting**: 实施API调用频率限制
- **Input Validation**: 严格的输入参数验证
- **SQL Injection**: 使用参数化查询防止SQL注入

### 7.3 权限控制
- **角色权限**: USER（普通用户）、ADMIN（管理员）
- **接口权限**: 基于角色的接口访问控制
- **资源权限**: 用户只能访问自己的资源

## 8. 接口示例

### 8.1 获取所有用户
- **接口标识**: `USER_LIST`
- **请求路径**: `GET /users`
- **接口描述**: 获取系统中所有用户列表
- **认证要求**: 需要JWT Token

#### 请求参数
无

#### 响应示例
```json
{
  "code": 200,
  "message": "获取用户列表成功",
  "data": [
    {
      "id": 1,
      "username": "testuser",
      "email": "test@example.com",
      "role": "USER",
      "createTime": "2025-09-19T01:30:00Z",
      "updateTime": "2025-09-19T01:30:00Z"
    }
  ],
  "timestamp": "2025-09-19T01:30:00Z"
}
```




### 8.2 用户注册接口

#### 基本信息
- **接口标识**: `AUTH_REGISTER`
- **请求路径**: `POST /auth/register/step2`
- **接口描述**: 用户通过邮箱验证码完成注册
- **认证要求**: 无需认证（公开接口）

#### 请求参数
```json
{
  "username": "testuser",
  "password": "Test123456",
  "confirmPassword": "Test123456",
  "email": "test@example.com",
  "verificationCode": "123456"
}
```

#### 响应示例
```json
{
  "code": 201,
  "message": "注册成功",
  "data": {
    "userId": 1,
    "username": "testuser",
    "email": "test@example.com",
    "createTime": "2025-09-19T01:30:00Z"
  },
  "timestamp": "2025-09-19T01:30:00Z"
}
```

### 8.3 用户登录接口

#### 基本信息
- **接口标识**: `AUTH_LOGIN`
- **请求路径**: `POST /auth/login`
- **接口描述**: 用户登录获取JWT Token
- **认证要求**: 无需认证（公开接口）

#### 请求参数
```json
{
  "username": "testuser",
  "password": "Test123456"
}
```

#### 响应示例
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "tokenType": "Bearer",
    "expiresIn": 86400,
    "userInfo": {
      "userId": 1,
      "username": "testuser",
      "email": "test@example.com",
      "role": "USER"
    }
  },
  "timestamp": "2025-09-19T01:30:00Z"
}
```


## 11. 注意事项与最佳实践

### 11.1 开发注意事项
1. **统一响应格式**: 所有接口都使用ApiResponse统一响应格式
2. **参数传输**: 统一使用JSON格式，不支持表单提交
3. **HTTP方法**: 仅支持GET和POST，不开放PUT、DELETE等方法
4. **认证机制**: 保护接口必须携带有效的JWT Token
5. **参数验证**: 失败时返回详细的错误信息
6. **安全配置**: 生产环境配置HTTPS和安全策略
7. **版本控制**: 使用API版本管理接口变更

