package ai.chatgps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Controller {
    private final AiService service;

    @Autowired
    public Controller(AiService service) { this.service = service; }

    @PostMapping(value = "/chat", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void testApi(@RequestBody InputModel input)  {
        try {
            service.getOpenAi(input);
        } catch (Exception e) {
            log.warn(String.format("Error from controller %s",e));
        }
    }
}
