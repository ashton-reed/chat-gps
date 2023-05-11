package ai.chatgps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

@Service
public class AiService {

    public void getOpenAi(InputModel input) throws IOException, InterruptedException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ask a question: ");
            while (!sc.equals("break")) {
                input.messages.get(0).content = sc.nextLine();
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
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                ObjectMapper mapper = new ObjectMapper();
                Query list = mapper.readValue(response.body(), Query.class);
                System.out.println(list.choices.get(0).message.content);
            }
//            return response;
        } catch (Exception e) {
         throw new RuntimeException(String.format("Error making GET request to OpenAi %s",e));
        }
    }
}
