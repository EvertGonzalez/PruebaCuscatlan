/*
 * Controller de prueba para verificar códigos HTTP de respuesta
 */
package com.testCus.shoppingcart.controller;

import com.testCus.shoppingcart.dto.ErrorResponse;
import com.testCus.shoppingcart.dto.TransactionResponse;
import com.testCus.shoppingcart.service.TransactionIdService;
import com.testCus.shoppingcart.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import java.util.Date;

/**
 * Controller de prueba para verificar códigos HTTP
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger logger = LogUtil.getLogger(TestController.class);
    private static final String OPERATION_TEST_SUCCESS = "TEST_SUCCESS";
    private static final String OPERATION_TEST_BAD_REQUEST = "TEST_BAD_REQUEST";
    private static final String OPERATION_TEST_NOT_FOUND = "TEST_NOT_FOUND";
    private static final String OPERATION_TEST_SERVER_ERROR = "TEST_SERVER_ERROR";
    private static final String OPERATION_TEST_VALIDATION = "TEST_VALIDATION";
    private static final String OPERATION_TEST_VALIDATION_BODY = "TEST_VALIDATION_BODY";

    @Autowired
    private TransactionIdService transactionIdService;

    /**
     * GET /api/test/success - Devuelve HTTP 200
     */
    @GetMapping("/success")
    public ResponseEntity<TransactionResponse> testSuccess() {
        long startTime = System.currentTimeMillis();
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_TEST_SUCCESS, transactionId);
        
        try {
            // Log de información
            LogUtil.logInfo(logger, OPERATION_TEST_SUCCESS, transactionId, "Generando respuesta de éxito");
            
            TransactionResponse response = new TransactionResponse(
                transactionId,
                "SUCCESS",
                "Operación exitosa - HTTP 200"
            );
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_TEST_SUCCESS, transactionId, "Respuesta de éxito generada");
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_TEST_SUCCESS, transactionId, startTime, endTime);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_TEST_SUCCESS, transactionId, "UNEXPECTED_ERROR", 
                "Error inesperado en test de éxito", e);
            throw e;
        }
    }

    /**
     * GET /api/test/bad-request - Devuelve HTTP 400
     */
    @GetMapping("/bad-request")
    public ResponseEntity<ErrorResponse> testBadRequest() {
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de operación
        LogUtil.logInfo(logger, OPERATION_TEST_BAD_REQUEST, transactionId, "Generando respuesta de error 400");
        
        ErrorResponse error = new ErrorResponse(400, "Bad Request", "Solicitud incorrecta - HTTP 400", new Date(), "/api/test/bad-request");
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * GET /api/test/not-found - Devuelve HTTP 404
     */
    @GetMapping("/not-found")
    public ResponseEntity<ErrorResponse> testNotFound() {
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de operación
        LogUtil.logInfo(logger, OPERATION_TEST_NOT_FOUND, transactionId, "Generando respuesta de error 404");
        
        ErrorResponse error = new ErrorResponse(404, "Not Found", "Recurso no encontrado - HTTP 404", new Date(), "/api/test/not-found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * GET /api/test/server-error - Devuelve HTTP 500
     */
    @GetMapping("/server-error")
    public ResponseEntity<ErrorResponse> testServerError() {
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de operación
        LogUtil.logInfo(logger, OPERATION_TEST_SERVER_ERROR, transactionId, "Generando respuesta de error 500");
        
        ErrorResponse error = new ErrorResponse(500, "Internal Server Error", "Error interno del servidor - HTTP 500", new Date(), "/api/test/server-error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * GET /api/test/validation/{id} - Prueba validación manual
     */
    @GetMapping("/validation/{id}")
    public ResponseEntity<TransactionResponse> testValidation(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_TEST_VALIDATION, transactionId, "id", id);
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_TEST_VALIDATION, transactionId, "ID", id);
            
            // Validaciones
            if (id < 0) {
                LogUtil.logValidationFailure(logger, OPERATION_TEST_VALIDATION, transactionId, "id", id, "ID debe ser un número positivo");
                throw new IllegalArgumentException("El ID debe ser un número positivo");
            }
            if (id == 0) {
                LogUtil.logValidationFailure(logger, OPERATION_TEST_VALIDATION, transactionId, "id", id, "ID no puede ser 0");
                throw new IllegalArgumentException("El ID no puede ser 0");
            }
            
            // Log de validación exitosa
            LogUtil.logValidationSuccess(logger, OPERATION_TEST_VALIDATION, transactionId, "id", id);
            
            // Log de información
            LogUtil.logInfo(logger, OPERATION_TEST_VALIDATION, transactionId, "ID válido, generando respuesta de éxito");
            
            TransactionResponse response = new TransactionResponse(
                transactionId,
                "SUCCESS",
                "ID válido: " + id + " - HTTP 200"
            );
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_TEST_VALIDATION, transactionId, "Validación exitosa para ID: " + id);
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_TEST_VALIDATION, transactionId, startTime, endTime);
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            // Log de error de validación
            LogUtil.logValidationError(logger, OPERATION_TEST_VALIDATION, transactionId, e.getMessage());
            throw e;
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_TEST_VALIDATION, transactionId, "UNEXPECTED_ERROR", 
                "Error inesperado en test de validación", e);
            throw e;
        }
    }

    /**
     * POST /api/test/validation-body - Prueba validación de request body
     */
    @PostMapping("/validation-body")
    public ResponseEntity<TransactionResponse> testValidationBody(@RequestBody TestRequest request) {
        long startTime = System.currentTimeMillis();
        String transactionId = transactionIdService.generateTransactionId();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, 
            "name", request.getName(),
            "age", request.getAge());
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, "TestRequest", request);
            
            // Log de validación exitosa (Bean Validation ya pasó)
            LogUtil.logValidationSuccess(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, "requestBody", "Validación Bean Validation exitosa");
            
            // Log de información
            LogUtil.logInfo(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, "Datos válidos, generando respuesta de éxito");
            
            // Las validaciones se ejecutarán automáticamente
            TransactionResponse response = new TransactionResponse(
                transactionId,
                "SUCCESS",
                "Datos válidos - HTTP 200"
            );
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, "Validación de body exitosa");
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, startTime, endTime);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_TEST_VALIDATION_BODY, transactionId, "UNEXPECTED_ERROR", 
                "Error inesperado en test de validación de body", e);
            throw e;
        }
    }

    /**
     * Clase interna para pruebas de validación
     */
    public static class TestRequest {
        private String name;
        private int age;

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}
