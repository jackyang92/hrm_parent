package com.ymy.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ymy.hrm.mapper")
public class ManagementApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication9001.class,args);
    }
}
