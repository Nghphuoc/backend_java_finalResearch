package jpa.projectresearch.API;

import jpa.projectresearch.ChatBot.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBootController {

    @Autowired
    private ChatBotService chatbotService;

    @PostMapping("/message")
    public ResponseEntity<?> handleMessage(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        Object response = chatbotService.handleUserMessage(userMessage);
        return ResponseEntity.ok(response);
    }
}
