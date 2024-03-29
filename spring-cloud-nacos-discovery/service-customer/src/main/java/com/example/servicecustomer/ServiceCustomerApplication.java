package com.example.servicecustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomerApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    public class TestController {

        private final RestTemplate restTemplate;

        @Autowired
        public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

        @GetMapping(value = "/echo/{str}")
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
        }
    }
}
