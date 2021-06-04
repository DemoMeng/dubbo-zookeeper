package com.mqz.api.service.validation;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.validation.Validation;
import org.apache.dubbo.validation.Validator;
import org.apache.dubbo.validation.support.jvalidation.JValidator;

import javax.validation.*;
import java.util.*;

/**
 *  版权所有 © Copyright 2012<br>
 *
 *      在service层使用了@Validated 进行了参数校验，会出现如下异常
 *          Caused by: com.alibaba.com.caucho.hessian.io.HessianProtocolException: 'org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl' could not be instantiated
 *      参考： https://segmentfault.com/a/1190000018986674
 *
 *
 * @Author： 蒙大拿
 * @Date：2021/6/3 10:03 上午
 * @Description
 * @About： https://github.com/DemoMeng
 */


public class BetterValidation implements Validation{

    @Override
    public Validator getValidator(URL url) {
        return new BetterValidator(url);
    }
}

class BetterValidator extends JValidator{
    private final Class<?> clazz;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public BetterValidator(URL url) {
        super(url);
        this.clazz = ReflectUtils.forName(url.getServiceInterface());
    }

    @Override
    public void validate(String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Exception {

        try{
            super.validate(methodName,parameterTypes,arguments);
        }catch (ConstraintViolationException e){
            throw new ValidationException(constraintMessage(clazz.getName(),methodName,e.getConstraintViolations()));
        }
    }

    private String constraintMessage(String className,String methodName,Set<ConstraintViolation<?>> violations){
        JSONObject json = new JSONObject();
        json.put("service",className);
        json.put("method",methodName);
        JSONArray details = new JSONArray();
        for(ConstraintViolation violation : violations){
            JSONObject detail = new JSONObject();
            detail.put("bean",violation.getRootBean().getClass().getName());
            detail.put("property",violation.getPropertyPath().toString());
            detail.put("message",violation.getMessage());
            details.add(detail);
        }
        json.put("details",details);
        return json.toString();
    }

}
