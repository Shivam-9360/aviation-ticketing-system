package com.flight.booking.schedule.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Component
public class SeatWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, Set<WebSocketSession>> scheduleSessions = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Set<Integer>>> blockedSeats = new ConcurrentHashMap<>();
    // (scheduleId -> (sessionId -> [seatIds]))

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        String scheduleId = extractScheduleId(session);
        scheduleSessions.computeIfAbsent(scheduleId, k -> new CopyOnWriteArraySet<>()).add(session);
        blockedSeats.computeIfAbsent(scheduleId, k -> new ConcurrentHashMap<>());

        // Send existing blocked seats to the new user
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
                blockedSeats.remove(scheduleId); // Clear all blocked seats if no users left
            }
        }

        // Remove all seats blocked by this session
        blockedSeats.getOrDefault(scheduleId, new ConcurrentHashMap<>()).remove(session.getId());

        broadcastBlockedSeats(scheduleId);
        broadcastUserCount(scheduleId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String scheduleId = extractScheduleId(session);
        String sessionId = session.getId();
        String payload = message.getPayload();

        if (payload.startsWith("BLOCK_SEAT")) {
            String[] parts = payload.split(":");
            if (parts.length == 2) {
                String seatId = parts[1];

                // Get session's blocked seats and add new one
                blockedSeats.computeIfAbsent(scheduleId, k -> new ConcurrentHashMap<>())
                        .computeIfAbsent(sessionId, k -> new HashSet<>())
                        .add(Integer.parseInt(seatId));

                broadcastBlockedSeats(scheduleId);
            }
        } else if (payload.startsWith("UNBLOCK_SEAT")) {
            String[] parts = payload.split(":");
            if (parts.length == 2) {
                String seatId = parts[1];

                // Remove seat only if it was blocked by this session
                blockedSeats.getOrDefault(scheduleId, Map.of())
                        .getOrDefault(sessionId, Set.of())
                        .remove(Integer.parseInt(seatId));;

                // Remove session entry if no seats are left
                blockedSeats.get(scheduleId).computeIfPresent(sessionId, (k, v) -> v.isEmpty() ? null : v);

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

        // Collect all blocked seats from all users
        Set<Integer> allBlockedSeats = blockedSeats.getOrDefault(scheduleId, Map.of())
                .values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        String jsonSeats = allBlockedSeats.toString();

        String message = "{\"type\":\"BLOCKED_SEATS\",\"seats\": " + jsonSeats + "}";
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    private void broadcastBlockedSeatsToUser(WebSocketSession session, String scheduleId) throws IOException {
        // Collect all blocked seats from all users
        Set<Integer> allBlockedSeats = blockedSeats.getOrDefault(scheduleId, Map.of())
                .values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        String jsonSeats = allBlockedSeats.toString();

        String message = "{\"type\":\"BLOCKED_SEATS\",\"seats\": " + jsonSeats + "}";
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    private String extractScheduleId(WebSocketSession session) {
        String path = session.getUri().getPath();
        return path.substring(path.lastIndexOf("/") + 1); // Extract scheduleId from URL
    }
    public void broadcastBookedSeats(String scheduleId, List<Integer> bookedSeats) throws IOException {
        Set<WebSocketSession> sessions = scheduleSessions.get(scheduleId);
        if (sessions == null) return;
        Map<String, Set<Integer>> scheduleBlockedSeats = blockedSeats.get(scheduleId);
        if (scheduleBlockedSeats != null) {
            for (Set<Integer> seatSet : scheduleBlockedSeats.values()) {
                seatSet.removeAll(bookedSeats); // ✅ Remove booked seats from each session's blocked list
            }

            // ✅ Clean up empty session entries
            scheduleBlockedSeats.entrySet().removeIf(entry -> entry.getValue().isEmpty());

            // ✅ Remove the schedule entry if no more blocked seats exist
            if (scheduleBlockedSeats.isEmpty()) {
                blockedSeats.remove(scheduleId);
            }
        }
        String jsonSeats = bookedSeats.toString();
        String message = "{\"type\":\"BOOKED_SEATS\",\"seats\": " + jsonSeats + "}";

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
