package com.example.serviceprovider.self;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Properties;

/**
 * @className: ServiceProvider
 * @description: 模拟注册两个实例到注册中心
 * @tag
 * @author: louis
 * @date: 2022/7/19
 **/
public class ServiceProvider {

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", Consts.NACOS_SERVER_ADDR);
        properties.setProperty("namespace", Consts.NAMESOACE);

        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.registerInstance(Consts.SERVICE_NAME, Consts.IP_1, Consts.PORT_1, Consts.CUSTER_1);
        namingService.registerInstance(Consts.SERVICE_NAME, Consts.IP_2, Consts.PORT_2, Consts.CUSTER_2);
        List<Instance> instances = namingService.getAllInstances(Consts.SERVICE_NAME);
        System.out.println("getAllInstances after registered\ninstance size="
                + instances.size() + "\ninstance list =" + instances);
        try {
            int n = System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
