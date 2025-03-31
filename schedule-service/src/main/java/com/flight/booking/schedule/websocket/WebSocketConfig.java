package com.flight.booking.schedule.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final SeatWebSocketHandler seatWebSocketHandler;

    public WebSocketConfig(SeatWebSocketHandler seatWebSocketHandler) {
        this.seatWebSocketHandler = seatWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(seatWebSocketHandler, "/ws/seats/{scheduleId}").setAllowedOrigins("*");
    }
}
