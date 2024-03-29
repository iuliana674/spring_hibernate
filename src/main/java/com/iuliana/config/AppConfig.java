package com.iuliana.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HibernateConfig.class)
@ComponentScan(basePackages = "com.iuliana.service")
public class AppConfig {


}
