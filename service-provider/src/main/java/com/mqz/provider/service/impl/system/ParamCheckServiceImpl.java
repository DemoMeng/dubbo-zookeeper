package com.mqz.provider.service.impl.system;

import com.mqz.api.service.model.dto.ParamCheckDTO;
import com.mqz.api.service.system.ParamCheckService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 4:24 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@DubboService(validation = "true") //dubbo 服务方开启参数校验 ，调用方也需要！！
@Slf4j
public class ParamCheckServiceImpl implements ParamCheckService {
    @Override
    public String check1(ParamCheckDTO dto) {

        log.info("参数校验方法调用了");
        return null;
    }
}
