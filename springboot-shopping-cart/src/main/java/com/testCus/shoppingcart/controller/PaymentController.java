/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.controller;

import com.testCus.shoppingcart.dto.OrderPaymentDTO;
import com.testCus.shoppingcart.dto.PaymentDTO;
import com.testCus.shoppingcart.dto.PaymentResponse;
import com.testCus.shoppingcart.dto.ErrorResponse;
import com.testCus.shoppingcart.model.OrderDetail;
import com.testCus.shoppingcart.service.OrderService;
import com.testCus.shoppingcart.service.TransactionIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Date;

/**
 *
 * @author Mohamed
 */

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final OrderService orderService;
    private final TransactionIdService transactionIdService;

    @Autowired
    public PaymentController(OrderService orderService, TransactionIdService transactionIdService) {
        this.orderService = orderService;
        this.transactionIdService = transactionIdService;
    }

    // Endpoint para procesar el pago de la orden
    @PostMapping
    public ResponseEntity<?> processPayment(@Valid @RequestBody OrderPaymentDTO orderPaymentDTO) {
        // Generar ID de transacción
        String transactionId = transactionIdService.generatePaymentTransactionId();
        
        // Verificar si hay una orden pendiente para el cliente
        Optional<OrderDetail> existingOrder = orderService.findPendingOrder(orderPaymentDTO.getCustomer().getCustomerId());

        if (!existingOrder.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "PENDING_ORDER_NOT_FOUND",
                "No se encontró una orden pendiente para el cliente con ID: " + orderPaymentDTO.getCustomer().getCustomerId(),
                new Date(),
                "/api/payments"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        OrderDetail order = existingOrder.get();

        // Validar que la orden tenga productos (usando el nuevo campo productCount)
        if (order.getProductCount() <= 0) {
            ErrorResponse errorResponse = new ErrorResponse(
                400,
                "ORDER_WITHOUT_PRODUCTS",
                "La orden no tiene productos para procesar el pago",
                new Date(),
                "/api/payments"
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Usar el total ya calculado en la orden
        double totalAmount = order.getTotal();
        
        // Simulamos el proceso de pago
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setCustomerId(orderPaymentDTO.getCustomer().getCustomerId());
        paymentDTO.setAmount(totalAmount);
        paymentDTO.setStatus("Pending");

        // Simulación de pago exitoso
        paymentDTO.setStatus("Completed");

        // Cambiar el estado de la orden a "Completed"
        order.setStatus("Completed");

        // Guardar la orden después del procesamiento
        orderService.updateOrder(order, transactionId);

        // Crear respuesta
        PaymentResponse response = new PaymentResponse(
            transactionId,
            "SUCCESS",
            "Pago procesado exitosamente",
            paymentDTO,
            order
        );

        return ResponseEntity.ok(response);
    }
}



