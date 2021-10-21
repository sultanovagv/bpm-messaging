package com.example.socketkafkatester.event;

import com.example.socketkafkatester.event.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;

    public MessageProducer(ObjectMapper objectMapper, StreamBridge streamBridge) {
        this.objectMapper = objectMapper;
        this.streamBridge = streamBridge;
    }

    public final void sendToUser(Message message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            streamBridge.send("publisher", jsonMessage);

        } catch (Exception e) {
            throw new RuntimeException("Could not transform and send Message due to: " + e.getMessage(), e);
        }
    }

}
