package com.mqz.provider.service.impl.system;

import com.mqz.api.service.exceptions.ParamValidException;
import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.system.ParamCheckService3;
import com.mqz.api.service.validation.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/6/3 10:43 上午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@DubboService()
@Slf4j
public class ParamCheckServiceImpl3 implements ParamCheckService3 {
    @Override
    public String check3(ParamCheckDTO dto) {
        System.out.println("参数校验。。。");
        return null;
    }

    @Override
    public String check4(ParamCheckDTO dto) {
        ValidationUtil.ValidResult result = ValidationUtil.validateBean(dto);
        if(result.hasErrors()){
            String errorMessage = result.getErrors();
            String errorProperties = result.getProperties();
            log.error("【参数异常-信息】{}",errorMessage);
            log.error("【参数异常-字段】{}",errorProperties);
            throw new ParamValidException(errorMessage);
        }
        return null;
    }
}
