#!/bin/bash

# 文件名：start-advanced.sh
# 功能：Spring Boot项目高级启动脚本
# 作者：CodeBuddy
# 创建时间：2025-09-19
# 版本：v1.0.0
# 备注：支持多种启动模式和参数配置

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

# 默认配置
DEFAULT_PORT=8080
DEFAULT_PROFILE="dev"
SKIP_TESTS=true
CLEAN_BUILD=true

# 显示帮助信息
show_help() {
    echo -e "${CYAN}=== Spring Boot 项目高级启动脚本 ===${NC}"
    echo ""
    echo -e "${YELLOW}用法:${NC}"
    echo "  ./start-advanced.sh [选项]"
    echo ""
    echo -e "${YELLOW}选项:${NC}"
    echo "  -p, --port PORT        指定端口号 (默认: 8080)"
    echo "  -e, --profile PROFILE  指定环境配置 (默认: dev)"
    echo "  -t, --test             运行测试"
    echo "  -nc, --no-clean        跳过清理步骤"
    echo "  -d, --debug            启用调试模式"
    echo "  -h, --help             显示帮助信息"
    echo ""
    echo -e "${YELLOW}示例:${NC}"
    echo "  ./start-advanced.sh                    # 使用默认配置启动"
    echo "  ./start-advanced.sh -p 8081 -e prod   # 指定端口和环境"
    echo "  ./start-advanced.sh -t                # 运行测试后启动"
    echo "  ./start-advanced.sh -d                # 调试模式启动"
    echo ""
}

# 解析命令行参数
parse_args() {
    while [[ $# -gt 0 ]]; do
        case $1 in
            -p|--port)
                DEFAULT_PORT="$2"
                shift 2
                ;;
            -e|--profile)
                DEFAULT_PROFILE="$2"
                shift 2
                ;;
            -t|--test)
                SKIP_TESTS=false
                shift
                ;;
            -nc|--no-clean)
                CLEAN_BUILD=false
                shift
                ;;
            -d|--debug)
                DEBUG_MODE=true
                shift
                ;;
            -h|--help)
                show_help
                exit 0
                ;;
            *)
                echo -e "${RED}❌ 未知参数: $1${NC}"
                show_help
                exit 1
                ;;
        esac
    done
}

# 检查并杀掉端口进程
kill_port_process() {
    local port=$1
    echo -e "${BLUE}🔍 检查端口 $port 占用情况...${NC}"
    
    local port_pid=$(lsof -ti:$port)
    
    if [ ! -z "$port_pid" ]; then
        echo -e "${YELLOW}⚠️  发现端口 $port 被进程 $port_pid 占用${NC}"
        echo -e "${RED}🔪 正在杀掉占用端口的进程...${NC}"
        
        # 尝试优雅关闭
        kill $port_pid
        sleep 2
        
        # 检查进程是否还存在
        if kill -0 $port_pid 2>/dev/null; then
            echo -e "${RED}⚡ 强制杀掉进程...${NC}"
            kill -9 $port_pid
            sleep 1
        fi
        
        echo -e "${GREEN}✅ 端口 $port 已释放${NC}"
    else
        echo -e "${GREEN}✅ 端口 $port 未被占用${NC}"
    fi
}

# 检查Maven环境
check_maven() {
    echo -e "${BLUE}🔧 检查Maven环境...${NC}"
    
    if ! command -v mvn &> /dev/null; then
        echo -e "${RED}❌ Maven未安装或不在PATH中${NC}"
        echo "请确保Maven已正确安装并配置环境变量"
        exit 1
    fi
    
    local maven_version=$(mvn -version | head -n 1)
    echo -e "${GREEN}✅ $maven_version${NC}"
}

# 构建项目
build_project() {
    if [ "$CLEAN_BUILD" = true ]; then
        echo -e "${BLUE}🧹 清理项目...${NC}"
        mvn clean -q
        if [ $? -ne 0 ]; then
            echo -e "${RED}❌ 项目清理失败${NC}"
            exit 1
        fi
    fi
    
    echo -e "${BLUE}🔨 编译项目...${NC}"
    if [ "$SKIP_TESTS" = true ]; then
        mvn compile -DskipTests -q
    else
        echo -e "${BLUE}🧪 运行测试...${NC}"
        mvn test
        if [ $? -ne 0 ]; then
            echo -e "${RED}❌ 测试失败${NC}"
            exit 1
        fi
        mvn compile -q
    fi
    
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ 项目编译失败${NC}"
        exit 1
    fi
    
    echo -e "${GREEN}✅ 项目构建成功${NC}"
}

# 启动应用
start_application() {
    echo ""
    echo -e "${PURPLE}🎯 启动Spring Boot应用...${NC}"
    echo -e "${CYAN}📍 项目路径: $(pwd)${NC}"
    echo -e "${CYAN}🌐 端口: $DEFAULT_PORT${NC}"
    echo -e "${CYAN}🔧 环境: $DEFAULT_PROFILE${NC}"
    
    if [ "$DEBUG_MODE" = true ]; then
        echo -e "${CYAN}🐛 调试模式: 启用${NC}"
    fi
    
    echo ""
    echo -e "${YELLOW}📝 日志将显示在下方，按 Ctrl+C 停止应用${NC}"
    echo -e "${YELLOW}🌐 应用启动后可访问: http://localhost:$DEFAULT_PORT${NC}"
    echo -e "${YELLOW}📚 API文档地址: http://localhost:$DEFAULT_PORT/swagger-ui.html${NC}"
    echo ""
    echo -e "${CYAN}==================== 应用日志 ====================${NC}"
    
    # 构建启动命令
    local start_cmd="mvn spring-boot:run"
    start_cmd="$start_cmd -Dspring-boot.run.profiles=$DEFAULT_PROFILE"
    start_cmd="$start_cmd -Dspring-boot.run.arguments=--server.port=$DEFAULT_PORT"
    
    if [ "$DEBUG_MODE" = true ]; then
        start_cmd="$start_cmd -Dspring-boot.run.jvmArguments='-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005'"
        echo -e "${YELLOW}🐛 调试端口: 5005${NC}"
    fi
    
    # 启动应用
    eval $start_cmd
    
    echo ""
    echo -e "${RED}🛑 应用已停止${NC}"
}

# 主函数
main() {
    # 解析参数
    parse_args "$@"
    
    echo -e "${CYAN}=== Spring Boot 项目高级启动脚本 ===${NC}"
    echo -e "${CYAN}当前时间: $(date)${NC}"
    echo ""
    
    # 检查并杀掉端口进程
    kill_port_process $DEFAULT_PORT
    
    echo ""
    
    # 检查Maven环境
    check_maven
    
    echo ""
    
    # 构建项目
    build_project
    
    # 启动应用
    start_application
    
    echo ""
    echo -e "${CYAN}=== 脚本执行完毕 ===${NC}"
}

# 执行主函数
main "$@"