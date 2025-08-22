/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 * @author Mohamed
 */
@SpringBootApplication
@EnableCaching
public class SpringbootShoppingCart {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShoppingCart.class, args);
    }

}