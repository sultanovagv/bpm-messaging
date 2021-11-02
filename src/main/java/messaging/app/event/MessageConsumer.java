package messaging.app.event;

import messaging.app.event.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.util.function.Consumer;

@Component
public class MessageConsumer {

    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageConsumer(ObjectMapper objectMapper, SimpMessagingTemplate template) {
        this.objectMapper = objectMapper;
        this.simpMessagingTemplate = template;
    }

    @Bean
    public Consumer<Message> receiver() {
        return value -> {
            try {
                var username = value.getUsername();
                var text = value.getText();
                System.out.println("--------------!!------------");
                simpMessagingTemplate.convertAndSend(String.format("/subscribe/%s", username),
                        HtmlUtils.htmlEscape(objectMapper.writeValueAsString(text)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        };
    }

}
