package com.mqz.consumer.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.mqz.consumer.web"})

public class WebConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebConsumerApplication.class, args);
    }

}
