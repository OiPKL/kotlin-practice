package com.example.websocketdemo.controller;

import com.example.websocketdemo.dto.SocketMessageDto;
import com.google.gson.Gson;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandlerImpl extends TextWebSocketHandler {
    private final Gson gson = new Gson();
    // 세션을 저장하기 위한 ConcurrentHashMap
    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        System.out.println("서버: 연결이 성립되었습니다. 세션 ID = " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("서버: 메시지 수신: " + payload);

        // JSON을 파싱하여 DTO로 변환 (필요한 경우)
        SocketMessageDto socketMessage = gson.fromJson(payload, SocketMessageDto.class);
        
        // 모든 세션에 대해 메시지 브로드캐스트
        for (WebSocketSession s : sessions.values()) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        System.out.println("서버: 연결이 종료되었습니다. 세션 ID = " + session.getId());
    }
}
