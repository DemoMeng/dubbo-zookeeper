package com.mqz.consumer.web.config.interceptor;

import cn.hutool.json.JSONUtil;
import com.mqz.api.service.annotations.ThreadLocalNeed;
import com.mqz.api.service.constants.CommonConstant;
import com.mqz.consumer.web.config.thread.LocalContext;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/17 4:18 下午
 * @Description
 * @About： https://github.com/DemoMeng
 */
public class HeadThreadLocalInterceptor implements HandlerInterceptor {



    /**
     *  TODO 解决方式把业务线放到 request OR ThreadLocal中 或者 RpcContext中
     *  注意 ： 使用ThreadLocal要在thread结束之后销毁否则大概率出现内存泄漏！
     *  request用不了（dubbo中，也不能作为参数传到service因为无法序列化！！）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            if(handler instanceof HandlerMethod){
                if(null != ((HandlerMethod) handler).getMethodAnnotation(ThreadLocalNeed.class) ){
                    //业务线
                    String businessOwnership = request.getHeader(CommonConstant.REQUEST_HEAD_NAME_B_O);
                    if(StringUtils.isEmpty(businessOwnership)){
                        Map<String,Object> resultJson = new HashMap<>();
                        resultJson.put("code",50001);
                        resultJson.put("data",null);
                        resultJson.put("msg","请求头中业务线[business]不得为空！");
                        returnJson(response, JSONUtil.toJsonStr(resultJson));
                        return false;
                    }
                    //TODO 查询数据库，判断参数的合法性！
                    String data = UUID.randomUUID().toString();

                    //TODO 先放到request中
                    //request.setAttribute(CommonConstant.REQUEST_HEAD_NAME_B_O,saleDeptBusiness.getId());
                    LocalContext.add(CommonConstant.REQUEST_HEAD_NAME_B_O,data);

                    //放到RpcContext上下文中，服务方和消费方共同共享，ThreadLocal只对当前程序
                    //dubbo通过客户端向服务器端传递参数，传递参数时path,group,version,dubbo,token,timeout即key有特殊处理，关键字
                    //服务方获取 ： RpcContext.getContext().getAttachments();
                    //消费方传参 ： RpcContext.getContext().setAttachment();
                    //调接口时，必须是A直接到B，如果A没有直接到B，而是先到C，再由C到B，那么在B里getAttachment()，就获取不到值了。
                    RpcContext.getContext().setAttachment("rpc-name",data);

//                HashMap<String,Object> hashMap = new HashMap<>();
//                hashMap.put(CommonConstant.REQUEST_HEAD_NAME_B_O,saleDeptBusiness.getId());
//                LocalContext.transArguments(hashMap);
                    return true;
                }
                return true;
            }
            return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocalContext.remove();//避免内存泄漏
    }


    /**
     * 返回json
     * @param response
     * @param json
     */
    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }


}
