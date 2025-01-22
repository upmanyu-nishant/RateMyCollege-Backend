package com.project.rate_my_college.security;

import com.project.rate_my_college.util.JwtFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
            // Disable CSRF as it's not needed for stateless APIs
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS

            // Configure authorization rules
            .authorizeHttpRequests(auth -> auth
                // Publicly accessible endpoints
                .requestMatchers(
                    "/api/auth/authenticate", 
                    "/api/ratingCards/college/**", 
                    "/api/ratingCards/email/**", 
                    "/api/colleges",               // Allow access to GET /api/colleges
                    "/api/colleges/search",        // Allow access to GET /api/colleges/search
                    "/api/colleges/{id}"           // Allow access to GET /api/colleges/{id}
                ).permitAll()
                
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )

            // Add JWT filter before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // Disable HTTP Basic authentication
            .httpBasic(httpBasic -> httpBasic.disable())

            // Configure exception handling
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, authException) -> 
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .accessDeniedHandler((request, response, accessDeniedException) -> 
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
                )
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //Allow all origins
        configuration.addAllowedOriginPattern(frontendUrl);

        // Allow all methods (GET, POST, PUT, DELETE, OPTIONS, etc.)
        configuration.addAllowedMethod("*");

        // Allow all headers
        configuration.addAllowedHeader("*");

        // Allow credentials (cookies, etc.)
        configuration.setAllowCredentials(true);

        // Apply the configuration to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
