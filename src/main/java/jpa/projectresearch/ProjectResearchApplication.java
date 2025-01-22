package jpa.projectresearch;

import jpa.projectresearch.ChatBot.ChatBotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectResearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectResearchApplication.class, args);
//        String userInput = "tôi cần tìm Wireless Mouse";
//        String response = ChatBotService.callChatbotApi(userInput);
//        System.out.println("Response from Gemini API: " + response);

    }

}
