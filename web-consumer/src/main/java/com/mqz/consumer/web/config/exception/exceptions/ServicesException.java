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
public class ServicesException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8332058098331820277L;

    private String msg;

    public ServicesException(){

    }

    public ServicesException(String msg){
        this.msg = msg;
    }

}
