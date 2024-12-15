package com.creditcard.management.credit_card_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and() // Habilita CORS
                .csrf().disable() // Desactiva CSRF (solo en desarrollo)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Permite todas las solicitudes
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // Permite todos los orígenes
        config.addAllowedMethod("*"); // Permite todos los métodos HTTP
        config.addAllowedHeader("*"); // Permite todas las cabeceras
        config.setAllowCredentials(false); // Deshabilita credenciales
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}