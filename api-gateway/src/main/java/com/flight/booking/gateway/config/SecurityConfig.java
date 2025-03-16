package com.flight.booking.gateway.config;

import com.flight.booking.gateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L); // Important: cache preflight response for 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsWebFilter(source);
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        // Explicitly permit OPTIONS requests for CORS preflight
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Auth Service - Public endpoints
                        .pathMatchers(HttpMethod.POST, "/auth-service/api/login").permitAll()
                        .pathMatchers(HttpMethod.POST, "/auth-service/api/register").permitAll()

                        // User Service - Admin only endpoints
                        .pathMatchers(HttpMethod.GET, "/user-service/api/user/email/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.GET, "/user-service/api/users").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.POST, "/user-service/api/user").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/user-service/api/user/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/user-service/api/users").hasRole("ADMIN")

                        // User Service - User/Admin endpoints (handled by controller-level checks)
                        .pathMatchers(HttpMethod.GET, "/user-service/api/user/**").authenticated()
                        .pathMatchers(HttpMethod.PUT, "/user-service/api/user").authenticated()

                        // Flight Service - Public endpoints
                        .pathMatchers(HttpMethod.GET, "/flight-service/api/flights").permitAll()
                        .pathMatchers(HttpMethod.GET, "/flight-service/api/flight/**").permitAll()

                        // Flight Service - Admin only endpoints
                        .pathMatchers(HttpMethod.POST, "/flight-service/api/flight").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/flight-service/api/flight").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/flight-service/api/flight/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/flight-service/api/flights").hasRole("ADMIN")

                        // Airport Service - Public endpoints
                        .pathMatchers(HttpMethod.GET, "/airport-service/api/airports").permitAll()
                        .pathMatchers(HttpMethod.GET, "/airport-service/api/airport/**").permitAll()

                        // Airport Service - Admin only endpoints
                        .pathMatchers(HttpMethod.POST, "/airport-service/api/airport").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.PUT, "/airport-service/api/airport").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/airport-service/api/airport/**").hasRole("ADMIN")

                        // Schedule Service - Public endpoints
                        .pathMatchers(HttpMethod.GET, "/schedule-service/api/schedules").permitAll()

                        // Schedule Service - Admin only endpoints
                        .pathMatchers(HttpMethod.POST, "/schedule-service/api/schedule").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/schedule-service/api/schedules").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/schedule-service/api/schedule/airport/**").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/schedule-service/api/schedule/flight/**").hasRole("ADMIN")

                        // Default policy - require authentication for any other endpoints
                        .anyExchange().authenticated())
                // Add the CORS filter BEFORE the JWT authentication filter
                .addFilterBefore(corsWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
}