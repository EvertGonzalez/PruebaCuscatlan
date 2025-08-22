/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

/**
 *
 * @author Mohamed
 */

public class OrderPaymentDTO {
    @Valid
    @NotNull(message = "Los datos del cliente son obligatorios")
    private CustomerDTO customer;  // Datos del cliente
    
    @Valid
    @NotEmpty(message = "La lista de productos no puede estar vacía")
    @Size(min = 1, max = 100, message = "La orden debe tener entre 1 y 100 productos")
    private List<ProductDTO> products;  // Lista de productos en el carrito

    public OrderPaymentDTO(CustomerDTO customer, List<ProductDTO> products) {
        this.customer = customer;
        this.products = products;
    }

    public OrderPaymentDTO() {
    }

    // Getters y Setters

    public static class CustomerDTO {
        @Min(value = 1, message = "El ID del cliente debe ser un número positivo")
        private int customerId;
        
        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
        @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
        private String firstName;
        
        @NotBlank(message = "El apellido es obligatorio")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
        @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
        private String lastName;
        
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        @Size(max = 100, message = "El email no puede exceder 100 caracteres")
        private String email;
        
        @Pattern(regexp = "^[+]?[0-9\\s\\-\\(\\)]+$", message = "El formato del teléfono no es válido")
        @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 caracteres")
        private String phone;
        
        @Valid
        @NotNull(message = "La dirección es obligatoria")
        private AddressDTO address;

        public CustomerDTO() {
        }

        public CustomerDTO(int customerId, String firstName, String lastName, String email, String phone, AddressDTO address) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public static class AddressDTO {
            @NotBlank(message = "La calle es obligatoria")
            @Size(min = 1, max = 100, message = "La calle debe tener entre 1 y 100 caracteres")
            private String street;
            
            @NotBlank(message = "La ciudad es obligatoria")
            @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "La ciudad solo puede contener letras y espacios")
            @Size(min = 1, max = 50, message = "La ciudad debe tener entre 1 y 50 caracteres")
            private String city;
            
            @NotBlank(message = "El código postal es obligatorio")
            @Pattern(regexp = "^[0-9A-Za-z\\-\\s]+$", message = "El código postal solo puede contener números, letras, guiones y espacios")
            @Size(min = 3, max = 10, message = "El código postal debe tener entre 3 y 10 caracteres")
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
}

