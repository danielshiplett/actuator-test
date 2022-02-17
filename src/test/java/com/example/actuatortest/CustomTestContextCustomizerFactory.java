package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

import java.util.List;

@Slf4j
public class CustomTestContextCustomizerFactory implements ContextCustomizerFactory {

    private static final String BEAN_NAME = "applicationStartup";
    private final BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(2048);

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
        LOG.warn("createContextCustomizer");

        return (context, mergedConfig) -> {
            LOG.warn("context: {}", context);
            LOG.warn("pre-created applicationStartup: {}", applicationStartup);

            if(context instanceof AnnotationConfigServletWebServerApplicationContext) {
                AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext)context;

//                ctx.registerBean(BEAN_NAME, BufferingApplicationStartup.class, applicationStartup);
//
//                ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
//                constructorArgumentValues.addIndexedArgumentValue(0, 2048);
//
//                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//                beanDefinition.setBeanClass(BufferingApplicationStartup.class);
//                beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
//                ctx.registerBeanDefinition(BEAN_NAME, beanDefinition);

                ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
                beanFactory.registerSingleton(BEAN_NAME, applicationStartup);
                beanFactory.setApplicationStartup(applicationStartup);
            }
        };
    }
}
