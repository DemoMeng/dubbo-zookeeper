package com.mqz.consumer.web.controller;

import com.mqz.api.service.annotations.ThreadLocalNeed;
import com.mqz.api.service.constants.CommonConstant;
import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.response.ResponseBean;
import com.mqz.api.service.system.LoadBalanceService;
import com.mqz.api.service.system.MQZUserService;
import com.mqz.api.service.system.ParamCheckService;
import com.mqz.api.service.system.ParamCheckService2;
import com.mqz.consumer.web.config.thread.LocalContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.service.GenericService;
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

    @DubboReference(url = "dubbo://192.168.2.11:20880")
    private LoadBalanceService loadBalanceService1;

    @DubboReference(url = "dubbo://192.168.5.32:20880")
    private LoadBalanceService loadBalanceService2;

    @DubboReference(url = "dubbo://192.168.5.32:20880",validation = "true") //dubbo调用方开启参数校验，服务提供方也需要
    private ParamCheckService paramCheckService;

    @DubboReference(url = "dubbo://192.168.5.32:20880")
    private ParamCheckService2 paramCheckService2;

    @GetMapping(value = "/index")
    @ApiOperation(value = "方法1")
    public ResponseBean index(){
        log.info("方法1调用了。。。。。");
        return ResponseBean.SUCCESS(mqzUserService.list());
    }


    @GetMapping(value = "/index2")
    @ApiOperation(value = "方法2")
    public ResponseBean index2(){
        log.info("方法2调用了。。。。。");
        return ResponseBean.SUCCESS(mqzUserService2.list());
    }

    @GetMapping(value = "/direct1")
    @ApiOperation(value = "直连dubbo服务1")
    public ResponseBean direct1(){
        loadBalanceService1.test2();
        return ResponseBean.SUCCESS("远程：192.168.2.11");
    }

    @GetMapping(value = "/direct2")
    @ApiOperation(value = "直连dubbo服务2")
    public ResponseBean direct2(){
        loadBalanceService2.test1();
        return ResponseBean.SUCCESS("本机：192.168.5.32");
    }


    @GetMapping(value = "/check")
    @ApiOperation(value = "参数校验")
    public ResponseBean check(){
        ParamCheckDTO dto = new ParamCheckDTO()
                .setName(null).setSex(20);
        paramCheckService.check1(dto);
        return ResponseBean.SUCCESS("校验");
    }

    @GetMapping(value = "/check2")
    @ApiOperation(value = "参数校验")
    public ResponseBean check2(){
        ParamCheckDTO dto = new ParamCheckDTO().setSex(20).setName(null);
        paramCheckService2.check2(dto);
        return ResponseBean.SUCCESS("校验2");
    }


    @GetMapping(value = "/head")
    @ApiOperation(value = "头部拦截")
    @ThreadLocalNeed()
    public ResponseBean head(){
        String head = LocalContext.get(CommonConstant.REQUEST_HEAD_NAME_B_O);
        return ResponseBean.SUCCESS("your request head is :"+head);
    }



}
