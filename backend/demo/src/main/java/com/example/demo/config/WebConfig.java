package com.example.demo.config;

// src/main/java/com/example/demo/config/WebConfig.java


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Allow CORS for all endpoints starting with /api/
                .allowedOrigins("http://localhost:3000")  // Allow requests from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify allowed methods
                .allowCredentials(true);
    }
}
