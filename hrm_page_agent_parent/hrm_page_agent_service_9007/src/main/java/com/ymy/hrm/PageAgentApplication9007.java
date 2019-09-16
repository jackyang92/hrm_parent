package com.ymy.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PageAgentApplication9007 {
    public static void main(String[] args) {
        SpringApplication.run(PageAgentApplication9007.class,args);
    }
}
