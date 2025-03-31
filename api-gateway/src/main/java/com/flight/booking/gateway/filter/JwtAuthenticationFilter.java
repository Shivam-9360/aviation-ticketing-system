package com.flight.booking.gateway.filter;

import com.flight.booking.gateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter implements WebFilter {
    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String requestToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);

            if (jwtService.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = jwtService.extractUserDetails(token);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContext context = new SecurityContextImpl(authenticationToken);

                // Set the Principal header in the request
                ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                        .header("email", user.getUsername())
                        .header("role", user.getAuthorities().stream().findFirst()
                            .map(GrantedAuthority::getAuthority).orElse(""))
                        .build();

                // Create a new ServerWebExchange with the modified request
                ServerWebExchange modifiedExchange = exchange.mutate()
                        .request(modifiedRequest)
                        .build();

                return chain.filter(modifiedExchange).contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
            }
        }
        return chain.filter(exchange);
    }
}
