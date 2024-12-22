package com.example.daw1_proyecto_final.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Habilita CORS para todas las rutas
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes desde tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*");  // Permite todos los headers
    }
}