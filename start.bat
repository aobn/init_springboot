@echo off
chcp 65001 >nul

REM 文件名：start.bat
REM 功能：一键启动Spring Boot项目脚本（Windows版）
REM 作者：CodeBuddy
REM 创建时间：2025-09-19
REM 版本：v1.0.0
REM 备注：自动杀掉8080端口进程并启动项目

echo === Spring Boot 项目一键启动脚本 ===
echo 当前时间: %date% %time%
echo.

echo 🔍 检查8080端口占用情况...

REM 查找占用8080端口的进程
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :8080 ^| findstr LISTENING') do (
    set PORT_PID=%%a
)

if defined PORT_PID (
    echo ⚠️  发现8080端口被进程 %PORT_PID% 占用
    echo 🔪 正在杀掉占用8080端口的进程...
    taskkill /PID %PORT_PID% /F >nul 2>&1
    timeout /t 2 /nobreak >nul
    echo ✅ 8080端口已释放
) else (
    echo ✅ 8080端口未被占用
)

echo.
echo 🚀 正在启动Spring Boot项目...
echo 📍 项目路径: %cd%
echo.

REM 检查Maven是否可用
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Maven未安装或不在PATH中
    echo 请确保Maven已正确安装并配置环境变量
    pause
    exit /b 1
)

REM 清理并编译项目
echo 🧹 清理项目...
mvn clean -q
if errorlevel 1 (
    echo ❌ 项目清理失败
    pause
    exit /b 1
)

echo 🔨 编译项目...
mvn compile -q
if errorlevel 1 (
    echo ❌ 项目编译失败
    pause
    exit /b 1
)

echo ✅ 项目编译成功
echo.

REM 启动项目
echo 🎯 启动Spring Boot应用...
echo 📝 日志将显示在下方，按 Ctrl+C 停止应用
echo 🌐 应用启动后可访问: http://localhost:8080
echo 📚 API文档地址: http://localhost:8080/swagger-ui.html (如果已配置)
echo.
echo ==================== 应用日志 ====================

REM 使用spring-boot:run启动项目
mvn spring-boot:run

echo.
echo 🛑 应用已停止
echo === 脚本执行完毕 ===
pause