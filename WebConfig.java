package com.example.gym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Deprecated
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS to all endpoints starting with /api/
                .allowedOrigins("http://127.0.0.1:5500")// Allow requests from your frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials such as cookies or tokens
    }
}
