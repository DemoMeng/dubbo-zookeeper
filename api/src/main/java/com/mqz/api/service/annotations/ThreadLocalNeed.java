package com.mqz.api.service.annotations;

import java.lang.annotation.*;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/17 4:21 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThreadLocalNeed {

    //业务线id，备用
    int id() default -1;
    //备注
    String description() default "businessOwnershipId";
}
