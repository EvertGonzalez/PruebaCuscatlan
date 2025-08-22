/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.repository;

import com.testCus.shoppingcart.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mohamed
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer> {

    // Buscar una orden pendiente de un cliente específico
    @Query("SELECT o FROM OrderDetail o WHERE o.customer.customerId = :customerId AND o.status = :status")
    Optional<OrderDetail> findByCustomerIdAndStatus(@Param("customerId") int customerId, @Param("status") String status);

    // Obtener todas las órdenes con un estado específico
    List<OrderDetail> findByStatus(String status);
}

