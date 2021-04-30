package com.mqz.monitor.notify.dingding;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.TestOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2020/6/4
 * @Description
 * @About： https://github.com/DemoMeng
 */
public class DingService {

    private final static Logger log = LoggerFactory.getLogger(DingService.class);
    private final static String url ="****";
    private final static String secret = "*****";

    //初始化客户端
    static OkHttpClient mClient ;
    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.readTimeout(10L, TimeUnit.SECONDS);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(200);
        dispatcher.setMaxRequests(200);
        builder.dispatcher(dispatcher);
        mClient = builder.build();
    }

    /**
     * 发送text消息
     * @param content
     * @return
     */
    public static String postWithText(String content) {
        Map<String,Object> params = new HashMap<>();
        Map<String,Object> text = new HashMap<>();
        params.put("msgtype","text");
        params.put("text",text);
        text.put("content",content);
        //  获取签名字符串
        System.out.println(JSONUtil.toJsonStr(text));
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = "&timestamp="+timestamp+"&sign="+ URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            String requestUrl = url+sign;
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONUtil.toJsonStr(params));
            Request request = new Request.Builder().url(requestUrl).post(body).build();
            Response response = mClient.newCall(request).execute();
            log.info("钉钉返回：{}",response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TestOnly
    public static String postWithJson(String url,String param) {
        //构建请求参数
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.body() != null) {
                String res = response.body().string();
                System.out.println(res);
                return response.body().string();
            }
        } catch (IOException var5) {
            //log.error("Exception:", var5);
        }
        return null;
    }

//    public static void main(String[] args) throws IOException {
//
//        JSONObject jsonObject = new JSONObject();
//        //固定参数
//        jsonObject.put("msgtype","text");
//        JSONObject content = new JSONObject();
//        //此处message是你想要发送到钉钉的信息
//        content.put("content","2020-09-22 20:08:18 INFO  http-nio-8090-exec-8 cn.fangxinqian.order.zhendao.service.handle.AopAspect 7de132af9503427b9dc217fee36f452b, url: http://zhenxinqian.71360.com/common/formId, method: GET, uri: /common/formId, params:");
//        jsonObject.put("text",content);
//        try {
//            //  获取签名字符串
//            Long timestamp = System.currentTimeMillis();
//            String stringToSign = timestamp + "\n" + secret;
//            Mac mac = Mac.getInstance("HmacSHA256");
//            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
//            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
//            String sign = "&timestamp="+timestamp+"&sign="+ URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
//            //发送
//            postWithJson(url + sign , jsonObject.toJSONString(22));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    static class User{
        private String userName;
        private String sex;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static void main(String[] args) {


        User u = new User();
        u.setSex("man");
        u.setUserName("mengqizhang");


        System.out.println(Optional.ofNullable(u).get().getUserName());
        System.out.println(Optional.of(u).get().getUserName());

        User uu = new User();
        System.out.println(Optional.ofNullable(uu).get().getUserName());
        System.out.println(Optional.of(uu).get().getUserName());


        List<String> usess= new ArrayList<>();
        usess.add("mengsdsd");
        usess.add("sdfsdf");
        System.out.println(usess.toString());


        BigDecimal a = new BigDecimal(0);

        for(int i=1;i<=1;i++){
            BigDecimal s = new BigDecimal(i);
            a = a.add(s);
        }
        System.out.println(a.toString());

    }

//    public static void main(String[] args) {
//        String error = "[WARN ]2021-04-30 09:52:40.911 [localhost-startStop-1-SendThread(127.0.0.1:2181)] org.apache.zookeeper.ClientCnxn.run 1102 -- Session 0x177d360b4190424 for server 127.0.0.1/127.0.0.1:2181, unexpected error, closing socket connection and attempting reconnect\n" +
//                "java.lang.IllegalStateException: Can't overwrite cause with java.lang.IllegalStateException: Illegal access: this web application instance has been stopped already. Could not load [org.apache.zookeeper.proto.SetWatches]. The following stack trace is thrown for debugging purposes as well as to attempt to terminate the thread which caused the illegal access.\n" +
//                "        at java.lang.Throwable.initCause(Throwable.java:457) ~[na:1.8.0_11]";
//        System.out.println(postWithText(error));
//    }
}
