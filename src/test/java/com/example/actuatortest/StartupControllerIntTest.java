package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@BootstrapWith(CustomTestContextBootstrapper.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = ActuatorTestApplication.class,
//        initializers = StartupApplicationContextInitializer.class)
//@TestExecutionListeners(value = {BufferingApplicationStartupInjectingListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@AutoConfigureMockMvc
@Slf4j
class StartupControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationStartup applicationStartup;

    @BeforeEach
    void beforeEach() {
        applicationStartup.start("before.each");
    }

    @Test
    void testControllerReturnsData() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/startup-timeline"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        LOG.warn("result: {}", mvcResult.getResponse().getContentAsString());
    }
}
