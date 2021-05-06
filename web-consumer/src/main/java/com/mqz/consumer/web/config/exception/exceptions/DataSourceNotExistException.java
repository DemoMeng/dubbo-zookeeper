package com.mqz.consumer.web.config.exception.exceptions;

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
public class DataSourceNotExistException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2522728131469045613L;

    private String msg;

    public DataSourceNotExistException(){}

    public DataSourceNotExistException(String msg){
        this.msg = msg;
    }


}
