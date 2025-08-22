/*
 * DTO para respuestas de productos con ID de transacción
 */
package com.testCus.shoppingcart.dto;

import java.util.List;

/**
 * Respuesta de productos que incluye ID de transacción
 */
public class ProductResponse extends TransactionResponse {
    private List<ProductDTO> products;
    private int totalProducts;

    public ProductResponse() {
        super();
    }

    public ProductResponse(String transactionId, String status, String message, List<ProductDTO> products) {
        super(transactionId, status, message);
        this.products = products;
        this.totalProducts = products != null ? products.size() : 0;
    }

    // Getters y Setters
    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
        this.totalProducts = products != null ? products.size() : 0;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }
}
