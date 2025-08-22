/*
 * Interceptor para logging automático de requests HTTP
 */
package com.testCus.shoppingcart.interceptor;

import com.testCus.shoppingcart.util.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Interceptor que registra automáticamente el logging de entrada y salida
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogUtil.getLogger(LoggingInterceptor.class);
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    private static final String REQUEST_START_TIME = "requestStartTime";
    private static final String REQUEST_ID = "requestId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        String requestId = generateRequestId();
        
        // Guardar información en el request para uso posterior
        request.setAttribute(REQUEST_START_TIME, startTime);
        request.setAttribute(REQUEST_ID, requestId);
        
        // Log de inicio de request
        LogUtil.logOperationStart(logger, "HTTP_REQUEST", requestId,
            "method", request.getMethod(),
            "uri", request.getRequestURI(),
            "clientIP", getClientIP(request),
            "userAgent", request.getHeader("User-Agent"));
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Log de respuesta exitosa
        String requestId = (String) request.getAttribute(REQUEST_ID);
        int statusCode = response.getStatus();
        
        if (statusCode >= 200 && statusCode < 300) {
            LogUtil.logOperationSuccess(logger, "HTTP_REQUEST", requestId, 
                String.format("Response Status: %d", statusCode));
        } else if (statusCode >= 400 && statusCode < 500) {
            LogUtil.logWarning(logger, "HTTP_REQUEST", requestId, 
                String.format("Client Error: %d", statusCode));
        } else if (statusCode >= 500) {
            LogUtil.logSystemError(logger, "HTTP_REQUEST", requestId, "SERVER_ERROR", 
                String.format("Server Error: %d", statusCode), null);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestId = (String) request.getAttribute(REQUEST_ID);
        Long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        
        if (startTime != null) {
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, "HTTP_REQUEST", requestId, startTime, endTime);
        }
        
        if (ex != null) {
            LogUtil.logSystemError(logger, "HTTP_REQUEST", requestId, "EXCEPTION", 
                "Exception during request processing", ex);
        }
    }
    
    private String generateRequestId() {
        return "REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private String getClientIP(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
