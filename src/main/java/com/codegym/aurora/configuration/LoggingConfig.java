package com.codegym.aurora.configuration;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }
}