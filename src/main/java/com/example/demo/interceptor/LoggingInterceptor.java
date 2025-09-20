package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 日志拦截器
 * 记录请求和响应信息
 * 
 * @author xxh
 * @since 2025-09-19
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, 
                           @NonNull HttpServletResponse response, 
                           @NonNull Object handler) throws Exception {
        
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String clientIp = getClientIpAddress(request);
        
        logger.info("=== 请求开始 === 请求方法: {} | 请求路径: {} | 查询参数: {} | 客户端IP: {} | User-Agent: {}", 
                   method, uri, queryString != null ? queryString : "无", clientIp, request.getHeader("User-Agent"));
        
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, 
                              @NonNull HttpServletResponse response, 
                              @NonNull Object handler, 
                              @Nullable Exception ex) throws Exception {
        
        Long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        
        String method = request.getMethod();
        String uri = request.getRequestURI();
        int status = response.getStatus();
        
        String exceptionInfo = ex != null ? " | 异常: " + ex.getMessage() : "";
        logger.info("=== 请求结束 === 请求方法: {} | 请求路径: {} | 响应状态: {} | 执行时间: {}ms{} ==================", 
                   method, uri, status, executeTime, exceptionInfo);
        
        if (ex != null) {
            logger.error("请求异常详情: ", ex);
        }
    }

    /**
     * 获取客户端真实IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0];
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }
}