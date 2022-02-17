package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.CacheAwareContextLoaderDelegate;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.DefaultTestContext;
import org.springframework.util.Assert;

@Slf4j
public class CustomDefaultTestContext extends DefaultTestContext {

    private final BufferingApplicationStartup bufferingApplicationStartup = new BufferingApplicationStartup(2048);

    public CustomDefaultTestContext(DefaultTestContext testContext) {
        super(testContext);
    }

    public CustomDefaultTestContext(Class<?> testClass, MergedContextConfiguration mergedContextConfiguration, CacheAwareContextLoaderDelegate cacheAwareContextLoaderDelegate) {
        super(testClass, mergedContextConfiguration, cacheAwareContextLoaderDelegate);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        ApplicationContext context = super.getApplicationContext();

//        ApplicationContext context = this.cacheAwareContextLoaderDelegate.loadContext(this.mergedContextConfiguration);
//        if (context instanceof ConfigurableApplicationContext) {
//            @SuppressWarnings("resource")
//            ConfigurableApplicationContext cac = (ConfigurableApplicationContext) context;
//            Assert.state(cac.isActive(), () ->
//                    "The ApplicationContext loaded for [" + this.mergedContextConfiguration +
//                            "] is not active. This may be due to one of the following reasons: " +
//                            "1) the context was closed programmatically by user code; " +
//                            "2) the context was closed during parallel test execution either " +
//                            "according to @DirtiesContext semantics or due to automatic eviction " +
//                            "from the ContextCache due to a maximum cache size policy.");
//        }
//        return context;
//
//        if(context instanceof AnnotationConfigServletWebServerApplicationContext) {
//            AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext)context;
//            ctx.getBeanFactory().setApplicationStartup(bufferingApplicationStartup);
//        }

        return context;
    }
}
