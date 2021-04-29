package com.mqz.consumer.web.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 版权所有 © Copyright 2012<br>
 * @Author： 蒙大拿
 * @Date：2021/4/28
 * @Description
 * @About： https://github.com/DemoMeng
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;


    @Bean(name = "服务消费者")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo()).groupName("服务消费者")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mqz.consumer.web.controller"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/simple/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("在线接口文档")
                .description("蒙大拿")
                .license("copyRight@mqz")
                .description("接口描述文档")
                .termsOfServiceUrl("https://github.com/DemoMeng")
                .contact("13128507506")
                .version("1.0")
                .build();
    }

}
