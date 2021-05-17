package com.mqz.consumer.web.config.thread;

import java.util.Map;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/17 4:09 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
public class LocalContext{

    private static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    static {
        threadLocal = new ThreadLocal<>();
    }


    public static void add(String key,String value){
        threadLocal.get().put(key,value);
    }

    public static String get(String key){
        return threadLocal.get().get(key) == null?null:(String) threadLocal.get().get("key");
    }

    public static void remove(){
        threadLocal.remove();
    }

}
