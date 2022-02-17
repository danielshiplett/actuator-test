package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class BufferingApplicationStartupInjectingListener implements TestExecutionListener {

    private final ApplicationStartup applicationStartupToInject;

    public BufferingApplicationStartupInjectingListener() {
        applicationStartupToInject = new BufferingApplicationStartup(2048);
    }

    @Override
    public void beforeTestClass(TestContext testContext) {
        LOG.warn("beforeTestClass");
        insertApplicationStartup(testContext);
    }

    @Override
    public void prepareTestInstance(TestContext testContext) {
        LOG.warn("prepareTestInstance");
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
        LOG.warn("beforeTestMethod");
    }

    @Override
    public void beforeTestExecution(TestContext testContext) {
        LOG.warn("beforeTestExecution");
    }

    private void insertApplicationStartup(TestContext testContext) {
        LOG.warn("insertApplicationStartup");
        ApplicationContext applicationContext = testContext.getApplicationContext();
        LOG.warn("applicationContext: {}", applicationContext);

        ApplicationContext parentContext = applicationContext.getParent();
        LOG.warn("parentContext: {}", applicationContext);

        if(applicationContext == null) {
            return;
        }

        if(applicationContext instanceof AnnotationConfigServletWebServerApplicationContext) {
            ApplicationStartup existingApplicationStartup = (ApplicationStartup)applicationContext.getBean("applicationStartup");
            LOG.warn("existing application startup: {}", existingApplicationStartup);

            if(existingApplicationStartup == null || !(existingApplicationStartup instanceof BufferingApplicationStartup)) {
                AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) applicationContext;
                ctx.registerBean("applicationStartup", ApplicationStartup.class, () -> applicationStartupToInject);
            }
        }
    }
}
