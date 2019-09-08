package com.ymy.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RedisApplication9005 {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication9005.class,args);
    }
}
