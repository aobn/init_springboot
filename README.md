# SpringBoot Demo Project

## 项目简介
这是一个标准的SpringBoot项目，包含用户管理、认证授权等基础功能。

## 技术栈
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Maven

## 项目结构
```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/          # 配置类
│   │   ├── controller/      # 控制器
│   │   ├── service/         # 服务层
│   │   ├── repository/      # 数据访问层
│   │   ├── entity/          # 实体类
│   │   ├── dto/             # 数据传输对象
│   │   ├── common/          # 公共类
│   │   ├── security/        # 安全相关
│   │   └── utils/           # 工具类
│   └── resources/
│       ├── application*.yml # 配置文件
│       └── db/migration/    # 数据库迁移脚本
└── test/                    # 测试代码
```

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 运行步骤
1. 克隆项目
2. 配置数据库连接（application-dev.yml）
3. 运行 `mvn spring-boot:run`
4. 访问 http://localhost:8080

## API文档
详细API文档请查看 `docs/api/` 目录。

## 开发规范
请遵循项目中的代码规范，详见 `docs/rules/` 目录。
