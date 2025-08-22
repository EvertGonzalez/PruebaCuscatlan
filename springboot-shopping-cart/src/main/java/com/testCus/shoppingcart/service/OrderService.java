/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.service;

import com.testCus.shoppingcart.model.OrderDetail;
import com.testCus.shoppingcart.repository.OrderRepository;
import com.testCus.shoppingcart.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mohamed
 */
@Service
public class OrderService {

    private static final Logger logger = LogUtil.getLogger(OrderService.class);
    private static final String OPERATION_SAVE_ORDER = "SAVE_ORDER";
    private static final String OPERATION_GET_ORDERS_BY_STATUS = "GET_ORDERS_BY_STATUS";
    private static final String OPERATION_FIND_PENDING_ORDER = "FIND_PENDING_ORDER";
    private static final String OPERATION_UPDATE_ORDER = "UPDATE_ORDER";
    
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDetail saveOrder(OrderDetail order, String transactionId) {
        long startTime = System.currentTimeMillis();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_SAVE_ORDER, transactionId, 
            "orderId", order.getOrderId(),
            "customerId", order.getCustomer().getCustomerId(),
            "productsCount", order.getProductCount());
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_SAVE_ORDER, transactionId, "OrderDetail", order);
            
            // Guardar la orden
            LogUtil.logInfo(logger, OPERATION_SAVE_ORDER, transactionId, "Guardando orden en el repositorio");
            OrderDetail savedOrder = orderRepository.save(order);
            
            // Log de datos de salida
            LogUtil.logOutputData(logger, OPERATION_SAVE_ORDER, transactionId, "SavedOrder", savedOrder);
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_SAVE_ORDER, transactionId, 
                String.format("Orden guardada con ID: %d", savedOrder.getOrderId()));
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_SAVE_ORDER, transactionId, startTime, endTime);
            
            return savedOrder;
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_SAVE_ORDER, transactionId, "SAVE_ERROR", 
                "Error al guardar orden", e);
            throw e;
        }
    }

    public List<OrderDetail> getOrdersByStatus(String status, String transactionId) {
        long startTime = System.currentTimeMillis();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, "status", status);
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, "Status", status);
            
            // Obtener órdenes por estado
            LogUtil.logInfo(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, "Obteniendo órdenes del repositorio");
            List<OrderDetail> orders = orderRepository.findByStatus(status);
            
            // Log de datos de salida
            LogUtil.logOutputData(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, "Orders", 
                String.format("Total órdenes encontradas: %d", orders.size()));
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, 
                String.format("Órdenes obtenidas: %d", orders.size()));
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, startTime, endTime);
            
            return orders;
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_GET_ORDERS_BY_STATUS, transactionId, "QUERY_ERROR", 
                "Error al obtener órdenes por estado", e);
            throw e;
        }
    }

    public Optional<OrderDetail> findPendingOrder(int customerId) {
        String transactionId = "SVC-" + System.currentTimeMillis();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "customerId", customerId);
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "CustomerID", customerId);
            
            // Buscar orden pendiente
            LogUtil.logInfo(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "Buscando orden pendiente en el repositorio");
            Optional<OrderDetail> pendingOrder = orderRepository.findByCustomerIdAndStatus(customerId, "Pending");
            
            if (pendingOrder.isPresent()) {
                LogUtil.logInfo(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "Orden pendiente encontrada");
            } else {
                LogUtil.logWarning(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "No se encontró orden pendiente");
            }
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_FIND_PENDING_ORDER, transactionId, 
                pendingOrder.isPresent() ? "Orden encontrada" : "Orden no encontrada");
            
            return pendingOrder;
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_FIND_PENDING_ORDER, transactionId, "QUERY_ERROR", 
                "Error al buscar orden pendiente", e);
            throw e;
        }
    }

    public void updateOrder(OrderDetail order, String transactionId) {
        long startTime = System.currentTimeMillis();
        
        // Log de inicio de operación
        LogUtil.logOperationStart(logger, OPERATION_UPDATE_ORDER, transactionId, 
            "orderId", order.getOrderId(),
            "status", order.getStatus());
        
        try {
            // Log de datos de entrada
            LogUtil.logInputData(logger, OPERATION_UPDATE_ORDER, transactionId, "OrderDetail", order);
            
            // Actualizar la orden
            LogUtil.logInfo(logger, OPERATION_UPDATE_ORDER, transactionId, "Actualizando orden en el repositorio");
            orderRepository.save(order);
            
            // Log de operación exitosa
            LogUtil.logOperationSuccess(logger, OPERATION_UPDATE_ORDER, transactionId, 
                String.format("Orden actualizada con ID: %d", order.getOrderId()));
            
            // Log de tiempo de ejecución
            long endTime = System.currentTimeMillis();
            LogUtil.logExecutionTime(logger, OPERATION_UPDATE_ORDER, transactionId, startTime, endTime);
            
        } catch (Exception e) {
            // Log de error del sistema
            LogUtil.logSystemError(logger, OPERATION_UPDATE_ORDER, transactionId, "UPDATE_ERROR", 
                "Error al actualizar orden", e);
            throw e;
        }
    }
}


