package com.flight.booking.schedule.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class SeatWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, Set<WebSocketSession>> scheduleSessions = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> blockedSeats = new ConcurrentHashMap<>(); // Stores blocked seats per schedule

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String scheduleId = extractScheduleId(session);
        scheduleSessions.computeIfAbsent(scheduleId, k -> new CopyOnWriteArraySet<>()).add(session);
        blockedSeats.computeIfAbsent(scheduleId, k -> new CopyOnWriteArraySet<>());

        // Broadcast blocked seats to the new user
        broadcastBlockedSeatsToUser(session, scheduleId);
        broadcastUserCount(scheduleId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws IOException {
        String scheduleId = extractScheduleId(session);
        Set<WebSocketSession> sessions = scheduleSessions.get(scheduleId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                scheduleSessions.remove(scheduleId);
                blockedSeats.remove(scheduleId); // Clear blocked seats when no users remain
            }
        }
        broadcastUserCount(scheduleId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String scheduleId = extractScheduleId(session);
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // Handle seat blocking/unblocking
        if (payload.startsWith("BLOCK_SEAT")) {
            String[] parts = payload.split(":");
            if (parts.length == 2) {
                String seatId = parts[1];
                blockedSeats.get(scheduleId).add(seatId);
                broadcastBlockedSeats(scheduleId);
            }
        } else if (payload.startsWith("UNBLOCK_SEAT")) {
            String[] parts = payload.split(":");
            if (parts.length == 2) {
                String seatId = parts[1];
                blockedSeats.get(scheduleId).remove(seatId);
                broadcastBlockedSeats(scheduleId);
            }
        }
    }

    private void broadcastUserCount(String scheduleId) throws IOException {
        Set<WebSocketSession> sessions = scheduleSessions.get(scheduleId);
        if (sessions == null) return;

        String message = "{\"type\":\"USER_COUNT\",\"count\": " + sessions.size() + "}";
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    private void broadcastBlockedSeats(String scheduleId) throws IOException {
        Set<WebSocketSession> sessions = scheduleSessions.get(scheduleId);
        if (sessions == null) return;

        String message = "{\"type\":\"BLOCKED_SEATS\",\"seats\": " + blockedSeats.get(scheduleId) + "}";
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    private void broadcastBlockedSeatsToUser(WebSocketSession session, String scheduleId) throws IOException {
        String message = "{\"type\":\"BLOCKED_SEATS\",\"seats\": " + blockedSeats.get(scheduleId) + "}";
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    private String extractScheduleId(WebSocketSession session) {
        String path = session.getUri().getPath();
        return path.substring(path.lastIndexOf("/") + 1); // Extract scheduleId from URL
    }
}
