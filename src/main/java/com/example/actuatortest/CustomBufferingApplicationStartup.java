package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.core.metrics.StartupStep;

@Slf4j
public class CustomBufferingApplicationStartup extends BufferingApplicationStartup {

    boolean firstInstantiate = true;

    public CustomBufferingApplicationStartup() {
        super(2048);
        LOG.warn("Created CustomBufferingApplicationStartup");
    }

    @Override
    public void startRecording() {
        LOG.warn("startRecording");
        super.startRecording();

        AbstractBeanFactory foo;
    }

    @Override
    public StartupStep start(String name) {
        LOG.warn("start: {}", name);

        if(name.equalsIgnoreCase("spring.beans.instantiate") && firstInstantiate) {
            firstInstantiate = false;

            try {
                throw new Exception("foo");
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return super.start(name);
    }
}
