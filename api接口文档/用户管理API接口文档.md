# 用户管理API接口文档

**作者**: xxh  
**创建时间**: 2025-09-19  
**版本**: v1.0.0  
**Base URL**: `http://localhost:8080/api/users`

## 接口概览

| 接口名称 | 请求方法 | 请求路径 | 接口描述 | 认证要求 |
|----------|----------|----------|----------|----------|
| 获取用户列表 | POST | `/list` | 获取所有用户列表 | 无 |
| 获取用户详情 | GET | `/{userId}` | 根据用户ID获取用户信息 | 无 |
| 创建用户 | POST | `/create` | 创建新用户 | 无 |
| 更新用户 | POST | `/update` | 更新用户信息 | 无 |
| 删除用户 | POST | `/delete` | 删除用户 | 无 |
| 根据用户名查询 | POST | `/findByUsername` | 根据用户名查询用户 | 无 |

## 统一响应格式

所有接口统一使用以下响应格式：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": "2025-09-19T15:30:00Z"
}
```

## 接口详情

### 1. 获取用户列表

**接口标识**: `USER_LIST`  
**请求路径**: `POST /api/users/list`  
**接口描述**: 获取系统中所有用户列表  
**认证要求**: 无

#### 请求参数
无需请求参数

#### 请求示例
```bash
curl -X POST http://localhost:8080/api/users/list \
  -H "Content-Type: application/json"
```

#### 响应示例
```json
{
  "code": 200,
  "message": "获取用户列表成功",
  "data": [
    {
      "id": 1,
      "username": "testuser"
    },
    {
      "id": 2,
      "username": "admin"
    }
  ],
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 2. 获取用户详情

**接口标识**: `USER_DETAIL`  
**请求路径**: `GET /api/users/{userId}`  
**接口描述**: 根据用户ID获取用户详细信息  
**认证要求**: 无

#### 请求参数
| 参数名 | 类型 | 位置 | 必填 | 说明 |
|--------|------|------|------|------|
| userId | Long | Path | 是 | 用户ID |

#### 请求示例
```bash
curl -X GET http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json"
```

#### 响应示例
```json
{
  "code": 200,
  "message": "获取用户信息成功",
  "data": {
    "id": 1,
    "username": "testuser"
  },
  "timestamp": "2025-09-19T15:30:00Z"
}
```

#### 错误响应
```json
{
  "code": 404,
  "message": "用户不存在",
  "data": null,
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 3. 创建用户

**接口标识**: `USER_CREATE`  
**请求路径**: `POST /api/users/create`  
**接口描述**: 创建新用户  
**认证要求**: 无

#### 请求参数
| 参数名 | 类型 | 位置 | 必填 | 说明 | 验证规则 |
|--------|------|------|------|------|----------|
| username | String | Body | 是 | 用户名 | 3-20个字符，不能为空 |

#### 请求示例
```bash
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{
    "username": "newuser"
  }'
```

#### 响应示例
```json
{
  "code": 201,
  "message": "创建用户成功",
  "data": {
    "id": 3,
    "username": "newuser"
  },
  "timestamp": "2025-09-19T15:30:00Z"
}
```

#### 错误响应
```json
{
  "code": 409,
  "message": "用户名已存在",
  "data": null,
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 4. 更新用户

**接口标识**: `USER_UPDATE`  
**请求路径**: `POST /api/users/update`  
**接口描述**: 更新用户信息  
**认证要求**: 无

#### 请求参数
| 参数名 | 类型 | 位置 | 必填 | 说明 |
|--------|------|------|------|------|
| userId | Long | Body | 是 | 用户ID |
| userDTO | Object | Body | 是 | 用户信息对象 |
| userDTO.username | String | Body | 是 | 新用户名 |

#### 请求示例
```bash
curl -X POST http://localhost:8080/api/users/update \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "userDTO": {
      "username": "updateduser"
    }
  }'
```

#### 响应示例
```json
{
  "code": 200,
  "message": "更新用户成功",
  "data": {
    "id": 1,
    "username": "updateduser"
  },
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 5. 删除用户

**接口标识**: `USER_DELETE`  
**请求路径**: `POST /api/users/delete`  
**接口描述**: 删除指定用户  
**认证要求**: 无

#### 请求参数
| 参数名 | 类型 | 位置 | 必填 | 说明 |
|--------|------|------|------|------|
| userId | Long | Body | 是 | 要删除的用户ID |

#### 请求示例
```bash
curl -X POST http://localhost:8080/api/users/delete \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1
  }'
```

#### 响应示例
```json
{
  "code": 200,
  "message": "删除用户成功",
  "data": null,
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 6. 根据用户名查询用户

**接口标识**: `USER_FIND_BY_USERNAME`  
**请求路径**: `POST /api/users/findByUsername`  
**接口描述**: 根据用户名查询用户信息  
**认证要求**: 无

#### 请求参数
| 参数名 | 类型 | 位置 | 必填 | 说明 |
|--------|------|------|------|------|
| username | String | Body | 是 | 用户名 |

#### 请求示例
```bash
curl -X POST http://localhost:8080/api/users/findByUsername \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser"
  }'
```

#### 响应示例
```json
{
  "code": 200,
  "message": "查询用户成功",
  "data": {
    "id": 1,
    "username": "testuser"
  },
  "timestamp": "2025-09-19T15:30:00Z"
}
```

## 状态码说明

| 状态码 | 含义 | 说明 |
|--------|------|------|
| 200 | 成功 | 请求处理成功 |
| 201 | 创建成功 | 资源创建成功 |
| 400 | 请求错误 | 参数验证失败或请求格式错误 |
| 404 | 资源不存在 | 请求的用户不存在 |
| 409 | 资源冲突 | 用户名已存在等冲突情况 |
| 500 | 服务器错误 | 服务器内部错误 |

## 参数验证规则

### 用户名验证
- **必填**: 用户名不能为空
- **长度**: 3-20个字符
- **唯一性**: 用户名在系统中必须唯一
- **格式**: 支持字母、数字、下划线

## 错误处理

### 参数验证错误
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
  "timestamp": "2025-09-19T15:30:00Z"
}
```

### 业务逻辑错误
```json
{
  "code": 409,
  "message": "用户名已存在",
  "data": null,
  "timestamp": "2025-09-19T15:30:00Z"
}
```

## 测试用例

### 创建用户测试
```bash
# 正常创建用户
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser123"}'

# 用户名为空（应返回400错误）
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"username": ""}'

# 用户名重复（应返回409错误）
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser123"}'
```

### 查询用户测试
```bash
# 获取所有用户
curl -X POST http://localhost:8080/api/users/list

# 根据ID查询用户
curl -X GET http://localhost:8080/api/users/1

# 查询不存在的用户（应返回404错误）
curl -X GET http://localhost:8080/api/users/999
```

## 注意事项

1. **请求格式**: 所有POST请求都使用JSON格式传递参数
2. **响应格式**: 统一使用ApiResponse格式返回数据
3. **错误处理**: 所有异常都会被全局异常处理器捕获并返回标准格式
4. **参数验证**: 使用Jakarta Validation进行参数验证
5. **数据库操作**: 使用MyBatis-Plus进行数据库操作

## 更新日志

| 版本 | 日期 | 更新内容 | 作者 |
|------|------|----------|------|
| v1.0.0 | 2025-09-19 | 初始版本，包含基础用户CRUD操作 | xxh |

---

**维护人员**: xxh  
**联系方式**: 开发团队  
**文档状态**: 当前版本