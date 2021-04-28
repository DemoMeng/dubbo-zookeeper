package com.mqz.consumer.web.controller;

import com.mqz.api.service.system.MQZUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/4/28 4:02 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @DubboReference(version = "0.1")
    private MQZUserService mqzUserService;

    @DubboReference(version = "0.2")
    private MQZUserService mqzUserService2;

    @GetMapping(value = "/index")
    public Object index(){
        return mqzUserService.list();
    }

    @GetMapping(value = "/index2")
    public Object index2(){
        return mqzUserService2.list();
    }


}
