/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import java.util.Date;
import jakarta.validation.constraints.*;

/**
 *
 * @author Mohamed
 */

public class PaymentDTO {
    @Min(value = 1, message = "El ID del pago debe ser un número positivo")
    private int paymentId;     
    
    @Min(value = 1, message = "El ID del cliente debe ser un número positivo")
    private int customerId;    
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El monto debe tener máximo 2 decimales")
    private double amount;     
    
    @NotBlank(message = "El estado del pago es obligatorio")
    @Pattern(regexp = "^(Pending|Completed|Failed|Cancelled)$", message = "El estado debe ser: Pending, Completed, Failed o Cancelled")
    private String status;     // Estado del pago: "Pending" o "Completed"
    
    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "^(Credit Card|Debit Card|PayPal|Bank Transfer|Cash)$", message = "Método de pago no válido")
    private String paymentMethod; 
    
    @NotNull(message = "La fecha del pago es obligatoria")
    private Date date;       

    // Getters y Setters

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
