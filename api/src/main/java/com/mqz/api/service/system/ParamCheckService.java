package com.mqz.api.service.system;

import com.mqz.api.service.model.dto.ParamCheckDTO;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 4:10 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
public interface ParamCheckService {

    @interface Check1{}
    String check1(ParamCheckDTO dto);




}
