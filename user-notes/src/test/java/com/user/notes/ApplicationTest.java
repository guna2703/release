package com.user.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.user.notes")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.user.notes")
@EntityScan(basePackages = "com.user.notes")
public class ApplicationTest {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
