/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.service;

/**
 *
 * @author Mohamed
 */
import com.testCus.shoppingcart.dto.PaymentDTO;
import com.testCus.shoppingcart.exception.InvalidPaymentException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDTO processPayment(PaymentDTO payment) {
        // Simulación del proceso de pago
        if (payment.getAmount() > 10000) {
            throw new InvalidPaymentException("Amount exceeds the limit for payments");
        }
        //payment.setPaymentStatus("SUCCESS");
        return payment;
    }
}



