package com.example.socketkafkatester.controller;

import com.example.socketkafkatester.event.MessageProducer;
import com.example.socketkafkatester.event.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;

    @PostMapping("send")
    public void sendToUser(@RequestBody Message message) {
        message.setUsername("sultanovagv");
        messageProducer.sendToUser(message);
    }

}
