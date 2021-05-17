package com.mqz.consumer.web.config.handler;

import com.mqz.consumer.web.config.interceptor.HeadThreadLocalInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/17 4:39 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
public class HandlerInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> ex = new ArrayList<>();
        ex.add("/doc.html");
        ex.add("/swagger-ui.html/**");
        ex.add("/swagger-resources/**");
        ex.add("v2/**");
        ex.add("/api-docs");
        registry.addInterceptor(new HeadThreadLocalInterceptor()).addPathPatterns("/**").excludePathPatterns(ex);
    }
}
