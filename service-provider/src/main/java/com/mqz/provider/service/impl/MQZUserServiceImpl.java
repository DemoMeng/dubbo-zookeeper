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
 * @Date：2021/4/26 4:57 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
//@org.apache.dubbo.config.annotation.Service(version = "1.0")
//dubbo @Service、@Refrence  在2.7之后已经过时，使用@DubboService、DubboRefrence替代
@DubboService(version = "0.1")
@Slf4j
public class MQZUserServiceImpl implements MQZUserService {
    @Override
    public List<Map<String,Object>> list() {
        log.info("dubbo-interface[0.1] was been called !!");
        Map<String,Object> map =new HashMap<>();
        map.put("userName","mengqizhang");
        map.put("phone","131xxxxxxxxx");
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}
