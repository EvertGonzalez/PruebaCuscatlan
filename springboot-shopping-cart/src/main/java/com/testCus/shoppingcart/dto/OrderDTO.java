/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import com.testCus.shoppingcart.model.Product;
import java.util.List;

/**
 *
 * @author Mohamed
 */

public class OrderDTO {
    
    private int userId;
    private List<Product> products;
    private double totalPrice;  

    public OrderDTO() {
    }

    public OrderDTO(int userId, List<Product> products, double totalPrice) {
        this.userId = userId;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public void calculateTotal() {
        this.setTotalPrice(this.getProducts().stream().mapToDouble(Product::getPrice).sum());
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

//    private Long orderId;
//    private Long productId;
//    private Integer quantity;
//    private String orderStatus;
//
//
//    public OrderDTO() {
//    }
//
//    public OrderDTO(Long orderId, Long productId, Integer quantity, String orderStatus) {
//        this.orderId = orderId;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.orderStatus = orderStatus;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderDTO{" +
//                "orderId=" + orderId +
//                ", productId=" + productId +
//                ", quantity=" + quantity +
//                ", orderStatus='" + orderStatus + '\'' +
//                '}';
//    }


