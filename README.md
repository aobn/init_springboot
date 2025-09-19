# Spring Boot Demo 项目

## 项目简介
这是一个基于Spring Boot的演示项目，遵循标准的Java开发规范和API设计原则。

**作者**: xxh  
**创建时间**: 2025-09-19

## 项目结构
```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── DemoApplication.java          # 应用入口
│   │       ├── config/                       # 配置类
│   │       ├── common/                       # 通用类
│   │       ├── entity/                       # 实体类
│   │       ├── service/                      # 服务层
│   │       └── service/impl/                 # 服务实现
│   └── resources/
│       ├── application.yml                   # 主配置文件
│       └── application-dev.yml               # 开发环境配置
├── api/                                      # API接口目录
│   └── UserController.java                  # 用户API控制器
└── docs/                                     # 文档目录
```

## 快速开始

### 1. 启动应用
```bash
mvn spring-boot:run
```

### 2. 访问API
应用启动后，可以通过以下地址访问API：

- 获取所有用户: `GET http://localhost:8080/api/users`
- 获取单个用户: `GET http://localhost:8080/api/users/{id}`
- 创建用户: `POST http://localhost:8080/api/users`

### 3. API响应格式
所有API都使用统一的响应格式：
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": "2025-09-19T01:30:00Z"
}
```

## 开发规范
- 严格遵循Java命名规范
- API接口统一放在 `api/` 目录下
- 使用统一的响应格式 `ApiResponse`
- 仅支持GET和POST请求方法

## 技术栈
- Spring Boot 3.2.0
- Java 17
- Maven
- MySQL 8.0
- MyBatis Plus 3.5.3
- Druid连接池
- Jackson (JSON处理)

## 数据库配置
项目已配置连接到阿里云RDS MySQL数据库：
- 主机: rm-bp1twd33b087qgv0l4o.mysql.rds.aliyuncs.com
- 端口: 3306
- 数据库: test
- 用户名: root

## 注意事项
1. 数据库连接已配置，数据持久化到MySQL
2. 使用MyBatis Plus进行数据库操作
3. 配置了Druid连接池优化性能
4. 所有时间格式使用ISO 8601标准
5. 支持数据库字段下划线自动转换为驼峰命名