package com.mqz.provider.service.impl.system;

import com.mqz.api.service.system.LoadBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 *  版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021/5/6 1:41 下午
 * @Description
 * @About： https://github.com/DemoMeng
 *
 * dubbo负载均衡策略：
 *     consistenthash ：一致性hash，把请求参数hash相同分配到同个服务
 *     leastactive ：最少连接数，越不活跃的服务分配处理的请求越少
 *     random ： 缺省配置，随机
 *     roundrobin ：轮询，可能存在请求堆积的情况
 *     shortestresponse ： 最短响应时间，2.7.7提出来，为解决的问题： leastactive下，服务器性能极端情况下，高配置服务器收到了所有请求，低性能没有收到请求，这样就不存在负载均衡了。
 *
 * 重试机制：（集群容错）
 *     官方文档： https://dubbo.apache.org/zh/docs/v2.7/user/examples/fault-tolerent-strategy/
 *
 *
 */

@DubboService(loadbalance = "roundrobin",retries = 2)
@Slf4j
public class LoadBalanceServiceImpl implements LoadBalanceService {


    @Override
    public void test1() {
        log.info("test1 被调用。。。。。。。");
    }

    @Override
    public void test2() {
        log.info("test1 被调用。。。。。。。");
    }

    @Override
    public void test3() {
        log.info("test1 被调用。。。。。。。");
    }
}
