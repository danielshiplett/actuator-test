package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class ActuatorTestApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws IOException {

        ResponseEntity<String> response = testRestTemplate.exchange("/actuator", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.trace("actuators: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/health", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.trace("health: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/metrics", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.trace("metrics: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/startup", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        String body = response.getBody();
        LOG.info("startup: {}", body);

        File file = new File(this.getClass().getClassLoader().getResource(".").getFile() + "/spring-boot-test-startup.json");
        if (file.createNewFile()) {
            LOG.info("File is created!");
        } else {
            LOG.info("File already exists.");
        }

        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(body);
        fileWriter.close();
    }
}
