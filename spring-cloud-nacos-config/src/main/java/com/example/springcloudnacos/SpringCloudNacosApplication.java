package com.example.springcloudnacos;

import org.apache.tomcat.jni.Time;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringCloudNacosApplication {

    public static void main(String[] args) throws InterruptedException {
        //SpringApplication.run(SpringCloudNacosApplication.class, args);

        /**
         * 手动在nacos 上添加配置
         * user.name=nacos-config-properties
         * user.age=90
         */
       /* ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringCloudNacosApplication.class, args);
        String  userName = applicationContext.getEnvironment().getProperty("user.name");
        String  userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.out.println("user name: " + userName + ", user age: " + userAge);*/

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringCloudNacosApplication.class, args);
        while (true) {
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.err.println("user name: " + userName + ", user age: " + userAge);
            System.err.println("extension config:" + applicationContext.getEnvironment().getProperty("parttern.name"));
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
