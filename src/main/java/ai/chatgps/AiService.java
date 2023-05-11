package ai.chatgps;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Service {

    public HttpResponse<String> getOpenAi(Root input) throws IOException, InterruptedException {
        try {
            // Converting (POJO)input to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(input);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openai80.p.rapidapi.com/chat/completions"))
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", "f97aefd7aemsh71ad17ff2a959d1p14e65djsn82acd5d21dbb")
                    .header("X-RapidAPI-Host", "openai80.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
            
    }
}
