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

// Referenced from https://medium.com/@madhavpaliwal/spring-security-integration-on-api-gateway-in-spring-boot-microservices-818d021d0d31#id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IjI1ZjgyMTE3MTM3ODhiNjE0NTQ3NGI1MDI5YjAxNDFiZDViM2RlOWMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDIxMTE5Njk2NDc2MTYwNzc5MjUiLCJlbWFpbCI6InNoaXZhbS5zdXlhc2hAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5iZiI6MTc0MTQzNzgzMywibmFtZSI6IlNoaXZhbSBLdWxrYXJuaSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NKaG02OWVIUWVpTEVsNFRqX1lZRlpRUnNfNDRPUWVMX3ZiN2dNM3ZKdGE4VVpCY21jQT1zOTYtYyIsImdpdmVuX25hbWUiOiJTaGl2YW0iLCJmYW1pbHlfbmFtZSI6Ikt1bGthcm5pIiwiaWF0IjoxNzQxNDM4MTMzLCJleHAiOjE3NDE0NDE3MzMsImp0aSI6IjU5NWNiZDc4NzkwOTIzNTRmMTViMTNhZDI2YTU0ZTI3ZGMyNzNjM2QifQ.MF1ow7rNkRK2U2QSX3CJ39zidNfpwIRKSCU7FXhp2EDP1g6hNMy52ufmogBEyU8bozDrsQeF-04v-fk3tVTTe7DfK3Oa3XHEYhme9DZ-G4QnVzwl6V_e0C7R9neeJ6XOm2NF2efQeyU7g7TLmrWF4jB-eKFtlhyBKZ-DGpqM2OglBpOlsOg8_R0qr8fxzUNg_HR7GJ9ere2XTkoD1ipAFHUFh96tKf38bezZWXBh-8_Yh2cIceTwdHit6EHkwjSQ7WfdQYGpw-S8Jo_zsTRhEb5oBAxqos_9vV4JCWtPM6CeLX9f7QOUPXjLDFPBLhL0sDXymLByqyZaDtQEkxUQRA

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
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
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
}
