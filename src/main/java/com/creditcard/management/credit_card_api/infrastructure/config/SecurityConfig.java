package com.creditcard.management.credit_card_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for Spring Security.
 * Configures CORS and CSRF settings and defines security rules for HTTP requests.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the Spring Security filter chain.
     * - Enables CORS (Cross-Origin Resource Sharing).
     * - Disables CSRF (Cross-Site Request Forgery) protection (intended for development environments).
     * - Allows all HTTP requests without restrictions.
     *
     * @param http The HttpSecurity object for configuring security settings.
     * @return A SecurityFilterChain defining the security rules.
     * @throws Exception If an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    /**
     * Configures a CORS filter.
     * - Allows all origins, HTTP methods, and headers.
     * - Credentials (e.g., cookies) are disabled.
     *
     * This configuration is applied globally to all endpoints.
     *
     * @return A CorsFilter bean for handling CORS configuration.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(false);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
