package jpa.projectresearch.ChatBot;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class GoogleAuthService {
    @Value("${google.service.account.json}")
    private String serviceAccountJson;

    private static final String SCOPE = "https://www.googleapis.com/auth/generative-language";

    public String getAccessToken() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(serviceAccountJson))
                .createScoped(SCOPE);
        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }
}
