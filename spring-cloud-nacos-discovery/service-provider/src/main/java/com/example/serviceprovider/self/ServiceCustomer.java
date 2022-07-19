package com.example.serviceprovider.self;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Properties;

/**
 * @className: ServiceCustomer
 * @description: 创建一个消费服务，向注册中心订阅一个服务，拿到服务列表，模拟五次服务请求
 * @tag
 * @author: louis
 * @date: 2022/7/19
 **/
public class ServiceCustomer {
    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", Consts.NACOS_SERVER_ADDR);
        properties.setProperty("namespace", Consts.NAMESOACE);

        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.subscribe(Consts.SERVICE_NAME, new EventListener() {
            @Override
            public void onEvent(Event event) {
                NamingEvent namingEvent = (NamingEvent) event;
                printInstance(namingEvent);
                mockConsume(namingService, Consts.SERVICE_NAME);
            }
        });

        try {
            int n = System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mockConsume(NamingService namingService, String serviceName) {
        int i = 0, loop = 5;
        try {
            while (i++ < loop) {
                Instance instance = namingService.selectOneHealthyInstance(serviceName);
                if (instance != null) {
                    System.out.println("get one healthy instance of service:" + serviceName
                            + "\nip=" + instance.getIp()
                            + ",\nport=" + instance.getPort()
                            + ",\ncuster=" + instance.getClusterName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printInstance(NamingEvent namingEvent) {
        List<Instance> instances = namingEvent.getInstances();
        System.out.println("all instance of service:\n");
        for (Instance instance : instances) {
            System.out.println("ip=" + instance.getIp()
                    + ",port=" + instance.getPort()
                    + ",custer=" + instance.getClusterName());
        }
        System.out.println("=====");
    }
}
