package com.example.actuatortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.BootstrapContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate;
import org.springframework.test.context.support.DefaultBootstrapContext;
import org.springframework.test.context.support.DefaultTestContext;
import org.springframework.test.context.web.WebMergedContextConfiguration;

@Slf4j
public class CustomTestContextBootstrapper extends SpringBootTestContextBootstrapper {

    private final ApplicationStartup applicationStartupToInject;

    public CustomTestContextBootstrapper() {
      LOG.warn("CustomTestContextBootstrapper!!!");
      applicationStartupToInject = new BufferingApplicationStartup(2048);
        WebMergedContextConfiguration foo;
    }

    @Override
    public TestContext buildTestContext() {
        return new CustomDefaultTestContext(getBootstrapContext().getTestClass(), buildMergedContextConfiguration(),
                getCacheAwareContextLoaderDelegate());
    }

//    @Override
//    public void setBootstrapContext(BootstrapContext bootstrapContext) {
//        LOG.warn("bootstrapContext: {}", bootstrapContext);
//
//        if(bootstrapContext instanceof DefaultBootstrapContext) {
//            DefaultBootstrapContext defaultBootstrapContext = (DefaultBootstrapContext)bootstrapContext;
//            LOG.warn("defaultBootstrapContext: {}", defaultBootstrapContext);
//
//            if(defaultBootstrapContext.getCacheAwareContextLoaderDelegate() instanceof DefaultCacheAwareContextLoaderDelegate) {
//                DefaultCacheAwareContextLoaderDelegate defaultCacheAwareContextLoaderDelegate = (DefaultCacheAwareContextLoaderDelegate)defaultBootstrapContext.getCacheAwareContextLoaderDelegate();
//
//            }
//        }
//
//        super.setBootstrapContext(bootstrapContext);
//    }
//
//    private void insertApplicationStartup(TestContext testContext) {
//        LOG.warn("insertApplicationStartup");
//        ApplicationContext applicationContext = testContext.getApplicationContext();
//        LOG.warn("applicationContext: {}", applicationContext);
//
//        ApplicationContext parentContext = applicationContext.getParent();
//        LOG.warn("parentContext: {}", applicationContext);
//
//        if(applicationContext == null) {
//            return;
//        }
//
//        if(applicationContext instanceof AnnotationConfigServletWebServerApplicationContext) {
//            ApplicationStartup existingApplicationStartup = (ApplicationStartup)applicationContext.getBean("applicationStartup");
//            LOG.warn("existing application startup: {}", existingApplicationStartup);
//
//            if(existingApplicationStartup == null || !(existingApplicationStartup instanceof BufferingApplicationStartup)) {
//                AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) applicationContext;
//                ctx.registerBean("applicationStartup", ApplicationStartup.class, () -> applicationStartupToInject);
//            }
//        }
//    }
}
