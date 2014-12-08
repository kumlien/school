package com.kumliens.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(value=WebSecurityConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Bean(name="validator")
    public LocalValidatorFactoryBean validator() {
    	return new LocalValidatorFactoryBean();
    }
    
    
}
