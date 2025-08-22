/*
 * DTO para respuestas de error consistentes
 */
package com.testCus.shoppingcart.dto;

import java.util.Date;

/**
 * Respuesta estándar para errores en la API
 */
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private Date timestamp;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String error, String message, Date timestamp, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    // Getters y Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

