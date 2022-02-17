package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class StartupApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        LOG.warn("applicationContext: {}", applicationContext);

        if(applicationContext instanceof AnnotationConfigServletWebServerApplicationContext) {
            AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) applicationContext;
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClassName(CustomBufferingApplicationStartup.class.getName());
//            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
//            constructorArgumentValues.addIndexedArgumentValue(0, 2048);
//            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            ctx.registerBeanDefinition("applicationStartup", beanDefinition);
            LOG.warn("added bean definition: {}", beanDefinition);
        }
    }

}
