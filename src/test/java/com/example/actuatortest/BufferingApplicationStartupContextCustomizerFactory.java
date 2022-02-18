package com.example.actuatortest;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.MergedContextConfiguration;

import java.util.List;

public class BufferingApplicationStartupContextCustomizerFactory implements ContextCustomizerFactory {

    private final BufferingApplicationStartupContextCustomizer contextCustomizer = new BufferingApplicationStartupContextCustomizer();

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
        return contextCustomizer;
    }

    public static class BufferingApplicationStartupContextCustomizer implements ContextCustomizer {

        private static final String BEAN_NAME = "applicationStartup";

        // This must be static.  It is the only field used in calculating the hashCode and it must stay the same through
        // ALL tests or else you will get context refreshes with ever test class.
        private static final BufferingApplicationStartup APPLICATION_STARTUP = new BufferingApplicationStartup(2048);

        @Override
        public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

            Object possibleSingleton = beanFactory.getSingleton(BEAN_NAME);

            // The only way it wouldn't be an instance of a BufferingApplicationStartup is if it is null or we haven't
            // run yet (and it is the DefaultApplicationStartup).  In either case, jam our BufferingApplicationStartup
            // in here.
            if(!(possibleSingleton instanceof BufferingApplicationStartup)) {
                beanFactory.registerSingleton(BEAN_NAME, APPLICATION_STARTUP);
                beanFactory.setApplicationStartup(APPLICATION_STARTUP);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            return obj != null && obj.getClass() == getClass();
        }

        @Override
        public int hashCode() {
            return APPLICATION_STARTUP.hashCode();
        }
    }
}
