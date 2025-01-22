package jpa.projectresearch.ChatBot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jpa.projectresearch.Service.OrderService;
import jpa.projectresearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ChatBotService {

    @Autowired
    GoogleAuthService googleAuthService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private static final String API_KEY = "AIzaSyBBD5TIxdqvvwBOch5eOl1Vdj3RuzFyoUg";

    // Gọi API Chatbot
    public static String callChatbotApi(String userMessage) {
        try {
            String payload = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + userMessage + "\" } ] } ] }";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String fullUrl = API_URL + "?key=" + API_KEY;
            HttpEntity<String> request = new HttpEntity<>(payload, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(fullUrl, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "API call failed. Status code: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    // Phát hiện intent dựa trên từ khóa
    private String detectIntent(String chatbotText) {
        Map<String, List<String>> intentKeywords = new HashMap<>();
        intentKeywords.put("get_product_info", Arrays.asList("tìm chuột", "chuột không dây", "mua chuột","chuột"));
        intentKeywords.put("get_order_status", Arrays.asList("kiểm tra đơn hàng", "trạng thái đơn hàng"));

        for (Map.Entry<String, List<String>> entry : intentKeywords.entrySet()) {
            for (String keyword : entry.getValue()) {
                if (chatbotText.contains(keyword)) {
                    return entry.getKey();
                }
            }
        }
        return "unknown";
    }

    // Xử lý phản hồi người dùng
    public Object handleUserMessage(String userMessage) {
        try {
            String chatbotResponse = callChatbotApi(userMessage);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(chatbotResponse);
            System.out.println(chatbotResponse);
            JsonNode candidatesNode = rootNode.path("candidates");
            if (candidatesNode.isArray() && !candidatesNode.isEmpty()) {
                String chatbotText = candidatesNode.get(0).path("content").path("parts").get(0).path("text").asText();

                String intent = detectIntent(chatbotText);

                switch (intent) {
                    case "get_product_info":
                        return productService.findProductName("Wireless Mouse");
                    case "get_order_status":
                        return "Intent: get_order_status, Content: " + chatbotText;
                    default:
                        return "Xin lỗi, tôi không hiểu yêu cầu của bạn.";
                }
            } else {
                return "Không tìm thấy dữ liệu trong phản hồi của chatbot.";
            }
        } catch (Exception e) {
            return "Đã xảy ra lỗi khi xử lý yêu cầu: " + e.getMessage();
        }
    }
}
