package com.guvi.mvc_demo.config;

import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JspConfig {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> staticResourceCustomizer() {
        return factory ->
                factory.addContextCustomizers(context -> context.addServletMappingDecoded("*.jsp", "jsp"));
    }
}
