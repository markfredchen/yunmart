package com.handchina.yunmart.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by markfredchen on 9/3/15.
 */
@Configuration
public class H2Config {
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
            new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}
