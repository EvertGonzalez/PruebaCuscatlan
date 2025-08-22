/*
 * Servicio para obtener productos desde FakeStore API
 */
package com.testCus.shoppingcart.service;

import com.testCus.shoppingcart.dto.ProductDTO;
import com.testCus.shoppingcart.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que actúa como proxy hacia FakeStore API
 */
@Service
public class ProductService {

    private static final String PRODUCT_API_URL = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate;

    public ProductService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Obtiene productos desde FakeStore API
     * @param id ID del producto (null o 0 para todos los productos)
     * @return Lista de productos
     */
    @Cacheable(value = "products", key = "#id == null ? 'all' : #id.toString()")
    public List<ProductDTO> getProductDetails(Integer id) {
        try {
            // Si el id es nulo o 0, obtenemos todos los productos
            if (id == null || id == 0) {
                // Obtener todos los productos
                ResponseEntity<List<ProductDTO>> response = restTemplate.exchange(
                    PRODUCT_API_URL, // URL de la API externa
                    HttpMethod.GET,  // Método GET
                    null,  // Sin cuerpo para GET
                    new ParameterizedTypeReference<List<ProductDTO>>() {} // Especificar que la respuesta es una lista de ProductDTO
                );

                List<ProductDTO> products = response.getBody();

                if (products == null || products.isEmpty()) {
                    throw new ProductNotFoundException("No products found.");
                }

                return products;  // Retornar todos los productos como lista
            } else {
                // Si el id no es nulo ni 0, obtenemos un producto específico
                String url = PRODUCT_API_URL + "/" + id;
                ResponseEntity<ProductDTO> response = restTemplate.exchange(
                    url,  // URL con el id específico
                    HttpMethod.GET,  // Método GET
                    null,  // Sin cuerpo para GET
                    ProductDTO.class // Especificamos que esperamos una respuesta de tipo ProductDTO
                );

                ProductDTO product = response.getBody();

                if (product == null) {
                    throw new ProductNotFoundException("Product not found with ID: " + id);
                }

                return List.of(product);  // Si solo hay un producto, lo retornamos como una lista
            }
        } catch (Exception e) {
            throw new ProductNotFoundException("Error fetching products: " + e.getMessage());
        }
    }

    /**
     * Obtiene productos por categoría
     * @param category Categoría de productos
     * @return Lista de productos de la categoría
     */
    @Cacheable(value = "productsByCategory", key = "#category")
    public List<ProductDTO> getProductsByCategory(String category) {
        try {
            List<ProductDTO> allProducts = getProductDetails(null);
            return allProducts.stream()
                .filter(product -> category.equalsIgnoreCase(product.getCategory()))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ProductNotFoundException("Error fetching products by category: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las categorías disponibles
     * @return Lista de categorías únicas
     */
    @Cacheable(value = "categories")
    public List<String> getAllCategories() {
        try {
            List<ProductDTO> allProducts = getProductDetails(null);
            return allProducts.stream()
                .map(ProductDTO::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ProductNotFoundException("Error fetching categories: " + e.getMessage());
        }
    }

    /**
     * Limpia el caché de productos
     */
    @CacheEvict(value = {"products", "productsByCategory", "categories"}, allEntries = true)
    public void clearCache() {
        // Método para limpiar caché si es necesario
    }
}
 


