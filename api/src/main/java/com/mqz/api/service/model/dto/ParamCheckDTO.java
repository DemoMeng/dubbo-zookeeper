package com.mqz.api.service.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 4:11 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
@Data
@Accessors(chain = true)
public class ParamCheckDTO implements Serializable {

    private static final long serialVersionUID = 1524303936925858248L;

    @NotNull(message = "name不得为空")
    @Size(min = 0,max = 10,message = "name长度不得超过3")
    private String name;
    @Min(value = 0,message = "sex最小不能小于0")
    @Max(value = 2,message = "sex最大不能大于2")
    private int sex;

}
