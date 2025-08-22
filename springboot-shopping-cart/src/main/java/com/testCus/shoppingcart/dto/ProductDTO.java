/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testCus.shoppingcart.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

/**
 *
 * @author Mohamed
 */

public class ProductDTO {

   @Min(value = 1, message = "El ID debe ser un número positivo")
   private int id;
   
   @NotBlank(message = "El título es obligatorio")
   @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
   private String title;
   
   @NotNull(message = "El precio es obligatorio")
   @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
   @Digits(integer = 6, fraction = 2, message = "El precio debe tener máximo 2 decimales")
   private double price;
   
   @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
   private String description;
   
   @NotBlank(message = "La categoría es obligatoria")
   @Size(min = 1, max = 50, message = "La categoría debe tener entre 1 y 50 caracteres")
   private String category;
   
   @Size(max = 255, message = "La URL de la imagen no puede exceder 255 caracteres")
   private String image;
   
   @Valid
   private Rating rating;

    public ProductDTO() {
    }

    public ProductDTO(int id, String title, double price, String description, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }


}
