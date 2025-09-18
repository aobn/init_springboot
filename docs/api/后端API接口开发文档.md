# 后端API接口开发文档

## 文档信息
- **项目名称**: Spring Boot Demo
- **版本**: v1.0.0
- **创建时间**: 2025-09-19
- **作者**: CodeBuddy
- **Base URL**: `http://localhost:8080/api/`

## 目录
- [1. 接口规范](#1-接口规范)
- [2. 统一响应格式](#2-统一响应格式)
- [3. 状态码定义](#3-状态码定义)

## 1. 接口规范

### 1.1 基础信息
| 项目 | 内容 | 说明 |
|------|------|------|
| **Base URL** | `http://localhost:8080/api/` | 接口基础路径 |
| **Content-Type** | `application/json` | 统一采用JSON格式 |
| **字符编码** | `UTF-8` | 确保多语言字符兼容 |
| **时间格式** | `ISO 8601` | 示例：`2025-01-09T10:30:00Z` |
| **请求方法** | 仅支持GET和POST | GET（查询）、POST（创建/更新/删除） |

### 1.2 参数传输规范
- **GET请求**: 查询参数通过JSON格式在请求体中传输（特殊情况下可使用URL参数）
- **POST请求**: 所有参数统一使用JSON格式在请求体中传输
- **路径参数**: 资源ID等关键参数可在URL路径中传递
- **禁用方法**: 不支持PUT、DELETE、PATCH等其他HTTP方法

### 1.3 参数命名规则

#### 1.3.1 基本命名规范
- **命名风格**: 统一使用驼峰命名法（camelCase）
- **长度**: 参数名长度建议3-30个字符
- **可读性**: 参数名应具有明确的业务含义

#### 1.3.2 常用参数命名标准
| 参数类型 | 命名规范 | 示例 | 说明 |
|----------|----------|------|------|
| **ID类** | 以Id结尾 | `userId`, `adminId`, `orderId` | 唯一标识符 |
| **时间类** | 以Time结尾 | `createTime`, `updateTime`, `expireTime` | 时间戳字段 |
| **状态类** | 以Status结尾 | `userStatus`, `orderStatus` | 状态枚举值 |
| **数量类** | 以Count/Num结尾 | `totalCount`, `pageNum`, `itemCount` | 数值统计 |
| **标志类** | 以Flag/Is开头 | `isActive`, `deleteFlag`, `isEnabled` | 布尔值 |
| **列表类** | 以List结尾 | `userList`, `dataList`, `itemList` | 数组/集合 |
| **分页类** | 标准分页参数 | `pageNum`, `pageSize`, `totalCount` | 分页查询 |

#### 1.3.3 特殊业务参数规范
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

#### 1.3.4 禁用命名规范
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

#### 1.3.5 参数验证规范
| 验证类型 | 规则说明 | 示例 |
|----------|----------|------|
| **必填验证** | 标注required字段 | `username`(必填), `email`(必填) |
| **长度验证** | 指定最小/最大长度 | `username`(3-20字符) |
| **格式验证** | 正则表达式验证 | `email`(邮箱格式), `phone`(手机号格式) |
| **枚举验证** | 限定可选值 | `role`(USER/ADMIN), `status`(ACTIVE/INACTIVE) |
| **数值验证** | 范围限制 | `pageSize`(1-100), `age`(1-150) |

#### 1.3.6 参数命名示例对照表

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

### 1.4 认证方式
- **JWT Token**: 在请求头中携带 `Authorization: Bearer <token>`
- **公开接口**: 注册、登录等接口无需认证
- **保护接口**: 需要有效的JWT Token

## 2. 统一响应格式

所有API接口统一使用以下响应格式：

```typescript
interface ApiResponse<T> {
  code: number;           // 业务状态码
  message: string;        // 响应描述信息
  data: T | null;         // 响应数据
  timestamp: string;      // 服务器响应时间戳
}
```

### 2.1 成功响应示例
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

### 2.2 失败响应示例
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

## 3. 状态码定义

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

## 4. 示例
### 4.1 获取所有用户
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




## 注意事项
**注意事项**:
1. 所有接口都使用统一的响应格式
2. 所有参数统一使用JSON格式传输，不支持表单提交
3. 仅支持GET和POST请求方法，不开放PUT、DELETE等其他方法
4. 需要认证的接口必须在请求头中携带有效的JWT Token
5. 参数验证失败时会返回详细的错误信息
6. 生产环境中应配置HTTPS和适当的安全策略
7. 建议使用API版本控制来管理接口变更