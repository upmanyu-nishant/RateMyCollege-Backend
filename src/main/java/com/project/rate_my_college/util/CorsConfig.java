package com.project.rate_my_college.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


//@Configuration
public class CorsConfig {

    @Value("${frontend.url}")
    private String frontendUrl; 

    //@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow specific origins
       config.addAllowedOrigin(frontendUrl);

        // Allow specific HTTP methods
        config.addAllowedMethod("*");

        // Allow specific headers
        config.addAllowedHeader("*");

        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);

        // Apply configuration to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
