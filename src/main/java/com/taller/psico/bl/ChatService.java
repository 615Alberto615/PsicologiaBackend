package com.taller.psico.bl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taller.psico.dto.ChatMessageDto;
import com.taller.psico.entity.Completion;
import com.taller.psico.repository.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private CompletionRepository completionRepository;

    @Value("${chatgpt.url}")
    private String chatUrl;

    @Value("${chatgpt.apikey}")
    private String apiKey;

    public ChatMessageDto sendMessage(ChatMessageDto chatMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String requestBody = String.format("{\"model\": \"gpt-4\", \"prompt\": \"%s\", \"max_tokens\": 150}", chatMessage.getMessage());

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(chatUrl, request, String.class);
        String completionText = parseCompletionResponse(response.getBody()); // Parsing the response

        Completion completion = new Completion();
        completion.setSessionId(chatMessage.getSessionId());
        completion.setPrompt(chatMessage.getMessage());
        completion.setCompletion(completionText);
        completion.setCreatedAt(LocalDateTime.now());
        completionRepository.save(completion);

        chatMessage.setResponse(completionText);
        return chatMessage;
    }

    private String parseCompletionResponse(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            JsonNode choicesNode = rootNode.path("choices");
            if (!choicesNode.isMissingNode() && choicesNode.isArray() && choicesNode.has(0)) {
                JsonNode textNode = choicesNode.get(0).path("text");
                String responseText = textNode.asText().trim(); // Getting the text element and trimming it

                // Replacing newline characters with formatted HTML or plain text bullet points
                responseText = responseText.replaceAll("\\n\\n", "\n");  // Replace with "<br>" for HTML or use " " for plain text
                responseText = responseText.replaceAll("(\\d+)\\. ", "\n$1. "); // Optional: Format numbered lists with line breaks before numbers

                return responseText;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing errors or log them appropriately
            return "There was an error processing your request.";
        }
        return "No response was received.";
    }


    public List<ChatMessageDto> getHistoryBySessionId(String sessionId) {
        return completionRepository.findBySessionId(sessionId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ChatMessageDto getHistoryById(Long id) {
        Optional<Completion> completion = completionRepository.findById(id);
        return completion.map(this::convertToDto).orElse(null);
    }

    private ChatMessageDto convertToDto(Completion completion) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setSessionId(completion.getSessionId());
        dto.setMessage(completion.getPrompt());
        dto.setResponse(completion.getCompletion());
        return dto;
    }
}
