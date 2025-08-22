/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.controller;

import com.testCus.shoppingcart.dto.OrderPaymentDTO;
import com.testCus.shoppingcart.dto.OrderResponse;
import com.testCus.shoppingcart.dto.ProductDTO;
import com.testCus.shoppingcart.model.OrderDetail;
import com.testCus.shoppingcart.model.Product;
import com.testCus.shoppingcart.service.OrderService;
import com.testCus.shoppingcart.service.TransactionIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import com.testCus.shoppingcart.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import java.util.Date;

/**
 *
 * @author Mohamed
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final TransactionIdService transactionIdService;

    @Autowired
    public OrderController(OrderService orderService, TransactionIdService transactionIdService) {
        this.orderService = orderService;
        this.transactionIdService = transactionIdService;
    }

    // Endpoint para crear una nueva orden
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderPaymentDTO orderPaymentDTO) {
        // Generar ID de transacción
        String transactionId = transactionIdService.generateOrderTransactionId();
        
        // Crear la orden sin convertir productos a entidades JPA
        OrderDetail order = new OrderDetail();
        order.setCustomer(createCustomerFromDTO(orderPaymentDTO.getCustomer()));
        
        // Calcular el total de la orden
        double total = orderPaymentDTO.getProducts().stream()
                .mapToDouble(ProductDTO::getPrice)
                .sum();
        
        // Establecer información de productos
        order.setProductCount(orderPaymentDTO.getProducts().size());
        order.setProductSummary("Orden con " + orderPaymentDTO.getProducts().size() + " productos");
        order.setTotal(total);
        order.setStatus("Pending");
        
        // No establecer productos aquí para evitar problemas de persistencia
        // Los productos se manejarán como información de referencia

        // Delegar al servicio (el logging se maneja ahí)
        OrderDetail savedOrder = orderService.saveOrder(order, transactionId);

        // Crear y retornar respuesta
        OrderResponse response = new OrderResponse(
            transactionId,
            "SUCCESS",
            "Orden creada exitosamente",
            savedOrder
        );
        
        return ResponseEntity.ok(response);
    }

    // Método para obtener todas las órdenes con un estado específico
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable String status) {
        // Validación básica del estado
        if (!status.matches("^(Pending|Completed|Cancelled)$")) {
            ErrorResponse errorResponse = new ErrorResponse(
                400,
                "INVALID_STATUS",
                "El estado debe ser: Pending, Completed o Cancelled. Estado recibido: " + status,
                new Date(),
                "/api/orders/status/" + status
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        
        // Generar ID de transacción
        String transactionId = transactionIdService.generateQueryTransactionId();
        
        // Delegar al servicio (el logging se maneja ahí)
        List<OrderDetail> orders = orderService.getOrdersByStatus(status, transactionId);

        if (orders.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "ORDERS_NOT_FOUND",
                "No se encontraron órdenes con estado: " + status,
                new Date(),
                "/api/orders/status/" + status
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // Crear respuesta con la primera orden como ejemplo
        OrderDetail sampleOrder = orders.get(0);
        OrderResponse response = new OrderResponse(
            transactionId,
            "SUCCESS",
            "Órdenes obtenidas exitosamente. Total: " + orders.size(),
            sampleOrder
        );
        
        return ResponseEntity.ok(response);
    }

    // Método para crear el cliente desde el DTO
    private OrderDetail.Customer createCustomerFromDTO(OrderPaymentDTO.CustomerDTO customerDTO) {
        OrderDetail.Customer customer = new OrderDetail.Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(createAddressFromDTO(customerDTO.getAddress()));
        return customer;
    }

    // Método para crear la dirección del cliente desde el DTO
    private OrderDetail.Customer.Address createAddressFromDTO(OrderPaymentDTO.CustomerDTO.AddressDTO addressDTO) {
        OrderDetail.Customer.Address address = new OrderDetail.Customer.Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setZipCode(addressDTO.getZipCode());
        return address;
    }

    // Método para convertir ProductDTO a Product
    private Product convertDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setImage(productDTO.getImage());
        
        // Convertir rating si existe
        if (productDTO.getRating() != null) {
            Product.Rating rating = new Product.Rating();
            rating.setRate(productDTO.getRating().getRate());
            rating.setCount(productDTO.getRating().getCount());
            product.setRating(rating);
        }
        
        return product;
    }
}

