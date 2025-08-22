/*
 * Controlador para gestionar productos desde FakeStore API
 */
package com.testCus.shoppingcart.controller;

import com.testCus.shoppingcart.dto.ProductDTO;
import com.testCus.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import com.testCus.shoppingcart.dto.ErrorResponse;
import org.springframework.http.HttpStatus;

/**
 * Controlador REST para productos
 * Actúa como proxy hacia FakeStore API
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Permitir CORS para desarrollo
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Obtiene productos desde FakeStore API
     * @param id ID del producto (opcional, si no se proporciona retorna todos)
     * @return Lista de productos
     */
    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false) Integer id) {
        try {
            List<ProductDTO> products = productService.getProductDetails(id);
            if (products == null || products.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse(
                    404,
                    "PRODUCTS_NOT_FOUND",
                    id != null ? "No se encontraron productos con ID: " + id : "No se encontraron productos",
                    new Date(),
                    "/api/products" + (id != null ? "?id=" + id : "")
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "API_ERROR",
                "Error al obtener productos: " + e.getMessage(),
                new Date(),
                "/api/products" + (id != null ? "?id=" + id : "")
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    /**
     * Obtiene un producto específico por ID
     * @param id ID del producto
     * @return Producto específico
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        try {
            List<ProductDTO> products = productService.getProductDetails(id);
            if (products == null || products.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse(
                    404,
                    "PRODUCT_NOT_FOUND",
                    "No se encontró el producto con ID: " + id,
                    new Date(),
                    "/api/products/" + id
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(products.get(0));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "API_ERROR",
                "Error al obtener el producto con ID " + id + ": " + e.getMessage(),
                new Date(),
                "/api/products/" + id
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    /**
     * Obtiene todas las categorías disponibles
     * @return Lista de categorías únicas
     */
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        try {
            List<String> categories = productService.getAllCategories();
            if (categories == null || categories.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse(
                    404,
                    "CATEGORIES_NOT_FOUND",
                    "No se encontraron categorías disponibles",
                    new Date(),
                    "/api/products/categories"
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "API_ERROR",
                "Error al obtener categorías: " + e.getMessage(),
                new Date(),
                "/api/products/categories"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    /**
     * Obtiene productos por categoría
     * @param category Categoría de productos
     * @return Lista de productos de la categoría
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        try {
            List<ProductDTO> products = productService.getProductsByCategory(category);
            if (products == null || products.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse(
                    404,
                    "PRODUCTS_NOT_FOUND",
                    "No se encontraron productos en la categoría: " + category,
                    new Date(),
                    "/api/products/category/" + category
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                404,
                "API_ERROR",
                "Error al obtener productos de la categoría " + category + ": " + e.getMessage(),
                new Date(),
                "/api/products/category/" + category
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    /**
     * Limpia el caché de productos
     * @return Respuesta de confirmación
     */
    @PostMapping("/cache/clear")
    public ResponseEntity<String> clearCache() {
        try {
            productService.clearCache();
            return ResponseEntity.ok("Cache cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error clearing cache: " + e.getMessage());
        }
    }
}

