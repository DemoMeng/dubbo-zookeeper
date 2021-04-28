package com.mqz.provider.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author： 蒙大拿
 * @Date：2021/4/8 4:22 下午
 * @Description
 * @About： https://github.com/DemoMeng
 **/
public class Start {


    public Start getPercent(){

        String num1 = "7.1";
        String num2 = "9.6";

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);

        String result = numberFormat.format(Float.valueOf(num1)  / Float.valueOf(num2) * 100);

        System.out.println("num1和num2的百分比为:" + result + "%");

        return null;
    }

    public void getDiffHours() throws ParseException {
        String fromTime = "2021-04-08 15:20:50";
        String toTime = "2021-04-08 15:55:20";

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 注意：hh:12小时制度，HH:小时制
        Date fromM = simpleFormat.parse(fromTime.substring(0, 19));    // 截取到分钟
        Date toM = simpleFormat.parse(toTime.substring(0, 19));

        long from = fromM.getTime();   // getTime()返回到毫秒
        long to = toM.getTime();
        System.out.println((to - from) / (1000 * 60));
        int gap = (int) ((to - from) / (1000 * 60));
        System.out.println("util.CountTimeGap 两个时间之间的小时差gap为：" + gap);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = df.parse("2004-03-26 13:31:40");
            Date d2 = df.parse("2004-03-27 15:30:24");
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
        }catch (Exception e)
        {
        }
    }


    public static void main(String[] args) throws ParseException {
            Start s = new Start().getPercent();
    }

}
