package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;

@Slf4j
public class BufferingApplicationStartupContextCustomizerFactory implements ContextCustomizerFactory {

    private static final String BEAN_NAME = "applicationStartup";
    private final BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(2048);

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
        return (context, mergedConfig) -> {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            beanFactory.registerSingleton(BEAN_NAME, applicationStartup);
            beanFactory.setApplicationStartup(applicationStartup);
        };
    }
}
