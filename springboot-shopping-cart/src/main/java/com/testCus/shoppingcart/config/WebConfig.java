/*
 * Configuración web para interceptores y configuración adicional
 */
package com.testCus.shoppingcart.config;

import com.testCus.shoppingcart.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración web para interceptores y configuración adicional
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**")  // Aplicar a todos los endpoints de la API
                .excludePathPatterns("/api/test/**"); // Excluir endpoints de prueba si es necesario
    }
}
