 package com.example.dogshelter.config;

 import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 @Configuration
 public class CorsConfig {

     @Bean
     public WebMvcConfigurer corsConfigurer() {
         return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")           // Apply CORS to all API endpoints
                        .allowedOrigins(
                            "http://localhost:5500",    // Vanilla JS dev server
                            "http://localhost:3000",    // Vue.js dev server
                           "http://localhost:5175"     // React dev server
                                       ).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                         .allowedHeaders("*")           // Allow all headers
                         .allowCredentials(true);      // If you use cookies/auth
             }
        };
     }
 }
