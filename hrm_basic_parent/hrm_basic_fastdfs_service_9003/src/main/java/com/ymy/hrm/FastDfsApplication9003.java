package com.ymy.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FastDfsApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(FastDfsApplication9003.class,args);
    }
}
