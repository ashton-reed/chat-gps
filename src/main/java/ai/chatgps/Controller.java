import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@RestController
public class Controller {

    @GetMapping(value = "/chat", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void testApi() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openai80.p.rapidapi.com/chat/completions"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "f97aefd7aemsh71ad17ff2a959d1p14e65djsn82acd5d21dbb")
                .header("X-RapidAPI-Host", "openai80.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n    \"model\": \"gpt-3.5-turbo\"," +
                        "\n    \"messages\": [\n        {\n            \"role\": \"user\",\n            " +
                        "\"content\": \"Hello!\"\n        }\n    ]\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
