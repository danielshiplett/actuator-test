package com.example.actuatortest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ActuatorTestApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(ActuatorTestApplicationTests.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {

        ResponseEntity<String> response = testRestTemplate.exchange("/actuator", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.warn("actuators: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/health", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.warn("health: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/metrics", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.warn("metrics: {}", response.getBody());

        response = testRestTemplate.exchange("/actuator/startup", HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        LOG.warn("startup: {}", response.getBody());
    }
}
