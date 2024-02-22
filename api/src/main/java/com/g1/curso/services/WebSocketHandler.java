package com.g1.curso.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.g1.curso.models.GlobalChatModel;
import com.g1.curso.repositories.GlobalChatRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Component
@Lazy
public class WebSocketHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = new HashSet<>();
    @Autowired(required = true)
    private GlobalChatRepository globalChatRepository ;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        super.afterConnectionEstablished(session);
        sessions.add(session);
        System.out.println("Nova conexão estabelecida: " + session.getId());
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        Set<String> dbMessage = new HashSet<>();

        String payload = message.getPayload();
        GlobalChatModel globalChatModel = new GlobalChatModel();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode msgJson = objectMapper.readTree(payload);

        UUID uuid_post = UUID.fromString(msgJson.get("uuid_post").asText());
        String username = msgJson.get("username").asText();
        String content = msgJson.get("content").asText();

        globalChatModel.setContent(content);
        globalChatModel.setUser_post(uuid_post);
        globalChatModel.setUsername(username);

        try {
            globalChatRepository.save(globalChatModel);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        for(WebSocketSession s : sessions) {
            try {
                dbMessage.add(payload);
                for( String msg : dbMessage){
                    s.sendMessage(new TextMessage(msg));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        session.sendMessage(new TextMessage("Sua Menssagem Foi Recebida: " + payload));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
            throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
        System.out.println("Conexão fechada: " + session.getId());
    }
}
