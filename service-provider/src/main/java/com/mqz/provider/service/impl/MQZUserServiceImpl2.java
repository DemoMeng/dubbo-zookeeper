package com.mqz.provider.service.impl;

import com.mqz.api.service.system.MQZUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/4/28 4:08 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@DubboService(version = "0.2")
@Slf4j
public class MQZUserServiceImpl2 implements MQZUserService {
    @Override
    public List<Map<String, Object>> list() {
        log.info("dubbo-interface[0.2] was been called !!");
        Map<String,Object> map =new HashMap<>();
        map.put("userName","zzzz");
        map.put("phone","222222");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
