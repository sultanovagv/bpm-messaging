package com.example.socketkafkatester.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String username;
    private String text;
}
