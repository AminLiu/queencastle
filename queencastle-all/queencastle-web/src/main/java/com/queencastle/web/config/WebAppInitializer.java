package com.queencastle.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.queencastle.dao.config.DaoConfig;
import com.queencastle.dao.config.JedisConfig;
import com.queencastle.service.config.ServiceConfig;
import com.queencastle.web.filters.UserLoginFilter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {DaoConfig.class, JedisConfig.class, ServiceConfig.class,};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebMvcConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        ShallowEtagHeaderFilter etagFilter = new ShallowEtagHeaderFilter();

        UserLoginFilter loginFilter = new UserLoginFilter();

        return new Filter[] {characterEncodingFilter, etagFilter, loginFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
    }

    static class ConfigApplicationContextInitializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {

        private Logger logger = LoggerFactory.getLogger(ConfigApplicationContextInitializer.class);

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            try {
                environment.getPropertySources().addFirst(
                        new ResourcePropertySource("classpath:filtered.properties"));
                logger.info("filtered.properties loaded");
            } catch (IOException e) {
                logger.error("didn't find filtered.properties in classpath so not loading it in the AppContextInitialized");
            }
        }
    }
}
