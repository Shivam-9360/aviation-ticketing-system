package com.flight.booking.gateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        DTO<String> customResponse = new DTO<>(false,"Invalid Token",null);
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        new ObjectMapper().writeValue(response.getOutputStream(),customResponse);
//    }
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-Type", "application/json");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
