package com.mqz.monitor.notify;

import cn.hutool.json.JSONUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
/**
 * 版权所有 © Copyright 2012<br>
 *
 * @Author： 蒙大拿
 * @Date：2021-05-06
 * @Description
 * @About： https://github.com/DemoMeng
 */
@Service
public class SelfNotify extends AbstractStatusChangeNotifier {


    public SelfNotify(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        StringBuilder str = new StringBuilder();
        str.append("<p>监控报警</p> ");
        str.append("【服务名称】: 【" + serviceName + "】<br/>");
        str.append("【服务地址】:" + serviceUrl+"<br/>");
        str.append("【状态】:" + status+"<br/>");
        str.append("【详情】:" + JSONUtil.toJsonStr(details));
        return Mono.fromRunnable(() -> {
            //TODO 通知处理， 钉钉，邮件，短信
        });
    }
}
