/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import java.util.List;

/**
 *
 * @author Mohamed
 */
public class OrderDetailDTO {
    private int orderId;
    private CustomerDTO customer;  
    private List<ProductDTO> products;
    private double total;
    private String status;  
    private String paymentMethod;  // "Credit Card", "PayPal"

    public OrderDetailDTO(int orderId, CustomerDTO customer, List<ProductDTO> products, double total, String status, String paymentMethod) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public OrderDetailDTO() {
    }

 
    public static class CustomerDTO {
        private int customerId;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private AddressDTO address;

        public CustomerDTO(int customerId, String firstName, String lastName, String email, String phone, AddressDTO address) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public CustomerDTO() {
        }

        public static class AddressDTO {
            private String street;
            private String city;
            private String zipCode;

            public AddressDTO() {
            }

            public AddressDTO(String street, String city, String zipCode) {
                this.street = street;
                this.city = city;
                this.zipCode = zipCode;
            }

            /**
             * @return the street
             */
            public String getStreet() {
                return street;
            }

            /**
             * @param street the street to set
             */
            public void setStreet(String street) {
                this.street = street;
            }

            /**
             * @return the city
             */
            public String getCity() {
                return city;
            }

            /**
             * @param city the city to set
             */
            public void setCity(String city) {
                this.city = city;
            }

            /**
             * @return the zipCode
             */
            public String getZipCode() {
                return zipCode;
            }

            /**
             * @param zipCode the zipCode to set
             */
            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }
            
            
        }

        /**
         * @return the customerId
         */
        public int getCustomerId() {
            return customerId;
        }

        /**
         * @param customerId the customerId to set
         */
        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName the firstName to set
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName the lastName to set
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return the phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * @param phone the phone to set
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * @return the address
         */
        public AddressDTO getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(AddressDTO address) {
            this.address = address;
        }
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the customer
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * @return the products
     */
    public List<ProductDTO> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
