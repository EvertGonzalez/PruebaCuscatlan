/*
 * DTO para respuestas de órdenes con ID de transacción
 */
package com.testCus.shoppingcart.dto;

import com.testCus.shoppingcart.model.OrderDetail;

/**
 * Respuesta de órdenes que incluye ID de transacción
 */
public class OrderResponse extends TransactionResponse {
    private OrderDetail order;
    private String orderNumber;

    public OrderResponse() {
        super();
    }

    public OrderResponse(String transactionId, String status, String message, OrderDetail order) {
        super(transactionId, status, message);
        this.order = order;
        this.orderNumber = order != null ? "ORD-" + order.getOrderId() : null;
    }

    // Getters y Setters
    public OrderDetail getOrder() {
        return order;
    }

    public void setOrder(OrderDetail order) {
        this.order = order;
        this.orderNumber = order != null ? "ORD-" + order.getOrderId() : null;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
