package com.mqz.api.service.exceptions;

import lombok.Data;

import java.io.Serializable;
/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021-05-06
 * @Description
 * @About： https://github.com/DemoMeng
 */
@Data
public class ParamValidException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -8171188744290010026L;

    private String msg;

    public ParamValidException(){}

    public ParamValidException(String msg){
        this.msg = msg;
    }
}
