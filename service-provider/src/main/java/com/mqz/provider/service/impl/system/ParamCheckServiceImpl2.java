package com.mqz.provider.service.impl.system;

import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.system.ParamCheckService;
import com.mqz.api.service.system.ParamCheckService2;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 4:24 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@DubboService()
@Slf4j
@Validated
public class ParamCheckServiceImpl2 implements ParamCheckService2 {
    @Override
    public String check2(@Valid ParamCheckDTO dto) {

        return null;
    }
}
