package com.example.actuatortest.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StartupController {

    private final ApplicationStartup applicationStartup;

    public StartupController(ApplicationStartup applicationStartup) {
        this.applicationStartup = applicationStartup;
    }

    @GetMapping("/startup-timeline")
    public StartupTimeline startupTimeline() {
        LOG.warn("applicationStartup: {}", this.applicationStartup);
        applicationStartup.start("test.event");

        if(applicationStartup instanceof BufferingApplicationStartup) {
            BufferingApplicationStartup bufferingApplicationStartup = (BufferingApplicationStartup) applicationStartup;
            return bufferingApplicationStartup.getBufferedTimeline();
        }

        return null;
    }
}
