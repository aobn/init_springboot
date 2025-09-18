#!/bin/bash

# SpringBoot项目结构标准化脚本
# 文件名：restructure-project.sh
# 功能：将现有项目重构为标准SpringBoot项目结构
# 作者：CodeBuddy
# 创建时间：2025-09-19
# 版本：v1.0.0

echo "开始SpringBoot项目结构标准化..."

# 1. 创建docs目录并重命名现有文档目录
echo "1. 重构文档目录..."
if [ -d "api接口文档" ]; then
    mkdir -p docs/api
    mv api接口文档/* docs/api/ 2>/dev/null || true
    rmdir "api接口文档" 2>/dev/null || true
    echo "   ✅ api接口文档 -> docs/api"
fi

if [ -d "rule" ]; then
    mkdir -p docs/rules
    mv rule/* docs/rules/ 2>/dev/null || true
    rmdir rule 2>/dev/null || true
    echo "   ✅ rule -> docs/rules"
fi

# 创建其他文档目录
mkdir -p docs/{design,deployment}
echo "   ✅ 创建设计和部署文档目录"

# 2. 创建脚本目录
echo "2. 创建脚本目录..."
mkdir -p scripts/{sql,deploy,tools}
echo "   ✅ 创建scripts目录结构"

# 3. 创建标准Java包结构
echo "3. 创建Java包结构..."
BASE_PACKAGE="src/main/java/com/example/demo"
mkdir -p ${BASE_PACKAGE}/{config,controller,service,repository,entity}
mkdir -p ${BASE_PACKAGE}/dto/{request,response}
mkdir -p ${BASE_PACKAGE}/common/{constants,enums}
mkdir -p ${BASE_PACKAGE}/{security,utils}
echo "   ✅ 创建主要包结构"

# 4. 创建测试目录结构
echo "4. 创建测试目录结构..."
TEST_PACKAGE="src/test/java/com/example/demo"
mkdir -p ${TEST_PACKAGE}/{controller,service,integration}
mkdir -p src/test/resources/test-data
echo "   ✅ 创建测试包结构"

# 5. 创建资源目录
echo "5. 创建资源目录..."
mkdir -p src/main/resources/{static,templates}
mkdir -p src/main/resources/db/migration
echo "   ✅ 创建资源目录结构"

# 6. 创建配置文件模板
echo "6. 创建配置文件模板..."

# 创建多环境配置文件
cat > src/main/resources/application.yml << 'EOF'
spring:
  profiles:
    active: dev
  application:
    name: springboot-demo

server:
  port: 8080
  servlet:
    context-path: /api/v1

logging:
  level:
    com.example.demo: INFO
    org.springframework.security: DEBUG
EOF

cat > src/main/resources/application-dev.yml << 'EOF'
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 22222hua
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

jwt:
  secret: dev-secret-key-change-in-production
  expiration: 86400000 # 24小时

logging:
  level:
    root: INFO
    com.example.demo: DEBUG
EOF

cat > src/main/resources/application-prod.yml << 'EOF'
spring:
  datasource:
    url: ${DB_URL:jdbc:mysql://localhost:3306/demo}
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION:86400000}

logging:
  level:
    root: WARN
    com.example.demo: INFO
EOF

cat > src/main/resources/application-test.yml << 'EOF'
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  
  h2:
    console:
      enabled: true

jwt:
  secret: test-secret-key
  expiration: 3600000 # 1小时
EOF

echo "   ✅ 创建多环境配置文件"

# 7. 创建基础文件
echo "7. 创建基础项目文件..."

# 创建.gitignore
cat > .gitignore << 'EOF'
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs
hs_err_pid*

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# IDE
.idea/
*.iws
*.iml
*.ipr
.vscode/
.classpath
.project
.settings/

# OS
.DS_Store
Thumbs.db

# Application specific
logs/
temp/
*.tmp
EOF

# 创建README.md
cat > README.md << 'EOF'
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
EOF

# 创建Dockerfile
cat > Dockerfile << 'EOF'
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# 创建docker-compose.yml
cat > docker-compose.yml << 'EOF'
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - DB_URL=jdbc:mysql://mysql:3306/demo
      - DB_USERNAME=root
      - DB_PASSWORD=password
    depends_on:
      - mysql

  mysql:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=password
      - MYSQL_DATABASE=demo
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
EOF

echo "   ✅ 创建基础项目文件"

echo ""
echo "🎉 SpringBoot项目结构标准化完成！"
echo ""
echo "📁 新的目录结构："
echo "   ├── docs/              # 文档目录"
echo "   ├── scripts/           # 脚本目录"
echo "   ├── src/main/java/     # Java源码"
echo "   ├── src/test/java/     # 测试代码"
echo "   ├── src/main/resources/ # 资源文件"
echo "   └── 配置文件和Docker支持"
echo ""
echo "📋 下一步建议："
echo "   1. 检查现有代码文件位置"
echo "   2. 将现有Java类移动到对应包中"
echo "   3. 更新import语句"
echo "   4. 运行测试确保功能正常"
echo ""
echo "⚠️  注意：请在执行前备份项目！"