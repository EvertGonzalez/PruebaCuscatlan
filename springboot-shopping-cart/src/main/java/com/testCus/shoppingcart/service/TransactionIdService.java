/*
 * Servicio para generar IDs de transacción únicos
 */
package com.testCus.shoppingcart.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Servicio para generar IDs de transacción únicos
 */
@Service
public class TransactionIdService {
    
    private static final AtomicLong sequence = new AtomicLong(1);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    
    /**
     * Genera un ID de transacción único con formato: TXN-YYYYMMDDHHMMSS-XXXXX
     * Ejemplo: TXN-20241201143022-00001
     */
    public String generateTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String sequenceStr = String.format("%05d", sequence.getAndIncrement());
        
        return String.format("TXN-%s-%s", timestamp, sequenceStr);
    }
    
    /**
     * Genera un ID de transacción para productos con formato: PRD-YYYYMMDDHHMMSS-XXXXX
     */
    public String generateProductTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String sequenceStr = String.format("%05d", sequence.getAndIncrement());
        
        return String.format("PRD-%s-%s", timestamp, sequenceStr);
    }
    
    /**
     * Genera un ID de transacción para órdenes con formato: ORD-YYYYMMDDHHMMSS-XXXXX
     */
    public String generateOrderTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String sequenceStr = String.format("%05d", sequence.getAndIncrement());
        
        return String.format("ORD-%s-%s", timestamp, sequenceStr);
    }
    
    /**
     * Genera un ID de transacción para pagos con formato: PAY-YYYYMMDDHHMMSS-XXXXX
     */
    public String generatePaymentTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String sequenceStr = String.format("%05d", sequence.getAndIncrement());
        
        return String.format("PAY-%s-%s", timestamp, sequenceStr);
    }
    
    /**
     * Genera un ID de transacción para consultas con formato: QRY-YYYYMMDDHHMMSS-XXXXX
     */
    public String generateQueryTransactionId() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String sequenceStr = String.format("%05d", sequence.getAndIncrement());
        
        return String.format("QRY-%s-%s", timestamp, sequenceStr);
    }
}
