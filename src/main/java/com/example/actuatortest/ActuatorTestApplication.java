package com.example.actuatortest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class ActuatorTestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ActuatorTestApplication.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.run(args);
    }
}
