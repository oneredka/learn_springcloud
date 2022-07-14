package com.example.springcloudnacos.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConfigController
 * @description: TODO description
 * @tag
 * @author: louis
 * @date: 2022/7/14
 **/
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${publicKey:123456}")
    private String publicKey;

    /**
     * http://localhost:8222/config/get
     */

    @RequestMapping("/get")
    public String get() {
        return publicKey;
    }
}
