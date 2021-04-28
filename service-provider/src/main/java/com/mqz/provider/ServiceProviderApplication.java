package com.mqz.provider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.mqz.provider"})
@EnableDubbo//开启dubbo
@DubboComponentScan(basePackages = {"com.mqz.provider"})//dubbo组件基础包路径
public class ServiceProviderApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceProviderApplication.class, args);
    }

}
