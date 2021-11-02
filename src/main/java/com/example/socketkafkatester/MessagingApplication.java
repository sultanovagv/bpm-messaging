package com.example.socketkafkatester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MessagingApplication {

    private static final Logger log = LoggerFactory.getLogger(MessagingApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MessagingApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t localhost : {}", env.getProperty("server.port"));
    }

}
