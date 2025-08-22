/*
 * DTO para respuestas de pagos con ID de transacción
 */
package com.testCus.shoppingcart.dto;

import com.testCus.shoppingcart.model.OrderDetail;

/**
 * Respuesta de pagos que incluye ID de transacción
 */
public class PaymentResponse extends TransactionResponse {
    private PaymentDTO payment;
    private OrderDetail order;
    private String paymentReference;

    public PaymentResponse() {
        super();
    }

    public PaymentResponse(String transactionId, String status, String message, PaymentDTO payment, OrderDetail order) {
        super(transactionId, status, message);
        this.payment = payment;
        this.order = order;
        this.paymentReference = payment != null ? "PAY-" + payment.getPaymentId() : null;
    }

    // Getters y Setters
    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
        this.paymentReference = payment != null ? "PAY-" + payment.getPaymentId() : null;
    }

    public OrderDetail getOrder() {
        return order;
    }

    public void setOrder(OrderDetail order) {
        this.order = order;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
