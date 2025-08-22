/*
 * DTO base para respuestas con ID de transacción
 */
package com.testCus.shoppingcart.dto;

import java.time.LocalDateTime;

/**
 * Respuesta base que incluye ID de transacción y timestamp
 */
public class TransactionResponse {
    private String transactionId;
    private LocalDateTime timestamp;
    private String status;
    private String message;

    public TransactionResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public TransactionResponse(String transactionId, String status, String message) {
        this();
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
    }

    // Getters y Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
