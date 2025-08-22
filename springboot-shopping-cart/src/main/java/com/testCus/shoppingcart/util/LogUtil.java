/*
 * Clase utilitaria para manejo centralizado de logging
 */
package com.testCus.shoppingcart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad para logging centralizado con formato consistente
 */
@Component
public class LogUtil {
    
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    /**
     * Obtiene un logger para la clase especificada
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
    
    /**
     * Log de inicio de operación
     */
    public static void logOperationStart(Logger logger, String operation, String transactionId, Object... params) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        StringBuilder message = new StringBuilder();
        message.append("🚀 [START] ").append(operation);
        message.append(" | TransactionID: ").append(transactionId);
        message.append(" | Timestamp: ").append(timestamp);
        
        if (params.length > 0) {
            message.append(" | Params: ");
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 < params.length) {
                    message.append(params[i]).append("=").append(params[i + 1]);
                    if (i + 2 < params.length) message.append(", ");
                }
            }
        }
        
        logger.info(message.toString());
    }
    
    /**
     * Log de operación exitosa
     */
    public static void logOperationSuccess(Logger logger, String operation, String transactionId, Object result) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.info("✅ [SUCCESS] {} | TransactionID: {} | Timestamp: {} | Result: {}", 
                   operation, transactionId, timestamp, result);
    }
    
    /**
     * Log de operación exitosa sin resultado
     */
    public static void logOperationSuccess(Logger logger, String operation, String transactionId) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.info("✅ [SUCCESS] {} | TransactionID: {} | Timestamp: {}", 
                   operation, transactionId, timestamp);
    }
    
    /**
     * Log de validación exitosa
     */
    public static void logValidationSuccess(Logger logger, String operation, String transactionId, String field, Object value) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.debug("✓ [VALIDATION] {} | TransactionID: {} | Timestamp: {} | Field: {} | Value: {}", 
                   operation, transactionId, timestamp, field, value);
    }
    
    /**
     * Log de validación fallida
     */
    public static void logValidationFailure(Logger logger, String operation, String transactionId, String field, Object value, String reason) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.warn("⚠️ [VALIDATION_FAILED] {} | TransactionID: {} | Timestamp: {} | Field: {} | Value: {} | Reason: {}", 
                   operation, transactionId, timestamp, field, value, reason);
    }
    
    /**
     * Log de error de validación
     */
    public static void logValidationError(Logger logger, String operation, String transactionId, String errorMessage) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.error("❌ [VALIDATION_ERROR] {} | TransactionID: {} | Timestamp: {} | Error: {}", 
                   operation, transactionId, timestamp, errorMessage);
    }
    
    /**
     * Log de error de negocio
     */
    public static void logBusinessError(Logger logger, String operation, String transactionId, String errorType, String errorMessage) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.error("💥 [BUSINESS_ERROR] {} | TransactionID: {} | Timestamp: {} | Type: {} | Error: {}", 
                   operation, transactionId, timestamp, errorType, errorMessage);
    }
    
    /**
     * Log de error del sistema
     */
    public static void logSystemError(Logger logger, String operation, String transactionId, String errorType, String errorMessage, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.error("🔥 [SYSTEM_ERROR] {} | TransactionID: {} | Timestamp: {} | Type: {} | Error: {}", 
                   operation, transactionId, timestamp, errorType, errorMessage, throwable);
    }
    
    /**
     * Log de información de debug
     */
    public static void logDebug(Logger logger, String operation, String transactionId, String message, Object... params) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("🔍 [DEBUG] ").append(operation);
        logMessage.append(" | TransactionID: ").append(transactionId);
        logMessage.append(" | Timestamp: ").append(timestamp);
        logMessage.append(" | Message: ").append(message);
        
        if (params.length > 0) {
            logMessage.append(" | Data: ");
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 < params.length) {
                    logMessage.append(params[i]).append("=").append(params[i + 1]);
                    if (i + 2 < params.length) logMessage.append(", ");
                }
            }
        }
        
        logger.debug(logMessage.toString());
    }
    
    /**
     * Log de información general
     */
    public static void logInfo(Logger logger, String operation, String transactionId, String message, Object... params) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("ℹ️ [INFO] ").append(operation);
        logMessage.append(" | TransactionID: ").append(transactionId);
        logMessage.append(" | Timestamp: ").append(timestamp);
        logMessage.append(" | Message: ").append(message);
        
        if (params.length > 0) {
            logMessage.append(" | Data: ");
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 < params.length) {
                    logMessage.append(params[i]).append("=").append(params[i + 1]);
                    if (i + 2 < params.length) logMessage.append(", ");
                }
            }
        }
        
        logger.info(logMessage.toString());
    }
    
    /**
     * Log de advertencia
     */
    public static void logWarning(Logger logger, String operation, String transactionId, String message, Object... params) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("⚠️ [WARNING] ").append(operation);
        logMessage.append(" | TransactionID: ").append(transactionId);
        logMessage.append(" | Timestamp: ").append(timestamp);
        logMessage.append(" | Message: ").append(message);
        
        if (params.length > 0) {
            logMessage.append(" | Data: ");
            for (int i = 0; i < params.length; i += 2) {
                if (i + 1 < params.length) {
                    logMessage.append(params[i]).append("=").append(params[i + 1]);
                    if (i + 2 < params.length) logMessage.append(", ");
                }
            }
        }
        
        logger.warn(logMessage.toString());
    }
    
    /**
     * Log de entrada de datos
     */
    public static void logInputData(Logger logger, String operation, String transactionId, String dataType, Object data) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.debug("📥 [INPUT] {} | TransactionID: {} | Timestamp: {} | Type: {} | Data: {}", 
                   operation, transactionId, timestamp, dataType, data);
    }
    
    /**
     * Log de salida de datos
     */
    public static void logOutputData(Logger logger, String operation, String transactionId, String dataType, Object data) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.debug("📤 [OUTPUT] {} | TransactionID: {} | Timestamp: {} | Type: {} | Data: {}", 
                   operation, transactionId, timestamp, dataType, data);
    }
    
    /**
     * Log de llamada a servicio externo
     */
    public static void logExternalServiceCall(Logger logger, String operation, String transactionId, String serviceName, String endpoint) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.info("🌐 [EXTERNAL_CALL] {} | TransactionID: {} | Timestamp: {} | Service: {} | Endpoint: {}", 
                   operation, transactionId, timestamp, serviceName, endpoint);
    }
    
    /**
     * Log de respuesta de servicio externo
     */
    public static void logExternalServiceResponse(Logger logger, String operation, String transactionId, String serviceName, String status, Object response) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.info("🌐 [EXTERNAL_RESPONSE] {} | TransactionID: {} | Timestamp: {} | Service: {} | Status: {} | Response: {}", 
                   operation, transactionId, timestamp, serviceName, status, response);
    }
    
    /**
     * Log de tiempo de ejecución
     */
    public static void logExecutionTime(Logger logger, String operation, String transactionId, long startTime, long endTime) {
        long executionTime = endTime - startTime;
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        logger.info("⏱️ [EXECUTION_TIME] {} | TransactionID: {} | Timestamp: {} | Duration: {}ms", 
                   operation, transactionId, timestamp, executionTime);
    }
}
