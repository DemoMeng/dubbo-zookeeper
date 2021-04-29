package com.mqz.consumer.web.controller;

import com.mqz.api.service.system.MQZUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
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
@Api(tags = "哈咯沃德")
@Slf4j
public class HelloController {

    @DubboReference(version = "0.1")
    private MQZUserService mqzUserService;

    @DubboReference(version = "0.2")
    private MQZUserService mqzUserService2;

    @GetMapping(value = "/index")
    @ApiOperation(value = "方法1")
    public Object index(){
        log.info("方法1调用了。。。。。");
        return mqzUserService.list();
    }

    @GetMapping(value = "/index2")
    @ApiOperation(value = "方法2")
    public Object index2(){
        log.info("方法2调用了。。。。。");
        return mqzUserService2.list();
    }


}
