/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import jakarta.validation.constraints.*;

/**
 *
 * @author Mohamed
 */
  public class Rating {
        @DecimalMin(value = "0.0", message = "La valoración no puede ser menor a 0")
        @DecimalMax(value = "5.0", message = "La valoración no puede ser mayor a 5")
        @Digits(integer = 1, fraction = 1, message = "La valoración debe tener máximo 1 decimal")
        private double rate;
        
        @Min(value = 0, message = "El conteo de valoraciones no puede ser negativo")
        @Max(value = 999999, message = "El conteo de valoraciones no puede exceder 999,999")
        private int count;

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    }