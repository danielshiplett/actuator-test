package com.example.actuatortest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

@Slf4j
public class CustomAnnotationConfigServletWebServerApplicationContext extends AnnotationConfigServletWebServerApplicationContext {

    public CustomAnnotationConfigServletWebServerApplicationContext() {
        super();
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        LOG.warn("getAutowireCapableBeanFactory");
        return super.getAutowireCapableBeanFactory();
    }
}
