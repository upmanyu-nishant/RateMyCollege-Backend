package com.project.rate_my_college.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

//@Configuration
public class CorsConfig {

    @Value("${frontend.url}")
    private String frontendUrl; 

    //@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow specific origins
        config.setAllowedOrigins(Arrays.asList(frontendUrl));

        // Allow specific HTTP methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH"));

        // Allow specific headers
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);

        // Apply configuration to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
