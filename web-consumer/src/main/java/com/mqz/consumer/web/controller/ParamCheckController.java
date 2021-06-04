package com.mqz.consumer.web.controller;

import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.response.ResponseBean;
import com.mqz.api.service.system.ParamCheckService;
import com.mqz.api.service.system.ParamCheckService2;
import com.mqz.api.service.system.ParamCheckService3;
import com.mqz.consumer.web.config.exception.exceptions.ParamValidException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/6/3 9:06 上午
 * @Description
 * @About： https://github.com/DemoMeng
 */

@RestController
@RequestMapping("/param")
@Api(tags = "参数校验")
@Slf4j
public class ParamCheckController {

    @DubboReference(url = "dubbo://192.168.5.32:20880",validation = "true") //dubbo调用方开启参数校验，服务提供方也需要
    private ParamCheckService paramCheckService;

    //@DubboReference(url = "dubbo://192.168.5.32:20880")
    @DubboReference(timeout = 5000,validation = "true")
    private ParamCheckService2 paramCheckService2;

    @DubboReference()
    private ParamCheckService3 paramCheckService3;

    @GetMapping(value = "/check")
    @ApiOperation(value = "参数校验（duboo官方提供的方式）")
    public ResponseBean check(){
        ParamCheckDTO dto = new ParamCheckDTO()
                .setName(null).setSex(20);
        paramCheckService.check1(dto);
        return ResponseBean.SUCCESS("校验");
    }

    @GetMapping(value = "/check2")
    @ApiOperation(value = "参数校验 （service层校验）") // TODO 这种方式和Dubbo官方提供的基本一致，不太建议使用，尽量在 consumer 层进行校验，避免请求打到 provider  需要在全局异常解析异常信息
    public ResponseBean check2(){
        ParamCheckDTO dto = new ParamCheckDTO().setSex(20).setName(null);
        paramCheckService2.check2(dto);
        return ResponseBean.SUCCESS("校验2");
    }


    /**
     * 参数校验尽量在 consumer 拦截即可，避免过多的请求打到 provider，资源浪费。。。。
     */
    @PostMapping(value = "/check3")
    @ApiOperation(value = "参数校验 （controller层）")
    public ResponseBean check3(@Valid @RequestBody ParamCheckDTO dto, BindingResult result){
        if(result.hasErrors()){
            throw new ParamValidException(result.getFieldError().getDefaultMessage());
        }
        paramCheckService3.check3(dto);
        return ResponseBean.SUCCESS("校验2");
    }


}
