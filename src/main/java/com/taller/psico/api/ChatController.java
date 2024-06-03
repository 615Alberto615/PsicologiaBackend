package com.taller.psico.api;

import com.taller.psico.bl.ChatService;
import com.taller.psico.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    ///cambios
    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<ChatMessageDto> sendMessage(@RequestBody ChatMessageDto chatMessage) {
        ChatMessageDto response = chatService.sendMessage(chatMessage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<ChatMessageDto>> getHistory(@PathVariable String sessionId) {
        List<ChatMessageDto> history = chatService.getHistoryBySessionId(sessionId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/history/detail/{historyId}")
    public ResponseEntity<ChatMessageDto> showHistorial(@PathVariable Long historyId) {
        ChatMessageDto historyDetail = chatService.getHistoryById(historyId);
        return ResponseEntity.ok(historyDetail);
    }

    @PostMapping("/start")
    public ResponseEntity<ChatMessageDto> iniciarChat() {
        ChatMessageDto response = new ChatMessageDto();
        response.setSessionId(generateRandomSessionId(12));
        response.setMessage("Soy un asistente virtual llamado PsicoGPT, entrenado para proporcionar evaluaciones psicológicas.");
        response.setResponse("¡Hola! Soy Sico, tu asistente virtual. Estoy aquí para ayudarte a resolver tus dudas de la forma más rápida y sencilla posible. Para empezar, ¿en qué puedo ayudarte hoy?");

        return ResponseEntity.ok(response);
    }

    private String generateRandomSessionId(int length) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
