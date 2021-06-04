package com.mqz.provider.service.impl.system;

import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.system.ParamCheckService;
import com.mqz.api.service.system.ParamCheckService2;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 4:24 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@DubboService(timeout = 5000,validation = "true")// TODO 这种方式和Dubbo官方提供的基本一致
@Slf4j
@Validated//   暂时这种方式不可行，
public class ParamCheckServiceImpl2 implements ParamCheckService2, Serializable {

    //service层校验需要ServiceImpl实现序列化接口
    private static final long serialVersionUID = 7936662556557524229L;

    @Override
    public String check2(@Valid ParamCheckDTO dto) {
        System.out.println("参数校验。。。");
        return null;
    }
}
